package io.septem150.xeric_server.player;

import io.septem150.xeric_server.task.Task;
import io.septem150.xeric_server.task.TaskRepository;
import io.septem150.xeric_server.util.NotFoundException;
import java.util.HashSet;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TaskRepository taskRepository;

    public PlayerService(final PlayerRepository playerRepository,
            final TaskRepository taskRepository) {
        this.playerRepository = playerRepository;
        this.taskRepository = taskRepository;
    }

    public List<PlayerDTO> findAll() {
        final List<Player> players = playerRepository.findAll(Sort.by("id"));
        return players.stream()
                .map(player -> mapToDTO(player, new PlayerDTO()))
                .toList();
    }

    public PlayerDTO get(final Long id) {
        return playerRepository.findById(id)
                .map(player -> mapToDTO(player, new PlayerDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PlayerDTO playerDTO) {
        final Player player = new Player();
        mapToEntity(playerDTO, player);
        return playerRepository.save(player).getId();
    }

    public void update(final Long id, final PlayerDTO playerDTO) {
        final Player player = playerRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(playerDTO, player);
        playerRepository.save(player);
    }

    public void delete(final Long id) {
        playerRepository.deleteById(id);
    }

    private PlayerDTO mapToDTO(final Player player, final PlayerDTO playerDTO) {
        playerDTO.setId(player.getId());
        playerDTO.setUsername(player.getUsername());
        playerDTO.setAccountType(player.getAccountType());
        playerDTO.setAccountExceptions(player.getAccountExceptions());
        playerDTO.setTasks(player.getTasks().stream()
                .map(task -> task.getId())
                .toList());
        return playerDTO;
    }

    private Player mapToEntity(final PlayerDTO playerDTO, final Player player) {
        player.setUsername(playerDTO.getUsername());
        player.setAccountType(playerDTO.getAccountType());
        player.setAccountExceptions(playerDTO.getAccountExceptions());
        final List<Task> tasks = taskRepository.findAllById(
                playerDTO.getTasks() == null ? List.of() : playerDTO.getTasks());
        if (tasks.size() != (playerDTO.getTasks() == null ? 0 : playerDTO.getTasks().size())) {
            throw new NotFoundException("one of tasks not found");
        }
        player.setTasks(new HashSet<>(tasks));
        return player;
    }

    public boolean usernameExists(final String username) {
        return playerRepository.existsByUsernameIgnoreCase(username);
    }

}
