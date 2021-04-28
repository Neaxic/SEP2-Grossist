package server.model.databaseMediator;

import shared.wares.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOModel extends BaseDAO implements ModelInterface
{
  ArrayList<Product> salesProducts;
  HashMap<String, ArrayList<Product>> map = new HashMap();

  public DAOModel() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
    salesProducts = new ArrayList();
  }

  public HashMap<String, ArrayList<Product>> getAllProducts()
  {
      map.put("Alcohol", getAlcoholProducts());
      return map;
  }

  public ArrayList<Alcohol> getAlcoholProducts()
  {
   ArrayList<Alcohol> alcoholList = new ArrayList();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "select * from alcoholicBeverage");

      ResultSet result = statement.executeQuery();
      while (result.next())
      {
        alcoholList.add(new Alcohol(result.getString("productName"), result.getString("measurement"),
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
    return alcoholList;
  }
}
