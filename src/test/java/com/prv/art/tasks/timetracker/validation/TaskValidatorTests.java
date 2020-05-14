package com.prv.art.tasks.timetracker.validation;

import com.prv.art.tasks.timetracker.exceptions.definition.DataProcessingExceptionType;
import com.prv.art.tasks.timetracker.web.dto.TaskDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskValidatorTests {

    @Test
    public void should_detect_incomplete_child_if_parent_finished(){
        TaskCompletionValidator tv=new TaskCompletionValidator();
        TaskDto tdto=new TaskDto();
        TaskDto child=new TaskDto();
        tdto.setFinished(true);
        child.setFinished(false);
        tv.checkSingleTask(tdto,child);
        assertThat(tv.validationResult()).isEqualTo(DataProcessingExceptionType.TASK_UPDATE_HAS_INCOMPLETE_CHILDREN);
    }

    @Test
    public void should_detect_pass_child_if_parent_finished(){
        TaskCompletionValidator tv=new TaskCompletionValidator();
        TaskDto tdto=new TaskDto();
        TaskDto child=new TaskDto();
        tdto.setFinished(true);
        child.setFinished(true);
        tv.checkSingleTask(tdto,child);
        assertThat(tv.validationResult()).isNull();
    }
}
