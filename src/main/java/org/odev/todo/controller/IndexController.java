package org.odev.todo.controller;

import org.odev.todo.model.Task;
import org.odev.todo.model.TaskCategory;
import org.odev.todo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IndexController {

    private final TaskService taskService;

    public IndexController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getIndex(Model model, Task task){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskNbr", tasks.size());
        model.addAttribute("category", TaskCategory.ALL);
        model.addAttribute("name", "Baeldung Reader");
        return "index";
    }

    @GetMapping("/finished")
    public String getFinishedTask(Model model, Task task){
        List<Task> tasks = taskService.getAllFinishedTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskNbr", tasks.size());
        model.addAttribute("category", TaskCategory.FINISHED);

        return "index";
    }

    @GetMapping("/notfinished")
    public String getNotFinishedTask(Model model, Task task){
        List<Task> tasks = taskService.getAllNotFinishedTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskNbr", tasks.size());
        model.addAttribute("category", TaskCategory.NOT_FINISHED);

        return "index";
    }


    @PostMapping("/")
    public String postTask(@Valid @ModelAttribute("task") Task task,
                           BindingResult bindingResult){

        if (bindingResult.hasErrors())  return "redirect:/";

        taskService.addTask(task);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam("idTask") Long id,
                             @RequestParam("taskCategory") TaskCategory taskCategory){
        taskService.deleteTask(id);
        return checkTaskCategory(taskCategory);
    }

    @PostMapping("/done")
    public String doneTask(@RequestParam("idTask") Long id,
                           @RequestParam("taskCategory") TaskCategory taskCategory){
        taskService.doneTask(id);
        return checkTaskCategory(taskCategory);
    }


    private String checkTaskCategory(TaskCategory taskCategory){
        if (taskCategory.equals(TaskCategory.ALL))
            return "redirect:/";
        else if (taskCategory.equals(TaskCategory.FINISHED))
            return "redirect:/finished";
        else
            return "redirect:/notfinished";
    }
}
