package com.dkit.gd2.johnloane.core;

import java.util.ArrayList;
import java.util.List;

public class TaskDatabase
{
    private List<TaskDTO> tasks;

    public TaskDatabase()
    {
        this.tasks = new ArrayList<>();
    }

    /* This will be operating in threaded environment to need to be thread safe
    Synchronise access to shared memory - use block level synchronization to
    minimise bottlenecks
     */
    public boolean add(TaskDTO t)
    {
        if(!tasks.contains(t))
        {
            synchronized (tasks)
            {
                tasks.add(t);
            }
            return true;
        }
        return false;
    }

    public boolean remove(TaskDTO t)
    {
        synchronized (tasks)
        {
            return tasks.remove(t);
        }
    }

    public List<TaskDTO> getAllTasks()
    {
        List<TaskDTO> duplicateList = new ArrayList<>();
        synchronized (tasks)
        {
            for(TaskDTO t : tasks)
            {
                duplicateList.add(t);
            }
        }
        return duplicateList;
    }


}
