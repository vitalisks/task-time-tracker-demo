package com.prv.art.tasks.timetracker.exceptions.handler;

import com.prv.art.tasks.timetracker.exceptions.definition.EmptyErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //shield all internal errors
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public void handleAll(Exception e) {
        //TO-DO: additional logging logic to be applied
    }

    //pass through only BAD_REQUEST exceptions generated for operations
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    EmptyErrorInfo
    handleApplicationError(HttpServletRequest req,Exception ex){
        //return empty object, so that default error info propagated
        return new EmptyErrorInfo();
    }

}