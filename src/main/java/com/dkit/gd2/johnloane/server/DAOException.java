package com.dkit.gd2.johnloane.server;

import java.sql.SQLException;

public class DAOException extends SQLException
{
    public DAOException()
    {

    }

    public DAOException(String aMessage)
    {
        super(aMessage);
    }
}
