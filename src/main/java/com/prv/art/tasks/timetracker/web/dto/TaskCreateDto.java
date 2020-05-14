package com.prv.art.tasks.timetracker.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.prv.art.tasks.timetracker.persistence.model.TaskBase;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCreateDto extends TaskBase {

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
    private List<TaskCreateDto> subTasks;

    @Override
    public Integer getTimeSpent() {
        return timeSpent;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public List<TaskCreateDto> getSubTasks() {
        return subTasks;
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

    public void setSubTasks(List<TaskCreateDto> subTasks) {
        this.subTasks = subTasks;
    }
}
