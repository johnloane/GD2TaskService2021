package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.TaskDTO;

import java.util.List;

public interface ITaskDAOInterface
{
    public List<TaskDTO>  findAllTasks() throws DAOException;
    public boolean addTask(TaskDTO newTask) throws DAOException;
    public boolean removeTask(TaskDTO taskToRemove) throws DAOException;
    public boolean checkIfTaskExists(TaskDTO task) throws DAOException;
}
