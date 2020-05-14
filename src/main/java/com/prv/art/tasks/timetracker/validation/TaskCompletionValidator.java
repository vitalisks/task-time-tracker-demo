package com.prv.art.tasks.timetracker.validation;

import com.prv.art.tasks.timetracker.exceptions.definition.DataProcessingExceptionType;
import com.prv.art.tasks.timetracker.persistence.model.TaskBase;

public class TaskCompletionValidator implements ITaskValidator {
    private boolean allChildrenCompleted=true;
    @Override
    public void checkSingleTask(TaskBase task, TaskBase childTask) {
        allChildrenCompleted= allChildrenCompleted && (!task.isFinished() || childTask.isFinished());
    }

    @Override
    public DataProcessingExceptionType validationResult() {
        return allChildrenCompleted?null:DataProcessingExceptionType.TASK_UPDATE_HAS_INCOMPLETE_CHILDREN;
    }
}
