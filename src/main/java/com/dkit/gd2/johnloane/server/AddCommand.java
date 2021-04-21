package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.TaskDTO;
import com.dkit.gd2.johnloane.core.TaskDatabase;
import com.dkit.gd2.johnloane.core.TaskService;

import java.util.Date;

public class AddCommand implements ICommand
{
    //add%%New Task%%John%%123456787
    @Override
    public String generateResponse(String[] components, TaskDatabase taskList)
    {
        String response = null;
        if(components.length == 4)
        {
            try
            {
                String taskName = components[1];
                String taskOwner = components[2];
                long deadline = Long.parseLong(components[3]);

                TaskDTO newTask = new TaskDTO(taskName, taskOwner, new Date(deadline));
                //boolean added = taskList.add(newTask);
                ITaskDAOInterface taskDAO = new MySqlTaskDAO();
                boolean added = taskDAO.addTask(newTask);
                if(added)
                {
                    response = TaskService.SUCCESSFUL_ADD;
                }
                else
                {
                    response = TaskService.FAILED_ADD;
                }
            }
            catch(NumberFormatException | DAOException e)
            {
                response = TaskService.FAILED_ADD;
                System.out.println("Could not convert deadline to long " + e.getMessage());
            }
        }
        return response;
    }
}
