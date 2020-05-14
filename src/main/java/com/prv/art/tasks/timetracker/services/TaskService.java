package com.prv.art.tasks.timetracker.services;

import com.prv.art.tasks.timetracker.exceptions.definition.DataProcessingException;
import com.prv.art.tasks.timetracker.exceptions.definition.DataProcessingExceptionType;
import com.prv.art.tasks.timetracker.persistence.model.Task;
import com.prv.art.tasks.timetracker.persistence.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TaskRepository taskRepository;
    public Optional<Task> findById(int id){

        return taskRepository.findById(id);
    }

    private Task syncParents(Task t){
        //mapping of parent is required to properly handle insertion of tasks without assigned parent
        if (t.getSubTasks()!=null){
            for (Task ct: t.getSubTasks()){
                if (ct.getParent()==null){
                    ct.setParent(t);
                }
                syncParents(ct);
            }
        }
        return t;
    }
    @Transactional
    public Task createNew(Task task){
        return taskRepository.save(syncParents(task));
    }

    @Transactional
    public Task updateTask(Task task){
        return taskRepository.save(syncParents(task));
    }

    public void deleteTask(int id) throws DataProcessingException {

        Task t=taskRepository
                .findById(id)
                .orElseThrow(()-> new DataProcessingException(DataProcessingExceptionType.TASK_FETCH_NOT_FOUND));
        //validate parent task and child task state
        if (t.isFinished()) throw new DataProcessingException(DataProcessingExceptionType.TASK_DELETE_PARENT_TASK_IS_COMPLETE);
        if (taskRepository.countSubTasksByCompletionStatus(id,false)>0){
            throw new DataProcessingException(DataProcessingExceptionType.TASK_UPDATE_HAS_INCOMPLETE_CHILDREN);
        }
        taskRepository.deleteById(id);
    }
}
