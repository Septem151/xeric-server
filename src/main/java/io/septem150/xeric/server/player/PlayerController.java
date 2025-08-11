package io.septem150.xeric.server.player;

import io.septem150.xeric.server.util.RequireAdmin;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PlayerController {
  private final PlayerService playerService;

  @GetMapping
  public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
    return ResponseEntity.ok(playerService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlayerDTO> getPlayer(@PathVariable(name = "id") final Long id) {
    return ResponseEntity.ok(playerService.get(id));
  }

  @PostMapping
  @ApiResponse(responseCode = "201")
  public ResponseEntity<Long> createPlayer(@RequestBody @Valid final PlayerDTO playerDTO) {
    final Long createdId = playerService.create(playerDTO);
    return new ResponseEntity<>(createdId, HttpStatus.CREATED);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Long> updatePlayer(@PathVariable(name = "id") final Long id,
                                           @RequestBody @Valid final PlayerDTO playerDTO) {
    playerService.update(id, playerDTO);
    return ResponseEntity.ok(id);
  }

  @DeleteMapping("/{id}")
  @ApiResponse(responseCode = "204")
  @RequireAdmin
  public ResponseEntity<Void> deletePlayer(@PathVariable(name = "id") final Long id) {
    playerService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
