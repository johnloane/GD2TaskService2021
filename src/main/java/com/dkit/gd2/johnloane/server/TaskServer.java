package com.dkit.gd2.johnloane.server;

/* A server that uses thread per client to offer the Task Service*/

import com.dkit.gd2.johnloane.core.TaskDatabase;
import com.dkit.gd2.johnloane.core.TaskService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskServer
{
    public static void main(String[] args)
    {
        TaskDatabase taskList = new TaskDatabase();

        try
        {
            ServerSocket connectionSocket = new ServerSocket(TaskService.PORT_NUM);
            while(true)
            {
                Socket clientSocket = connectionSocket.accept();
                TaskClientHandler clientHandler = new TaskClientHandler(clientSocket, taskList);
                Thread worker = new Thread(clientHandler);
                worker.start();
            }

        } catch (IOException e)
        {
            System.out.println("Problem setting up the connection socket " + e.getMessage());
        }
    }
}
