package com.prv.art.tasks.timetracker.validation;

import java.util.Arrays;
import java.util.List;

public class TaskValidatorFactory  {

    public static List<ITaskValidator> getTaskStructureValidators() {
        return Arrays.asList(
                new TaskCompletionValidator(),
                new TaskTimeValidator()
        );
    }
}
