package com.dkit.gd2.johnloane.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.zip.DataFormatException;

public class MySqlDAO
{
    public Connection getConnection() throws DAOException
    {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/gd2_oop_task_manager";
        String username = "root";
        String password = "";

        Connection con = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e)
        {
            System.out.println("Problem connecting " + e.getMessage());
            System.exit(1);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Problem with the driver " + e.getMessage());
            System.exit(2);
        }
        System.out.println("Connected to the database");
        return con;
    }

    public void freeConnection(Connection con) throws DAOException
    {
        try
        {
            if(con != null)
            {
                con.close();
                con = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println("Problem closing the database connection " + e.getMessage());
            System.exit(1);
        }
    }

}
