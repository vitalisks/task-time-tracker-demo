package com.prv.art.tasks.timetracker.services;

import com.prv.art.tasks.timetracker.exceptions.definition.DataProcessingExceptionType;
import com.prv.art.tasks.timetracker.persistence.model.TaskBase;
import com.prv.art.tasks.timetracker.validation.ITaskValidator;
import com.prv.art.tasks.timetracker.validation.TaskValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskValidationService {
    public Optional<List<DataProcessingExceptionType>> validateTaskStructure(TaskBase t){
        return validateChildTasks(t);
    }

    private Optional<List<DataProcessingExceptionType>> validateChildTasks(TaskBase t){
            if (t.getSubTasks()!=null){
                List<ITaskValidator> tv= TaskValidatorFactory.getTaskStructureValidators();
                List<DataProcessingExceptionType> result=new ArrayList<>();

                for (TaskBase ct :  t.getSubTasks()){
                    for (ITaskValidator ctv: tv){
                        ctv.checkSingleTask(t,ct);
                    }
                    Optional<List<DataProcessingExceptionType>> childValidation=this.validateChildTasks(ct);
                    result.addAll(childValidation.orElse(new ArrayList<>()));
                }
                return Optional.of(
                        Stream.concat(
                                tv.stream()
                                        .map(ctv->ctv.validationResult())
                                        .filter(r->r!=null),
                                result.stream()
                                )
                                .collect(Collectors.toList())
                        )
                        //return null value, because Optional do not work on empty lists
                        .map(r->r.size()>0?r:null);
            }
        return Optional.empty();
    }

}
