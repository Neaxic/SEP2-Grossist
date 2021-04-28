package server.model.databaseMediator;

import shared.wares.*;
import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOModel extends BaseDAO implements ModelInterface
{
  ArrayList<NewProduct> salesProducts;
  HashMap<String, ArrayList> map = new HashMap();

  public DAOModel() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
    salesProducts = new ArrayList();
  }

  public HashMap<String, ArrayList> getAllProducts()
  {
      map.put("Alcohol", getAlcoholProducts());
      return map;
  }

  public ArrayList<TestAlcohol> getAlcoholProducts()
  {
   ArrayList<TestAlcohol> alcoholList = new ArrayList();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "select * from alcoholicBeverage");

      ResultSet result = statement.executeQuery();
      while (result.next())
      {
        alcoholList.add(new TestAlcohol(result.getString("productName"), result.getString("measurement"),
            result.getDate("bbDate").toLocalDate(), result.getInt("productId"),0, result.getDouble("salesPrice"),
            result.getInt("minPurchase"), result.getString("productionCountry"), result.getDouble("alcoholPercentage"), result.getString("type")));
      }

      return alcoholList;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    System.out.println("Could not connect :( DAOMODEL");
    return  alcoholList;
  }
}
