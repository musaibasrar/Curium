package org.ideoholic.curium.model.task.dao;

import org.ideoholic.curium.model.task.dto.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
