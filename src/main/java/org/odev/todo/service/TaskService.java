package org.odev.todo.service;

import org.odev.todo.model.Task;
import org.odev.todo.model.TaskState;
import org.odev.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(Task task){
        task.setIsDone(false);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAllByOrderByIdAsc();
    }

    public List<Task> getAllFinishedTasks(){
        return taskRepository.findAllByIsDoneTrue();
    }

    public List<Task> getAllNotFinishedTasks(){
        return taskRepository.findAllByIsDoneFalse();
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public Optional<Task> doneTask(Long id){
        Optional<Task> t = taskRepository.findById(id);
        t.ifPresent(task -> {
            task.setIsDone(!task.getIsDone());
            taskRepository.save(t.get());
        });
        return t;
    }
}









