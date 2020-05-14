package com.prv.art.tasks.timetracker.exceptions.definition;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.function.Consumer;

public class DefaultErrorInfo {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public  static DefaultErrorInfo  buildError(List<Consumer<DefaultErrorInfo>> functions){
        DefaultErrorInfo err=new DefaultErrorInfo();
        for (Consumer<DefaultErrorInfo> fnc:functions){
            fnc.accept(err);
        }
        return err;
    }
    public int getStatus() {
        return status;
    }
    public String getError() {
        return error;
    }
    public String getMessage() {
        return message;
    }
    public String getPath() {
        return path;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimestamp() {
        return DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
    }
}
