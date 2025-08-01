package io.septem150.xeric.server.player;

import io.septem150.xeric.server.task.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findFirstByTasks(Task task);

    List<Player> findAllByTasks(Task task);

    boolean existsByUsernameIgnoreCase(String username);

}
