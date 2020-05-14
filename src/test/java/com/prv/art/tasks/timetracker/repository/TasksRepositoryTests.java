package com.prv.art.tasks.timetracker.repository;


import com.prv.art.tasks.timetracker.helpers.TaskCreator;
import com.prv.art.tasks.timetracker.persistence.model.Task;
import com.prv.art.tasks.timetracker.persistence.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TasksRepositoryTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TaskRepository repository;

    @Test
    public void should_create_simple_task_and_assign_id() {
        Task t=new Task();
        t=repository.save(t);
        assertThat(t.getId()).isGreaterThan(0);
    }
    @Test
    public void should_create_simple_task_with_children() {
        final Task t=new Task();
        List<Task> tl= Arrays.asList(
                TaskCreator.clone(t),
                TaskCreator.clone(t),
                TaskCreator.clone(t)
        ).stream()
                .peek(ct->ct.setParent(t))//set parent task for child
                .collect(Collectors.toList());
        t.setSubTasks(tl);

        Task savedTask=repository.save(t);
        assertThat(Optional.of(savedTask.getSubTasks()).orElse(new ArrayList<>())
                .stream()
                .limit(1)
                .peek(task->assertThat(task.getId()).isGreaterThan(0))
                .peek(task->assertThat(task.getParent()).isNotNull())
                .count()).isEqualTo(1);
    }
    @Test
    public void should_count_by_parent_and_status() {

        final Task t=new Task();
        List<Task> tl= Arrays.asList(
                //create 3 subtasks
                TaskCreator.clone(t),
                //for one set isFinished to true
                Stream.of(TaskCreator.clone(t)).peek(ct->ct.setFinished(true)).findFirst().get(),
                TaskCreator.clone(t)
        ).stream()
                .peek(ct->ct.setParent(t))//set parent task for child
                .collect(Collectors.toList());
        t.setSubTasks(tl);

        Task savedTask=repository.save(t);
        entityManager.clear();
        Task refreshed=repository.findById(savedTask.getId()).get();

        assertThat(repository.countSubTasksByCompletionStatus(savedTask.getId(),true)).isEqualTo(1);
        assertThat(repository.countSubTasksByCompletionStatus(savedTask.getId(),false)).isEqualTo(2);
    }



}