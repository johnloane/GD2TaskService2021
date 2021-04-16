package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.TaskService;
//Demonstrates the Factory pattern - this factory creates a command of the requested type

public class CommandFactory
{
    public static ICommand createCommand(String requestCommand)
    {
        ICommand c = null;
        switch(requestCommand)
        {
            case TaskService.ADD_COMMAND:
                c = new AddCommand();
                break;
            case TaskService.REMOVE_COMMAND:
                c = new RemoveCommand();
                break;
            case TaskService.VIEW_COMMAND:
                c = new ViewCommand();
                break;
            case TaskService.EXIT_COMMAND:
                c = new ExitCommand();
                break;
        }
        return c;
    }
}
