package com.prv.art.tasks.timetracker.mapper;

import com.prv.art.tasks.timetracker.persistence.model.Task;
import com.prv.art.tasks.timetracker.util.mappers.TaskMapper;
import com.prv.art.tasks.timetracker.web.dto.TaskCreateDto;
import com.prv.art.tasks.timetracker.web.dto.TaskDto;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TaskMapperTests {

    private ModelMapper modelMapper=TaskMapper.getInstance();


    @Test
    void should_map_without_subtasks(){
        TaskCreateDto tdto=new TaskCreateDto();
        tdto.setFinished(true);
        tdto.setTaskAssignee("assignee");
        tdto.setTaskGroup("group");
        tdto.setTaskName("name");
        tdto.setTimeSpent(10);

        Task t=modelMapper.map(tdto, Task.class);
        assertThat(t.isFinished()).isEqualTo(tdto.isFinished());
        assertThat(t.getTaskAssignee()).isEqualTo(tdto.getTaskAssignee());
        assertThat(t.getTaskName()).isEqualTo(tdto.getTaskName());
        assertThat(t.getTimeSpent()).isEqualTo(tdto.getTimeSpent());
        assertThat(t.getTaskGroup()).isEqualTo(tdto.getTaskGroup());
    }
    @Test
    public void should_map_from_createdto_to_task(){
        TaskCreateDto tdto=new TaskCreateDto();
        tdto.setFinished(true);
        tdto.setTaskAssignee("assignee");
        tdto.setTaskGroup("group");
        tdto.setTaskName("name");
        tdto.setTimeSpent(10);

        TaskCreateDto childTask=new TaskCreateDto();
        childTask.setFinished(false);
        childTask.setTaskAssignee("assignee1");
        childTask.setTaskGroup("group2");
        childTask.setTaskName("name3");
        childTask.setTimeSpent(9);

        tdto.setSubTasks(Collections.singletonList(childTask));
        Task t=modelMapper.map(tdto, Task.class);

        assertThat(t.isFinished()).isEqualTo(tdto.isFinished());
        assertThat(t.getTaskAssignee()).isEqualTo(tdto.getTaskAssignee());
        assertThat(t.getTaskName()).isEqualTo(tdto.getTaskName());
        assertThat(t.getTimeSpent()).isEqualTo(tdto.getTimeSpent());
        assertThat(t.getTaskGroup()).isEqualTo(tdto.getTaskGroup());
        for (Task child:t.getSubTasks()){
            assertThat(child.isFinished()).isEqualTo(childTask.isFinished());
            assertThat(child.getTaskAssignee()).isEqualTo(childTask.getTaskAssignee());
            assertThat(child.getTaskName()).isEqualTo(childTask.getTaskName());
            assertThat(child.getTimeSpent()).isEqualTo(childTask.getTimeSpent());
            assertThat(child.getTaskGroup()).isEqualTo(childTask.getTaskGroup());
        }
        assertThat(t.getSubTasks().size()).isEqualTo(tdto.getSubTasks().size());


    }

    @Test
    public void should_map_from_updatedto_to_task(){
        TaskDto tdto=new TaskDto();
        tdto.setId(1);
        tdto.setFinished(true);
        tdto.setTaskAssignee("assignee");
        tdto.setTaskGroup("group");
        tdto.setTaskName("name");
        tdto.setTimeSpent(10);

        TaskDto childTask=new TaskDto();
        childTask.setFinished(false);
        childTask.setId(2);
        childTask.setTaskAssignee("assignee1");
        childTask.setTaskGroup("group2");
        childTask.setTaskName("name3");
        childTask.setTimeSpent(9);

        tdto.setSubTasks(Collections.singletonList(childTask));
        Task t=modelMapper.map(tdto, Task.class);

        assertThat(t.getId()).isEqualTo(tdto.getId());
        assertThat(t.isFinished()).isEqualTo(tdto.isFinished());
        assertThat(t.getTaskAssignee()).isEqualTo(tdto.getTaskAssignee());
        assertThat(t.getTaskName()).isEqualTo(tdto.getTaskName());
        assertThat(t.getTimeSpent()).isEqualTo(tdto.getTimeSpent());
        assertThat(t.getTaskGroup()).isEqualTo(tdto.getTaskGroup());
        for (Task child:t.getSubTasks()){
            assertThat(child.getId()).isEqualTo(childTask.getId());
            assertThat(child.isFinished()).isEqualTo(childTask.isFinished());
            assertThat(child.getTaskAssignee()).isEqualTo(childTask.getTaskAssignee());
            assertThat(child.getTaskName()).isEqualTo(childTask.getTaskName());
            assertThat(child.getTimeSpent()).isEqualTo(childTask.getTimeSpent());
            assertThat(child.getTaskGroup()).isEqualTo(childTask.getTaskGroup());
        }
        assertThat(t.getSubTasks().size()).isEqualTo(tdto.getSubTasks().size());

        TaskDto reverseMap=modelMapper.map(t,TaskDto.class);
        assertThat(t.getId()).isEqualTo(reverseMap.getId());
        assertThat(t.isFinished()).isEqualTo(reverseMap.isFinished());
        assertThat(t.getTaskAssignee()).isEqualTo(reverseMap.getTaskAssignee());
        assertThat(t.getTaskName()).isEqualTo(reverseMap.getTaskName());
        assertThat(t.getTimeSpent()).isEqualTo(reverseMap.getTimeSpent());
        assertThat(t.getTaskGroup()).isEqualTo(reverseMap.getTaskGroup());
        for (Task child:t.getSubTasks()){
            assertThat(child.getId()).isEqualTo(childTask.getId());
            assertThat(child.isFinished()).isEqualTo(childTask.isFinished());
            assertThat(child.getTaskAssignee()).isEqualTo(childTask.getTaskAssignee());
            assertThat(child.getTaskName()).isEqualTo(childTask.getTaskName());
            assertThat(child.getTimeSpent()).isEqualTo(childTask.getTimeSpent());
            assertThat(child.getTaskGroup()).isEqualTo(childTask.getTaskGroup());
        }
        assertThat(t.getSubTasks().size()).isEqualTo(tdto.getSubTasks().size());
    }
}
