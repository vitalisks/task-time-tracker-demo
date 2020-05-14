package com.prv.art.tasks.timetracker.persistence.model;

import java.util.List;

public abstract class TaskBase {

    public abstract Integer getTimeSpent();

    public abstract boolean isFinished();

    public abstract List<? extends TaskBase> getSubTasks();
}
