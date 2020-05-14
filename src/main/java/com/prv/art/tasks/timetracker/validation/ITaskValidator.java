package com.prv.art.tasks.timetracker.validation;

import com.prv.art.tasks.timetracker.exceptions.definition.DataProcessingExceptionType;
import com.prv.art.tasks.timetracker.persistence.model.TaskBase;

public interface ITaskValidator {
    void checkSingleTask(TaskBase task, TaskBase childTask);
    DataProcessingExceptionType validationResult();
}
