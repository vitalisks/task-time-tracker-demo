package com.prv.art.tasks.timetracker.exceptions.definition;

public class DataProcessingException extends  RuntimeException  {

    private final String message;

    private final DataProcessingExceptionType exceptionType;
    public DataProcessingException(DataProcessingExceptionType exceptionType){
        this.exceptionType=exceptionType;
        this.message=exceptionType.getDescription();
    }

    public DataProcessingExceptionType getExceptionType() {
        return exceptionType;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
