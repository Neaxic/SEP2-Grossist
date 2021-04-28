package server.model.databaseMediator;

import shared.wares.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOModel extends BaseDAO implements ModelInterface
{
  ArrayList<Product> salesProducts;
  HashMap<String, ArrayList<Product>> map = new HashMap<>();

  public DAOModel() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
    salesProducts = new ArrayList<>();
  }

  public HashMap<String, ArrayList<Product>> getAllProducts()
  {
      map.put("Alcohol", getAlcoholProducts());
      map.put("Drink", getNonAlcoholic());
      map.put("MeatAndSeafood", getMeatAndSeafood());
      return map;
  }

  public ArrayList<Product> getAlcoholProducts()
  {
   ArrayList<Product> alcoholList = new ArrayList<>();
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

    public ArrayList<Product> getNonAlcoholic()
    {
        ArrayList<Product> nonAlcoholic = new ArrayList<>();
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from nonAlcoholicBeverage");

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                nonAlcoholic.add(new Drink(result.getString("productName"), result.getString("measurement"),
                        result.getDate("bbDate").toLocalDate(), result.getInt("productId"),0, result.getDouble("salesPrice"),
                        result.getInt("minPurchase"), result.getString("type")));
            }

            return nonAlcoholic;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        System.out.println("Could not connect :( DAOMODEL");
        return nonAlcoholic;
    }

    public ArrayList<Product> getMeatAndSeafood()
    {
        ArrayList<Product> meatAndSeafood = new ArrayList<>();
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from meatAndSeafood");

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                meatAndSeafood.add(new MeatAndFish(result.getString("productName"), result.getString("measurement"),
                        result.getDate("bbDate").toLocalDate(), result.getInt("productId"),0, result.getDouble("salesPrice"),
                        result.getInt("minPurchase"), result.getString("productionCountry")));
            }

            return meatAndSeafood;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        System.out.println("Could not connect :( DAOMODEL");
        return meatAndSeafood;
    }

    public ArrayList<Product> getFruitAndVegetable()
    {
        ArrayList<Product> fruitAndVegetable = new ArrayList<>();
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from fruitsAndVegetables");

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                fruitAndVegetable.add(new FruitsAndVegetable(result.getString("productName"), result.getString("measurement"),
                        result.getDate("bbDate").toLocalDate(), result.getInt("productId"),0, result.getDouble("salesPrice"),
                        result.getInt("minPurchase"), result.getString("productionCountry")));
            }

            return fruitAndVegetable;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        System.out.println("Could not connect :( DAOMODEL");
        return fruitAndVegetable;
    }
}
