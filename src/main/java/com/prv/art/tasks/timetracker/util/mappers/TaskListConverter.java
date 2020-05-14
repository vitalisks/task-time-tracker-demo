package com.prv.art.tasks.timetracker.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.util.List;
import java.util.stream.Collectors;

public class TaskListConverter implements Converter<List<Object>, List<Object>> {
    private ModelMapper mapper;
    public static TaskListConverter getInstance(ModelMapper mapper){
        TaskListConverter tc=new TaskListConverter();
        tc.setMapper(mapper);
        return tc;
    }
    @Override
    public List<Object> convert(MappingContext<List<Object>, List<Object>> context) {
        if (context.getSource()==null){
            return null;
        }else{
            return context.getSource()
                    .stream()
                    //this mapping approach will work only if parent type is matching target type in the list
                    //so adding additional children will require change
                    .map(task->mapper.map(task,context.getParent().getDestinationType()))
                    .collect(Collectors.toList());
        }
    }

    void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
}
