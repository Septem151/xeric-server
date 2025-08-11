package io.septem150.xeric.server.player;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PlayerRepository extends JpaRepository<Player, Long> {

  boolean existsByUsernameIgnoreCase(String username);

  Optional<Player> findByUsernameIgnoreCase(String name);
}
