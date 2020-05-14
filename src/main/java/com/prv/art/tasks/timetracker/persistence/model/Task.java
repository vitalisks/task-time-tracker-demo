package com.prv.art.tasks.timetracker.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Task implements Serializable {

    @Id
    @GeneratedValue(generator = "TASKS_SEQ", strategy = GenerationType.AUTO)
    private Integer id;
    private String taskName;
    private String taskGroup;
    private String taskAssignee;
    private Integer timeSpent;
    private boolean isFinished;

    @OneToMany(mappedBy = "parentTask", targetEntity = Task.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> subTasks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_TASK_ID", referencedColumnName = "ID")
    private Task parentTask;



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

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }


    public Task getParent() {
        return parentTask;
    }

    public void setParent(Task parent) {
        this.parentTask = parent;
    }

}
