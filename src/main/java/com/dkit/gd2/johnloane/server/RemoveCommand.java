package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.TaskDTO;
import com.dkit.gd2.johnloane.core.TaskDatabase;
import com.dkit.gd2.johnloane.core.TaskService;

public class RemoveCommand implements ICommand
{
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response =  null;
        try
        {
            if (components.length == 2)
            {
                String taskName = components[1];
                TaskDTO taskToBeRemoved = new TaskDTO(taskName);
                //boolean removed = taskList.remove(taskToBeRemoved);
                ITaskDAOInterface taskDAO = new MySqlTaskDAO();
                boolean removed = taskDAO.removeTask(taskToBeRemoved);
                if (removed)
                {
                    response = TaskService.SUCCESSFUL_REMOVE;
                } else
                {
                    response = TaskService.FAILED_REMOVE;
                }
            }
        }
        catch (DAOException e)
        {
            System.out.println("removeTask() db problem in RemoveCommand");
        }
        return response;
    }
}
