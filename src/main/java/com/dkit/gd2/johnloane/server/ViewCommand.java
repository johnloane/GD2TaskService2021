package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.Task;
import com.dkit.gd2.johnloane.core.TaskDatabase;
import com.dkit.gd2.johnloane.core.TaskService;

import java.util.Date;
import java.util.List;

public class ViewCommand implements ICommand
{
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response =  null;
        if(components.length == 1)
        {
            List<Task> tasks = taskList.getAllTasks();
            response = TaskService.flattenTaskList(tasks);
            if(response == null)
            {
                response = "DummyTask%%No Owner%%"+new Date().getTime();
            }
        }
        return response;
    }
}
