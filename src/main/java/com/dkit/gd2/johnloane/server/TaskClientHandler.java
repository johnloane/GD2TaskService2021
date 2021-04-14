package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.TaskDatabase;
import com.dkit.gd2.johnloane.core.TaskService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
                }
                if(response != null)
                {
                    clientOutput.println(response);
                }
            }


        }
        catch (IOException e)
        {
            //e.printStackTrace();
        }
    }
}
