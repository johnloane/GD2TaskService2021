package com.dkit.gd2.johnloane.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskService
{
    public static final String BREAKING_CHARACTER = "%%";
    public static final int PORT_NUM = 50017;
    public static final String HOSTNAME = "localhost";

    public static final String ADD_COMMAND = "add";
    public static final String SUCCESSFUL_ADD = "ADDED";
    public static final String FAILED_ADD = "FAILED";

    public static final String REMOVE_COMMAND = "remove";
    public static final String SUCCESSFUL_REMOVE = "DELETED";
    public static final String FAILED_REMOVE = "NOT_FOUND";

    public static final String VIEW_COMMAND = "viewAll";
    public static final String TASK_SEPARATOR = "##";

    public static final String EXIT_COMMAND = "exit";
    public static final String SIGN_OFF = "GOODBYE";

    //Finish correcting ca5 del one, John, 88888888888
    //Read masters thesis, John, 999999999999
    //Finish correcting ca5 del one%%John%%8888888888##Read masters thesis%%John%%9999999999

    public static String flattenTaskList(List<Task> taskList)
    {
        if(!taskList.isEmpty())
        {
            String tasks = taskList.get(0).format();
            for(int i=1; i < taskList.size(); i++)
            {
                tasks = tasks + TaskService.TASK_SEPARATOR + taskList.get(i).format();
            }
            return tasks;
        }
        else
        {
            return null;
        }
    }

    //This is the opposite of the previous method
    //takes input that looks like Finish correcting ca5 del one%%John%%8888888888##Read masters thesis%%John%%9999999999
    public static List<Task> recreateTaskList(String tasks)
    {
        List<Task> taskList = new ArrayList<>();
        //Now taskStrings will contain - Finish correcting ca5 del one%%John%%8888888888 and Read masters thesis%%John%%9999999999
        String[] taskStrings = tasks.split(TaskService.TASK_SEPARATOR);
        for (String task : taskStrings)
        {
            //Finish correcting ca5 del one%%John%%8888888888
            //components - Finish correcting ca5 del one, John,8888888888
            String[] components = task.split(TaskService.BREAKING_CHARACTER);
            if (components.length == 3)
            {
                try
                {
                    long deadlineTime = Long.parseLong(components[2]);
                    Date deadline = new Date(deadlineTime);
                    Task t = new Task(components[0], components[1], deadline);
                    taskList.add(t);
                } catch (NumberFormatException e)
                {
                    System.out.println("Deadline information is not a long. Skipping entry");
                }
            }
        }
        return taskList;
    }
}
