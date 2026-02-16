package com.kollab.task;

import com.kollab.task.dto.TaskRequestDTO;
import com.kollab.task.dto.TaskResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

//    @GetMapping("/{task-id}")
//    public TaskRequestDTO getTask(
//            @PathVariable("task-id") Integer id
//    ) {
//        return service.retrieveTask(id);
//    }
//
//    @GetMapping
//    public List<TaskRequestDTO> getAllTask () {
//        return service.retrieveAllTask();
//    }
//
    @PostMapping
    public TaskResponseDTO saveTask(
            @RequestBody TaskRequestDTO dto
    ) {
        return service.saveTask(dto);
    }

//    @PutMapping("/{task-id}")
//    public TaskRequestDTO updateTask(
//            @RequestBody TaskRequestDTO dto,
//            @PathVariable("task-id") Integer id
//    ) {
//        return service.updateTask(dto, id);
//    }
//
//    @DeleteMapping("/{task-id}")
//    public void deleteTask(
//            @PathVariable("task-id") Integer id
//    ) {
//        service.deleteTask(id);
//    }
}
