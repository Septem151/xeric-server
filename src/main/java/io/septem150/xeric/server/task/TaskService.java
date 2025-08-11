package io.septem150.xeric.server.task;

import io.septem150.xeric.server.util.NotFoundException;
import io.septem150.xeric.server.util.UniqueNameException;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@Transactional
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class TaskService {
  private final TaskRepository taskRepository;

  public @NonNull List<TaskDTO> findAll() {
    final List<Task> tasks = taskRepository.findAll(Sort.by("id"));
    return tasks.stream()
            .map(task -> mapToDTO(task, new TaskDTO()))
            .toList();
  }

  public @NonNull TaskDTO get(@NonNull final Long id) {
    return taskRepository.findById(id)
            .map(task -> mapToDTO(task, new TaskDTO()))
            .orElseThrow(() -> new NotFoundException("task not found"));
  }

  public @NonNull TaskDTO get(@NonNull final String name) {
    return taskRepository.findByNameIgnoreCase(name)
            .map(task -> mapToDTO(task, new TaskDTO()))
            .orElseThrow(() -> new NotFoundException("task not found"));
  }

  public @NonNull Long create(@NonNull final TaskDTO taskDTO) {
    Task task = taskRepository.findByNameIgnoreCase(taskDTO.getName()).orElse(null);
    if (task != null) {
      throw new UniqueNameException("task already exists", task.getId());
    }
    task = new Task();
    mapToEntity(taskDTO, task);
    return taskRepository.save(task).getId();
  }

  public @NonNull List<Long> bulkCreate(@Valid @NonNull final List<TaskDTO> taskDTOs) {
    List<Long> taskIds = new ArrayList<>();
    for (TaskDTO taskDTO : taskDTOs) {
      taskIds.add(create(taskDTO));
    }
    return taskIds;
  }

  public void update(@NonNull final Long id, @NonNull final TaskDTO taskDTO) {
    final Task task = taskRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("task not found"));
    mapToEntity(taskDTO, task);
    taskRepository.save(task);
  }

  public void delete(@NonNull final Long id) {
    if (!taskRepository.existsById(id)) {
      throw new NotFoundException("player not found");
    }
    taskRepository.deleteById(id);
  }

  private @NonNull TaskDTO mapToDTO(@NonNull final Task entity, @NonNull final TaskDTO dto) {
    dto.setAmount(entity.getAmount());
    dto.setBoss(entity.getBoss());
    dto.setDiary(entity.getDiary());
    dto.setGoal(entity.getGoal());
    dto.setIcon(entity.getIcon());
    dto.setId(entity.getId());
    dto.setItemIds(entity.getItemIds());
    dto.setLevel(entity.getLevel());
    dto.setName(entity.getName());
    dto.setQuest(entity.getQuest());
    dto.setSlayerPoints(entity.getSlayerPoints());
    dto.setTier(entity.getTier());
    dto.setTotal(entity.getTotal());
    dto.setType(entity.getType());
    return dto;
  }

  private void mapToEntity(@NonNull final TaskDTO dto, @NonNull final Task entity) {
    entity.setAmount(dto.getAmount());
    entity.setBoss(dto.getBoss());
    entity.setDiary(dto.getDiary());
    entity.setGoal(dto.getGoal());
    entity.setIcon(dto.getIcon());
    entity.setItemIds(dto.getItemIds());
    entity.setLevel(dto.getLevel());
    entity.setName(dto.getName());
    entity.setQuest(dto.getQuest());
    entity.setSlayerPoints(dto.getSlayerPoints());
    entity.setTier(dto.getTier());
    entity.setTotal(dto.getTotal());
    entity.setType(dto.getType());
  }
}
