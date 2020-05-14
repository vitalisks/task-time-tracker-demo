package com.prv.art.tasks.timetracker.validation;

import com.prv.art.tasks.timetracker.exceptions.definition.DataProcessingExceptionType;
import com.prv.art.tasks.timetracker.services.TaskValidationService;
import com.prv.art.tasks.timetracker.web.dto.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TaskValidationServiceTests {
    @Autowired
    private TaskValidationService taskValidationService;

    private TaskDto createBaseStructureWithChildren(){
        TaskDto t=new TaskDto();
        t.setSubTasks(Arrays.asList(
                new TaskDto(),
                new TaskDto()
        ));
        return t;
    }
    private TaskDto createWithEmptyTasks(){
        TaskDto t=createBaseStructureWithChildren();
        return t;
    }
    private TaskDto createFinishedParentTask(){
        TaskDto t=createBaseStructureWithChildren();
        t.setFinished(true);
        t.setTimeSpent(0);
        t.setSubTasks(t.getSubTasks().stream().peek(x->x.setTimeSpent(10)).collect(Collectors.toList()));
        return t;
    }

    private TaskDto createFinishedWithChildren(){
        TaskDto t=createBaseStructureWithChildren();
        t.setFinished(true);
        t.setTimeSpent(20);
        t.setSubTasks(t.getSubTasks().stream()
                .peek(x->x.setTimeSpent(10))
                .peek(x->x.setFinished(true))
                .collect(Collectors.toList()));
        return t;
    }

    @Test
    public void should_pass_base_task_list(){
        assertThat(taskValidationService
                .validateTaskStructure(createWithEmptyTasks())

        ).isEmpty();
    }

    @Test
    public void should_pass_completed_structure(){
        assertThat(taskValidationService
                .validateTaskStructure(createFinishedWithChildren())

        ).isEmpty();
    }

    @Test
    public void should_validate_time(){
        TaskDto t=createFinishedParentTask();
        List<DataProcessingExceptionType> r=taskValidationService
                .validateTaskStructure(createFinishedParentTask())
                .get();
        assertThat(taskValidationService
                .validateTaskStructure(createFinishedParentTask())
                .get()
                .stream()
                .filter(e->e.equals(DataProcessingExceptionType.TASK_UPDATE_CHILD_TIME_GREATER))
                .count()
                ).isEqualTo(1);
    }

    @Test
    public void should_validate_completion(){
        assertThat(taskValidationService
                .validateTaskStructure(createFinishedParentTask())
                .get()
                .stream()
                .filter(e->e.equals(DataProcessingExceptionType.TASK_UPDATE_HAS_INCOMPLETE_CHILDREN))
                .count()
        ).isEqualTo(1);
    }

}
