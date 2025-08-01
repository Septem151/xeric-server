package io.septem150.xeric.server.task;

import io.septem150.xeric.server.player.PlayerRepository;
import io.septem150.xeric.server.util.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class TaskService {

    private final TaskRepository taskRepository;
    private final PlayerRepository playerRepository;

    public TaskService(final TaskRepository taskRepository,
            final PlayerRepository playerRepository) {
        this.taskRepository = taskRepository;
        this.playerRepository = playerRepository;
    }

    public List<TaskDTO> findAll() {
        final List<Task> tasks = taskRepository.findAll(Sort.by("id"));
        return tasks.stream()
                .map(task -> mapToDTO(task, new TaskDTO()))
                .toList();
    }

    public TaskDTO get(final Long id) {
        return taskRepository.findById(id)
                .map(task -> mapToDTO(task, new TaskDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TaskDTO taskDTO) {
        final Task task = new Task();
        mapToEntity(taskDTO, task);
        return taskRepository.save(task).getId();
    }

    @Transactional
    public List<Long> bulkCreate(final List<TaskDTO> taskDTOs) {
        final List<Long> taskIds = new ArrayList<>();
        for (TaskDTO taskDTO : taskDTOs) {
            final long taskId = create(taskDTO);
            taskIds.add(taskId);
        }
        return taskIds;
    }

    public void update(final Long id, final TaskDTO taskDTO) {
        final Task task = taskRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(taskDTO, task);
        taskRepository.save(task);
    }

    public void delete(final Long id) {
        final Task task = taskRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        playerRepository.findAllByTasks(task)
                .forEach(player -> player.getTasks().remove(task));
        taskRepository.delete(task);
    }

    private TaskDTO mapToDTO(final Task task, final TaskDTO taskDTO) {
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setTier(task.getTier());
        taskDTO.setType(task.getType());
        taskDTO.setIcon(task.getIcon());
        taskDTO.setSlayerPoints(task.getSlayerPoints());
        taskDTO.setTotal(task.getTotal());
        taskDTO.setItemIds(task.getItemIds());
        taskDTO.setAmount(task.getAmount());
        taskDTO.setDiary(task.getDiary());
        taskDTO.setBoss(task.getBoss());
        taskDTO.setLevel(task.getLevel());
        taskDTO.setGoal(task.getGoal());
        taskDTO.setQuest(task.getQuest());
        return taskDTO;
    }

    private void mapToEntity(final TaskDTO taskDTO, final Task task) {
        task.setName(taskDTO.getName());
        task.setTier(taskDTO.getTier());
        task.setType(taskDTO.getType());
        task.setIcon(taskDTO.getIcon());
        task.setSlayerPoints(taskDTO.getSlayerPoints());
        task.setTotal(taskDTO.getTotal());
        task.setItemIds(taskDTO.getItemIds());
        task.setAmount(taskDTO.getAmount());
        task.setDiary(taskDTO.getDiary());
        task.setBoss(taskDTO.getBoss());
        task.setLevel(taskDTO.getLevel());
        task.setGoal(taskDTO.getGoal());
        task.setQuest(taskDTO.getQuest());
    }

    public boolean nameExists(final String name) {
        return taskRepository.existsByNameIgnoreCase(name);
    }

}
