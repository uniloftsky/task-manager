package com.uniloftsky.springframework.taskmanager.data.repositories;

import com.uniloftsky.springframework.taskmanager.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    Set<Task> findAllByDate(LocalDate localDate);

}
