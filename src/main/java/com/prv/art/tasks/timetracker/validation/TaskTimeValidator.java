package com.prv.art.tasks.timetracker.validation;

import com.prv.art.tasks.timetracker.exceptions.definition.DataProcessingExceptionType;
import com.prv.art.tasks.timetracker.persistence.model.TaskBase;

import java.util.Optional;

public class TaskTimeValidator implements  ITaskValidator {
    private int totalTime=0;
    private boolean isOvertime=false;

    @Override
    public void checkSingleTask(TaskBase task, TaskBase childTask) {
        if (task.isFinished()){
            totalTime+= Optional.ofNullable(childTask.getTimeSpent()).orElse(0);
            isOvertime = Optional.ofNullable(task.getTimeSpent()).orElse(0)<totalTime;
        }
    }

    @Override
    public DataProcessingExceptionType validationResult() {
        return isOvertime?(DataProcessingExceptionType.TASK_UPDATE_CHILD_TIME_GREATER):null;
    }
}
