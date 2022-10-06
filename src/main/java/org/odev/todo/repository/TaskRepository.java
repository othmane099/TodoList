package org.odev.todo.repository;

import org.odev.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByIdAsc();

    List<Task> findAllByIsDoneFalse();

    List<Task> findAllByIsDoneTrue();


}
