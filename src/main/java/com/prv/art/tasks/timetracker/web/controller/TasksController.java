package com.prv.art.tasks.timetracker.web.controller;

import com.prv.art.tasks.timetracker.exceptions.definition.DataProcessingException;
import com.prv.art.tasks.timetracker.exceptions.definition.DataProcessingExceptionType;
import com.prv.art.tasks.timetracker.persistence.model.TaskBase;
import com.prv.art.tasks.timetracker.services.TaskValidationService;
import com.prv.art.tasks.timetracker.web.dto.TaskCreateDto;
import com.prv.art.tasks.timetracker.web.dto.TaskDto;
import com.prv.art.tasks.timetracker.persistence.model.Task;
import com.prv.art.tasks.timetracker.services.TaskService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/v1.0/tasks")
@Validated
public class TasksController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskValidationService taskValidationService;
    @Autowired
    private ModelMapper modelMapper;

    @Operation(description = "Retrieves Task with all child tasks")
    @GetMapping(value = "/{taskId}",produces = "application/json")
    public ResponseEntity<TaskDto> getTask(@NotNull @PathVariable("taskId") int taskId) {
        return taskService.findById(taskId)
                .map(task -> modelMapper.map(task, TaskDto.class))
                .map(taskDto -> new ResponseEntity<TaskDto>(taskDto, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, DataProcessingExceptionType.TASK_FETCH_NOT_FOUND.getDescription()));
    }

    @Operation(description = "Creates specified Task with child Tasks")
    @PostMapping(value = "/",consumes = "application/json")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskCreateDto newTask) {
        return Stream.of(newTask)
                .peek(taskDto -> validateTaskStructure(taskDto))
                .map(taskDto -> modelMapper.map(taskDto, Task.class))
                .map(task -> taskService.createNew(task))
                .map(task -> modelMapper.map(task, TaskDto.class))
                .map(taskDto -> new ResponseEntity<TaskDto>(taskDto, HttpStatus.OK))
                .findFirst()
                .get();//should always return value
    }

    @Operation(description = "Allows modification of Task structure (child tasks also could be modified).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",description = "'Not all child tasks are complete', 'Total time reported in child tasks is greater than parent task time', 'Task 'id' in the path does not match 'id' specified in the payload'")
    })
    @PutMapping(value = "/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto updateTask, @NotNull @PathVariable("taskId") int taskId) {
        return Stream.of(updateTask)
                .peek(taskDto -> validateRootEntityId(taskDto, taskId))
                .peek(taskDto -> validateTaskStructure(taskDto))
                .map(taskDto -> modelMapper.map(taskDto, Task.class))
                .map(task -> taskService.updateTask(task))
                .map(task -> modelMapper.map(task, TaskDto.class))
                .map(taskDto -> new ResponseEntity<TaskDto>(taskDto, HttpStatus.OK))
                .findFirst().get();
    }


    @Operation(description = "Allows deletion of the completed Tasks, Tasks without children and Tasks without completed parents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",description = "'Cannot delete task due to completed parent task', 'Specified task was not found'")
    })
    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<String> deleteTask(@NotNull @PathVariable("taskId") int taskId) {

        try {
            //TO-DO time deduction is not handled for scenario, when
            taskService.deleteTask(taskId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (DataProcessingException dex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, dex.getMessage());
        }

    }
    private void validateRootEntityId(TaskDto updateTask, Integer pathEntityId) {
        //this is required to support update operation with id and without id in the PUT payload for the parent entity
        //validation required, so that id in the PATH and ID in the payload match if present
        if (updateTask.getId() != null && updateTask.getId() != pathEntityId) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    DataProcessingExceptionType.TASK_UPDATE_ID_MISMATCH.getDescription()
            );
        }else if (updateTask.getId()==null){//set entity id from the PATH, CREATE from the PUT is not supported
            updateTask.setId(pathEntityId);
        }
    }

    private void validateTaskStructure(TaskBase t) {
        taskValidationService
                .validateTaskStructure(t)
                .ifPresent(r -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            r.stream()
                                    .map(err -> err.getDescription())
                                    .distinct()
                                    .collect(Collectors.joining(", ")
                                    ));
                });
    }

}
