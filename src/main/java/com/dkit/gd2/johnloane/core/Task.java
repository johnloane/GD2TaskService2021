package com.dkit.gd2.johnloane.core;

import java.util.Date;
import java.util.Objects;

public class Task
{
    private String taskName;
    private String taskOwner;
    private Date deadline;

    public Task(String taskName, String taskOwner, Date deadline)
    {
        this.taskName = taskName;
        this.taskOwner = taskOwner;
        this.deadline = deadline;
    }

    public Task(String taskName, String taskOwner)
    {
        this(taskName, taskOwner, null);
    }

    public Task(String taskName)
    {
        this(taskName, "No one", null);
    }

    public String getTaskName()
    {
        return taskName;
    }

    public String getTaskOwner()
    {
        return taskOwner;
    }

    public Date getDeadline()
    {
        return deadline;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskName, task.taskName);
    }
    //If two object are equal they must have the same hashcode
    @Override
    public int hashCode()
    {
        return Objects.hash(taskName);
    }

    @Override
    public String toString()
    {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", taskOwner='" + taskOwner + '\'' +
                ", deadline=" + deadline +
                '}';
    }

    public String format()
    {
        return this.taskName + TaskService.BREAKING_CHARACTER + taskOwner + TaskService.BREAKING_CHARACTER + deadline.getTime();
    }
}
