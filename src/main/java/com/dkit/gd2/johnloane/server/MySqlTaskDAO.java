package com.dkit.gd2.johnloane.server;

import com.dkit.gd2.johnloane.core.TaskDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlTaskDAO extends MySqlDAO implements ITaskDAOInterface
{

    @Override
    public List<TaskDTO> findAllTasks() throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TaskDTO> tasks = new ArrayList<>();

        try
        {
            con = this.getConnection();
            String query = "select * from task";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next())
            {
                String taskName = rs.getString("task_name");
                String taskOwner = rs.getString("task_owner");
                long deadline = rs.getLong("deadline");

                TaskDTO readInTask = new TaskDTO(taskName, taskOwner, new Date(deadline));
                tasks.add(readInTask);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Problem in findAllTasks " + e.getMessage());
        }
        finally
        {
            try
            {
                if(rs != null)
                {
                    rs.close();
                }
                if(ps != null)
                {
                    ps.close();
                }
                if(con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                System.out.println("finalAllTasks finally() block " + e.getMessage());
            }
        }
        return tasks;

    }

    @Override
    public boolean addTask(TaskDTO newTask) throws DAOException
    {
        //Only add if the task does not already exist
        if(!checkIfTaskExists(newTask))
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try
            {
                con = this.getConnection();
                String query = "insert into task values(?, ? ,?)";
                ps = con.prepareStatement(query);
                ps.setString(1, newTask.getTaskName());
                ps.setString(2, newTask.getTaskOwner());
                ps.setLong(3, newTask.getDeadline().getTime());

                ps.executeUpdate();

                return true;

            }
            catch (SQLException e)
            {
                System.out.println("Problem in findAllTasks " + e.getMessage());
            }
            finally
            {
                try
                {
                    if(rs != null)
                    {
                        rs.close();
                    }
                    if(ps != null)
                    {
                        ps.close();
                    }
                    if(con != null)
                    {
                        freeConnection(con);
                    }
                } catch (SQLException e)
                {
                    System.out.println("addTask() finally() block " + e.getMessage());
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeTask(TaskDTO taskToRemove) throws DAOException
    {
        //Only remove if the task does already exist
        if(checkIfTaskExists(taskToRemove))
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try
            {
                con = this.getConnection();
                String query = "delete from task where task_name = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, taskToRemove.getTaskName());

                ps.executeUpdate();

                return true;

            }
            catch (SQLException e)
            {
                System.out.println("Problem in removeTask() " + e.getMessage());
            }
            finally
            {
                try
                {
                    if(rs != null)
                    {
                        rs.close();
                    }
                    if(ps != null)
                    {
                        ps.close();
                    }
                    if(con != null)
                    {
                        freeConnection(con);
                    }
                } catch (SQLException e)
                {
                    System.out.println("removeTask() finally() block " + e.getMessage());
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkIfTaskExists(TaskDTO task) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            String query = "select * from task where task_name = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, task.getTaskName());

            rs = ps.executeQuery();

            if (rs.next())
            {
                return true;
            }

        } catch (SQLException e)
        {
            System.out.println("Problem in checkIfTaskExists() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                System.out.println("checkIfTaskExists finally() block " + e.getMessage());
            }
        }
        return false;
    }
}
