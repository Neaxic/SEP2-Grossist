package server.model.databaseMediator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO
{
  protected Connection getConnection() throws SQLException
  {
    Connection result = DriverManager.getConnection(
        "jdbc:postgresql://5.186.121.168:5432/postgres?currentSchema=personSDJ2", //Prøv med IP hvis lokalt netværk
        "postgres", "Software20");
    //result.setAutoCommit(false);
    return result;
  }
}
