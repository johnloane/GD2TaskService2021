package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.TaskDTO;
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
        try
        {
            if (components.length == 1)
            {
                //List<TaskDTO> tasks = taskList.getAllTasks();
                ITaskDAOInterface taskDAO = new MySqlTaskDAO();
                List<TaskDTO> tasks = taskDAO.findAllTasks();
                response = TaskService.flattenTaskList(tasks);
                if (response == null)
                {
                    response = "DummyTask%%No Owner%%" + new Date().getTime();
                }
            }
        }
        catch (DAOException e)
        {
            System.out.println("Problem with findAllTasks() in ViewCommand " + e.getMessage());
        }

        return response;
    }
}
