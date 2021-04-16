package com.dkit.johnloane.client;

// Clear Client - can be used to test any server

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter the hostname on which your service is running: ");
        String hostname = keyboard.next();
        System.out.println("Please enter the port number on which your service is running");
        //User enters 50017\n
        int port = keyboard.nextInt();
        keyboard.nextLine();

        try
        {
            Socket dataSocket = new Socket(hostname, port);
            Scanner serverIn = new Scanner(dataSocket.getInputStream());
            PrintWriter serverOut = new PrintWriter(dataSocket.getOutputStream(), true);

            boolean keepRunning = true;
            while(keepRunning)
            {
                System.out.println("Please enter the command to be sent: ");
                //add%%New Task%%John%%
                String toBeSent = keyboard.nextLine();
                serverOut.println(toBeSent);

                String response = serverIn.nextLine();
                System.out.println("Response: " + response);

                System.out.println("Do you wish to contine? (-1 to end, any other key to continue");

                //p/n
                String choice = keyboard.nextLine();
                if(choice.equals("-1"))
                {
                    keepRunning = false;
                }
            }
            System.out.println("Thank you for using the TaskManager Client");

        }
        catch (UnknownHostException e)
        {
            System.out.println("Could not connect to Server " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("Problem setting up the streams or connecting to server. Check the server is running... " + e.getMessage());
        }
    }
}
