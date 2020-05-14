package com.prv.art.tasks.timetracker.exceptions.definition;

public enum DataProcessingExceptionType {
    TASK_UPDATE_HAS_INCOMPLETE_CHILDREN("Not all child tasks are complete"),
    TASK_UPDATE_CHILD_TIME_GREATER("Total time reported in child tasks is greater than parent task time"),
    TASK_FETCH_NOT_FOUND("Specified task was not found"),
    TASK_DELETE_PARENT_TASK_IS_COMPLETE("Cannot delete task due to completed parent task"),
    TASK_UPDATE_ID_MISMATCH("Task 'id' in the path does not match 'id' specified in the payload");
    private final String description;

    DataProcessingExceptionType(String description){
        this.description=description;
    }

    public String getDescription() {
        return description;
    }
}
