package server.model.databaseMediator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO
{
  protected Connection getConnection() throws SQLException
  {
    Connection result = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=grosser", //Prøv med IP hvis lokalt netværk
        "postgres", "290892");
    //result.setAutoCommit(false);
    return result;
  }
}
