package com.dkit.gd2.johnloane.core;

import java.util.ArrayList;
import java.util.List;

public class TaskDatabase
{
    private List<Task> tasks;

    public TaskDatabase()
    {
        this.tasks = new ArrayList<>();
    }

    /* This will be operating in threaded environment to need to be thread safe
    Synchronise access to shared memory - use block level synchronization to
    minimise bottlenecks
     */
    public boolean add(Task t)
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

    public boolean remove(Task t)
    {
        synchronized (tasks)
        {
            return tasks.remove(t);
        }
    }

    public List<Task> getAllTasks()
    {
        List<Task> duplicateList = new ArrayList<>();
        synchronized (tasks)
        {
            for(Task t : tasks)
            {
                duplicateList.add(t);
            }
        }
        return duplicateList;
    }


}
