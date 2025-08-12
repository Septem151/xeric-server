/**
BSD 2-Clause License

Copyright (c) 2025, Carly Mullins

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package io.septem150.xeric.server.player;

import io.septem150.xeric.server.task.Task;
import io.septem150.xeric.server.task.TaskRepository;
import io.septem150.xeric.server.util.NotFoundException;
import io.septem150.xeric.server.util.UniqueNameException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PlayerService {
  private final PlayerRepository playerRepository;
  private final TaskRepository taskRepository;

  public @NonNull List<PlayerDTO> findAll() {
    final List<Player> players = playerRepository.findAll(Sort.by("id"));
    return players.stream().map(player -> mapToDTO(player, new PlayerDTO())).toList();
  }

  public @NonNull PlayerDTO get(@NonNull final Long id) {
    return playerRepository
        .findById(id)
        .map(player -> mapToDTO(player, new PlayerDTO()))
        .orElseThrow(() -> new NotFoundException("player not found"));
  }

  public @NonNull PlayerDTO get(@NonNull final String name) {
    return playerRepository
        .findByUsernameIgnoreCase(name)
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
    final Player player =
        playerRepository.findById(id).orElseThrow(() -> new NotFoundException("player not found"));
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
      final List<Task> entityTasks =
          entity.getTaskCompletions().stream().map(PlayerTask::getTask).toList();
      dtoTasks.removeAll(entityTasks);
    } else {
      entity.setTaskCompletions(new ArrayList<>());
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
