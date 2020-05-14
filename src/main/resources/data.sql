insert into task (id, is_finished, task_assignee, task_group, task_name, time_spent) values (1,false, 'User1','group', 'Task 1', 10);
insert into task (id, is_finished, task_assignee, task_group, task_name, time_spent) values (2,false, 'User2','group', 'Task 2', 10);
insert into task (id, is_finished, task_assignee, task_group, task_name, time_spent) values (3,true, 'User3','group', 'Task 3', 10);
insert into task (id, is_finished, task_assignee, task_group, task_name, time_spent) values (4,false, 'User2','group', 'Task 5', 10);
--
----childs tasks
insert into task (id, is_finished, task_assignee, task_group, task_name, time_spent, parent_task_id) values (5,false, 'User2','group', 'Task 6', 10, 2);
insert into task (id, is_finished, task_assignee, task_group, task_name, time_spent, parent_task_id) values (6,false, 'User2','group', 'Task 7', 10, 5);
--

alter sequence TASKS_SEQ restart with 10;