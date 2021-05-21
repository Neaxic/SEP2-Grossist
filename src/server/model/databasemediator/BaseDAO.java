package server.model.databasemediator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// Line Guld, men bruger individualiseret så alle kan få adgang på deres computer

public class BaseDAO
{
  protected Connection getConnection() throws SQLException
  {
    Connection result = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=grosser", //Prøv med IP hvis lokalt netværk
        "postgres", "sh1tvac"); //TODO: HUSK AT SÆTTE JERES PASSWORD IND HER
    //result.setAutoCommit(false);
    return result;
  }

}
