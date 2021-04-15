package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.Task;
import com.dkit.gd2.johnloane.core.TaskDatabase;
import com.dkit.gd2.johnloane.core.TaskService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class TaskClientHandler implements Runnable
{
    private Socket clientSocket;
    private TaskDatabase taskList;

    public TaskClientHandler(Socket clientSocket, TaskDatabase taskList)
    {
        this.clientSocket = clientSocket;
        this.taskList = taskList;
    }

    @Override
    public void run()
    {
        //Step 1: Open the lines of communication
        //Decorator pattern
        try
        {
            Scanner clientInput = new Scanner(clientSocket.getInputStream());
            //Instead of Step 1: clientOutput.println("Testing the socket")
            //           Step 2: clientOutput.flush();
            //autoFlush = true means we only need step 1
            PrintWriter clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            //Step 2: Set up repeated exchanges
            boolean sessionActive = true;
            while(sessionActive)
            {
                //Protocol logic
                //Note the line below is blocking - program execution waits here
                //until we get a request
                String request = clientInput.nextLine();
                //The request will look like add%%New Task%%John%%67789897989
                //                           remove%%New Task
                //                           viewAll
                String[] components = request.split(TaskService.BREAKING_CHARACTER);
                String response = null;
                switch(components[0])
                {
                    case TaskService.ADD_COMMAND:
                        response = generateAddResponse(components);
                        break;
                    case TaskService.REMOVE_COMMAND:
                        response = generateRemoveResponse(components);
                        break;
                    case TaskService.VIEW_COMMAND:
                        response = generateViewAllResponse(components);
                        break;
                    case TaskService.EXIT_COMMAND:
                        response = TaskService.SIGN_OFF;
                        sessionActive = false;
                        break;
                }
                if(response != null)
                {
                    clientOutput.println(response);
                }
            }
            clientSocket.close();
        }
        catch (IOException e)
        {
            System.out.println("Problem setting up communication channels " + e.getMessage());
        }
    }

    //remove%%Get outside
    private String generateRemoveResponse(String[] components)
    {
        String response = null;
        if(components.length == 2)
        {
            String taskName = components[1];
            Task taskToBeRemoved = new Task(taskName);
            //This relies on the equals method in the Task class
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

    //add%%Get outside%%John%%1234545667
    //There are two possible responses -> Success or fail
    private String generateAddResponse(String[] components)
    {
        String response = null;
        if(components.length == 4)
        {
            try
            {
                String taskName = components[1];
                String taskOwner = components[2];
                long deadline = Long.parseLong(components[3]);

                Task newTask = new Task(taskName, taskOwner, new Date(deadline));
                boolean added = taskList.add(newTask);
                if(added)
                {
                    response = TaskService.SUCCESSFUL_ADD;
                }
                else
                {
                    response = TaskService.FAILED_ADD;
                }
            }
            catch(NumberFormatException e)
            {
                response = TaskService.FAILED_ADD;
                System.out.println(e.getMessage());
            }
        }
        return response;
    }
}
