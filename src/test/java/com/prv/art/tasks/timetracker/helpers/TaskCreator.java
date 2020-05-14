package com.prv.art.tasks.timetracker.helpers;

import com.prv.art.tasks.timetracker.persistence.model.Task;

public class TaskCreator {
    public static Task clone(Task p){
        Task t=new Task();
        t.setFinished(p.isFinished());
        t.setTaskAssignee(p.getTaskAssignee());
        t.setTaskName(p.getTaskName());
        p.setTimeSpent(p.getTimeSpent());
        p.setTaskGroup(p.getTaskGroup());
        return t;
    }

}
