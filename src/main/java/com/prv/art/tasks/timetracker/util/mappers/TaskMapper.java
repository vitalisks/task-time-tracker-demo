package com.prv.art.tasks.timetracker.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper{

    public static ModelMapper getInstance(){
        ModelMapper mapper=new ModelMapper();
        TaskMapper tm=new TaskMapper();
        tm.configureSubtaskMapper(mapper);
        return mapper;
    }
    private void configureSubtaskMapper(ModelMapper mapper){
        mapper.addConverter(TaskListConverter.getInstance(mapper));
    }

}
