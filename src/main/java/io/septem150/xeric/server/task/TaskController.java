package io.septem150.xeric.server.task;

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
@RequestMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class TaskController {
  private final TaskService taskService;

  @GetMapping
  public ResponseEntity<List<TaskDTO>> getAllTasks() {
    return ResponseEntity.ok(taskService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskDTO> getTask(@PathVariable(name = "id") final Long id) {
    return ResponseEntity.ok(taskService.get(id));
  }

  @PostMapping
  @ApiResponse(responseCode = "201")
  @RequireAdmin
  public ResponseEntity<Long> createTask(@RequestBody @Valid final TaskDTO taskDTO) {
    final Long createdId = taskService.create(taskDTO);
    return new ResponseEntity<>(createdId, HttpStatus.CREATED);
  }

  @PostMapping("/bulk")
  @ApiResponse(responseCode = "201")
  @RequireAdmin
  public ResponseEntity<List<Long>> bulkCreate(@RequestBody @Valid final List<TaskDTO> taskDTOs) {
    final List<Long> createdIds = taskService.bulkCreate(taskDTOs);
    return new ResponseEntity<>(createdIds, HttpStatus.CREATED);
  }

  @PostMapping("/{id}")
  @RequireAdmin
  public ResponseEntity<Long> updateTask(@PathVariable(name = "id") final Long id,
                                         @RequestBody @Valid final TaskDTO taskDTO) {
    taskService.update(id, taskDTO);
    return ResponseEntity.ok(id);
  }

  @DeleteMapping("/{id}")
  @ApiResponse(responseCode = "204")
  @RequireAdmin
  public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") final Long id) {
    taskService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
