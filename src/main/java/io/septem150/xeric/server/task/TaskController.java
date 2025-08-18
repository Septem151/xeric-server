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
package io.septem150.xeric.server.task;

import io.septem150.xeric.server.XericServerApplication;
import io.septem150.xeric.server.util.RequireAdmin;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
    value = "/" + XericServerApplication.API_VERSION + "/tasks",
    produces = MediaType.APPLICATION_JSON_VALUE)
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
  public ResponseEntity<Long> updateTask(
      @PathVariable(name = "id") final Long id, @RequestBody @Valid final TaskDTO taskDTO) {
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
