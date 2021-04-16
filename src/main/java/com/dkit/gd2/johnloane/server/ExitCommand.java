package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.TaskDatabase;
import com.dkit.gd2.johnloane.core.TaskService;

public class ExitCommand implements ICommand
{
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response = null;
        if(components.length == 1)
        {
            response = TaskService.SIGN_OFF;
        }
        return response;
    }
}
