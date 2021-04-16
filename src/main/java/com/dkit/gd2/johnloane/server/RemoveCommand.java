package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.Task;
import com.dkit.gd2.johnloane.core.TaskDatabase;
import com.dkit.gd2.johnloane.core.TaskService;

public class RemoveCommand implements ICommand
{
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response =  null;
        if(components.length == 2)
        {
            String taskName = components[1];
            Task taskToBeRemoved = new Task(taskName);
            boolean removed = taskList.remove(taskToBeRemoved);
            if(removed)
            {
                response = TaskService.SUCCESSFUL_REMOVE;
            }
            else
            {
                response = TaskService.FAILED_REMOVE;
            }
        }
        return response;
    }
}
