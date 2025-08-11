package io.septem150.xeric.server.player;

import io.septem150.xeric.server.task.Task;
import io.septem150.xeric.server.task.TaskRepository;
import io.septem150.xeric.server.util.NotFoundException;
import io.septem150.xeric.server.util.UniqueNameException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Validated
@Transactional
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PlayerService {
  private final PlayerRepository playerRepository;
  private final TaskRepository taskRepository;

  public @NonNull List<PlayerDTO> findAll() {
    final List<Player> players = playerRepository.findAll(Sort.by("id"));
    return players.stream()
            .map(player -> mapToDTO(player, new PlayerDTO()))
            .toList();
  }

  public @NonNull PlayerDTO get(@NonNull final Long id) {
    return playerRepository.findById(id)
            .map(player -> mapToDTO(player, new PlayerDTO()))
            .orElseThrow(() -> new NotFoundException("player not found"));
  }

  public @NonNull PlayerDTO get(@NonNull final String name) {
    return playerRepository.findByUsernameIgnoreCase(name)
            .map(player -> mapToDTO(player, new PlayerDTO()))
            .orElseThrow(() -> new NotFoundException("player not found"));
  }

  public @NonNull Long create(@NonNull final PlayerDTO playerDTO) {
    Player player = playerRepository.findByUsernameIgnoreCase(playerDTO.getUsername()).orElse(null);
    if (player != null) {
      throw new UniqueNameException("player already exists", player.getId());
    }
    player = new Player();
    mapToEntity(playerDTO, player);
    return playerRepository.save(player).getId();
  }

  public void update(@NonNull final Long id, @NonNull final PlayerDTO playerDTO) {
    final Player player = playerRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("player not found"));
    mapToEntity(playerDTO, player);
    playerRepository.save(player);
  }

  public void delete(@NonNull final Long id) {
    if (!playerRepository.existsById(id)) {
      throw new NotFoundException("player not found");
    }
    playerRepository.deleteById(id);
  }

  private @NonNull PlayerDTO mapToDTO(@NonNull final Player entity, @NonNull final PlayerDTO dto) {
    dto.setId(entity.getId());
    dto.setUsername(entity.getUsername());
    dto.setAccountType(entity.getAccountType());
    dto.setAccountExceptions(entity.getAccountExceptions());
    dto.setTasks(entity.getTaskCompletions().stream().map(PlayerTask::getTaskId).toList());
    dto.setPoints(entity.getPoints());
    dto.setSlayerException(entity.isSlayerException());
    return dto;
  }

  private void mapToEntity(@NonNull final PlayerDTO dto, @NonNull final Player entity) {
    entity.setUsername(dto.getUsername());
    entity.setAccountType(dto.getAccountType());
    entity.setAccountExceptions(dto.getAccountExceptions());
    final List<Task> dtoTasks = taskRepository.findAllById(dto.getTasks());
    if (dtoTasks.size() != dto.getTasks().size()) {
      throw new NotFoundException("one or more tasks not found");
    }
    if (entity.getTaskCompletions() != null) {
      final List<Task> entityTasks = entity.getTaskCompletions().stream().map(PlayerTask::getTask).toList();
      dtoTasks.removeAll(entityTasks);
    }
    for (Task task : dtoTasks) {
      PlayerTask playerTask = new PlayerTask();
      playerTask.setTask(task);
      playerTask.setPlayer(entity);
      playerTask.setDateCompleted(OffsetDateTime.now());
      entity.getTaskCompletions().add(playerTask);
    }
  }
}
