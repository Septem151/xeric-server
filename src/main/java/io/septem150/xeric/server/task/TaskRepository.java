package io.septem150.xeric.server.task;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByNameIgnoreCase(String name);

}
