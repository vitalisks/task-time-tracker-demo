package com.prv.art.tasks.timetracker.persistence.repository;
import com.prv.art.tasks.timetracker.persistence.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Query("select count(*) from Task t where t.isFinished=?2 and t.parentTask.id=?1")
    int countSubTasksByCompletionStatus(int taskId,boolean isFinished);

}
