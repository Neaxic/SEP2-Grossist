package server.model.databaseMediator;

import shared.wares.NewProduct;
import shared.wares.NewProductInterface;
import shared.wares.Product;

import java.sql.*;
import java.util.ArrayList;

public class DAOModel extends BaseDAO
{
  ArrayList<NewProduct> salesProducts;

  public DAOModel() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
    salesProducts = new ArrayList();
  }

  public ArrayList<NewProduct> getAllProducts()
  {
    ArrayList<NewProduct> productList = new ArrayList();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "select * from product");

      ResultSet result = statement.executeQuery();
      while (result.next())
      {

          productList.add(new NewProductInterface(result.getInt("productId"),
              result.getString("name"), result.getString("measurement"),
              result.getDate("bbDate"), null, result.getDouble("salesPrice"),
              result.getInt("minPurchase"));
      }

      return productList;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
