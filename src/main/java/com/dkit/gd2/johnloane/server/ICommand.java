package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.TaskDatabase;

public interface ICommand
{
    public String generateResponse(String[] components, TaskDatabase taskList);
}
