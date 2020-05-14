package com.prv.art.tasks.timetracker.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.prv.art.tasks.timetracker.persistence.model.TaskBase;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto extends TaskBase {

    private Integer id;

    @Schema(description = "Name of the Task")
    private String taskName;

    @Schema(description = "Name of the Task Group")
    private String taskGroup;

    @Schema(description = "Name to who Task to assign")
    private String taskAssignee;

    @Schema(description = "Total time spent on Task execution. Updatable value.")
    private Integer timeSpent;
    @Schema(description = "Status, which identifies Task completion")
    private boolean isFinished;

    @Schema(description = "List of the child Tasks")
    private List<TaskDto> subTasks;
    private ParentTaskDto parentTask;

    @Override
    public Integer getTimeSpent() {
        return timeSpent;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public List<TaskDto> getSubTasks() {
        return subTasks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    public String getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(String taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setSubTasks(List<TaskDto> subTasks) {
        this.subTasks = subTasks;
    }

    public ParentTaskDto getParentTask() {
        return parentTask;
    }

    public void setParentTask(ParentTaskDto parentTask) {
        this.parentTask = parentTask;
    }
}
