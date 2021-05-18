package server.model.databaseMediator;

import shared.wares.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

// Lavet af hele teamet

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
    map.put("Alcohol",
        getProduct("alcoholicBeverage", Alcohol.class.getName()));
    map.put("Drink", getProduct("nonAlcoholicBeverage", Drink.class.getName()));
    map.put("MeatAndSeafood",
        getProduct("meatAndSeafood", MeatAndFish.class.getName()));
    map.put("Colonial", getProduct("colonial", Colonial.class.getName()));
    map.put("CooledAndDairy",
        getProduct("dairyandeggs", CooledAndDairy.class.getName()));
    map.put("Frozen", getProduct("frozenfood", Frozen.class.getName()));
    map.put("FruitsAndVegetable",
        getProduct("fruitsandvegetables", FruitsAndVegetable.class.getName()));
    return map;
  }

	/**
	 * Creates an ArrayList of Products and returns it with products of the given type. Assumes the BaseDAO is correctly setup to connect to the Grosser Database
	 *
	 * @param schemaName   The name of the Schema where the products requested are
	 * @param productClass Exact name of the class in question. <u><b>Yes, this is Case Sensitive</b></u>
	 * @return an <b>ArrayList</b> consisting of the products from the given list. All cast to the superclass Product
	 */
	public ArrayList<Product> getProduct(String schemaName, String productClass) {
		ArrayList<Product> list = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("select * from " + schemaName);
			ResultSet result = statement.executeQuery();
			// Constructor for dynamic creation of different Product Classes
			Constructor<?> c;
			ArrayList<Object> params;
			int columnCount = result.getMetaData().getColumnCount();
			while (result.next()) {
				params = new ArrayList<>();
				for (int i = 0; i < columnCount; i++) {
					params.add(result.getObject(i + 1));
				}
				c = Class.forName(productClass).getDeclaredConstructor(Object[].class);
				list.add((Product) c.newInstance((Object) params.toArray()));
			}
		} catch (SQLException | ClassNotFoundException throwables) {
			System.out.println("SQL or Database error");
			throwables.printStackTrace();
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			System.out.println("Class Instantiation error");
			e.printStackTrace();
		}
		return list;
	}

	public void createOrder(int cvr, double sum, LocalDate date) {
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO order_(cvr, orderNo, orderDate, totalPrice) VALUES(?,default,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setInt(1, cvr);
			statement.setDate(2, Date.valueOf(date));
			statement.setDouble(3, sum);

      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      keys.next();
    }
    catch (SQLException throwables)
    {
      System.out.println("SQL or Database error");
      throwables.printStackTrace();
    }
  }

  public void createOrderSpec(Basket basket, int CVR, LocalDate date,
      double sum) //TODO: Skal laves når der er styr på DB.
  {
//    try (Connection connection = getConnection())
//    {
//      PreparedStatement getOrderNo = connection.prepareStatement(
//          "SELECT orderNo FROM order_ WHERE cvr = " + CVR + " AND totalprice = " + sum +
//              "AND orderdate = '" + date.toString() + "'");
//
//      ResultSet orderNoResult = getOrderNo.executeQuery();
//      orderNoResult.next();
//      int orderNo = (int) orderNoResult.getObject(1);
//
//      PreparedStatement statement = connection
//          .prepareStatement("INSERT INTO orderspec(orderno, productid, amount) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
//
//      for (Product product : basket.getBasket().keySet())
//      {
//        statement.setInt(1, orderNo);
//        statement.setInt(2, product.getWareNumber());
//        statement.setInt(3, basket.getAmount(product));
//
//        statement.executeUpdate();
//      }
//    }
//    catch (SQLException e)
//    {
//      System.out.println("Create orderSpec error");
//      e.printStackTrace();
//    }
  }


	//GETTERS --------------------------------------------------------------------------------------------------------------
	public ArrayList<String> getCustomerID() throws SQLException {
		ArrayList<String> str = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("select cvr from customer");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				str.add(result.getString("cvr"));
			}
		}
		return str;
	}

	public String getCustomerNameFromCvr(int cvr) throws SQLException {
		String str = "";
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("select name from customer where cvr = " + cvr);
			ResultSet result = statement.executeQuery();
			result.next();
			str = result.getString(1);
		}
		return str;
	}

	public ArrayList<Integer> getProductIds() throws SQLException {
		return getProductIds("product");
	}

	public String getProductNameFromProductId(int id) throws SQLException {
		return getFrom("select productname from product where productId =", id);
	}

	public String getProductMeasurementFromProductId(int id) throws SQLException {
		return getFrom("select measurement from product where productId =", id);
	}

	public String getProductMinPurchaseFromProductId(int id) throws SQLException {
		return getFrom("select minPurchase from product where productId =", id);
	}

	public String getProductProducesByFromProductId(int id) throws SQLException {
		return getFrom("select producesBy from product where productId =", id);
	}

	public int getProductSalesPriceFromProductId(int id) throws SQLException {
		String q = getFrom("select salesPrice from product where productId =", id);
		int i = Integer.parseInt(q);
		return i;
	}

	public String getProductbbDateFromProductId(int id) throws SQLException {
		return getFrom("select bbDate from product where productId =", id);
	}

	public int getProductAmountInStockFromProductId(int id) throws SQLException {
		String queryResult = getFrom("select amountInStock from product where productId =", id);
		if (queryResult.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(queryResult);
	}

	public String getProductTagsFromProductId(int id) throws SQLException {
		return getFrom("select tags from product where productId =", id);
	}

	public ArrayList<Integer> getAlcoholicBeveragesIds() throws SQLException {
		return getProductIds("alcoholicbeverage");
	}

	public double getAlcoholicPercentageFromProductId(int id) throws SQLException {
		String q = getFrom("select alcoholpercentage from alcoholicbeverage where productId =", id);
		Double i = Double.parseDouble(q);
		return i;
	}

	public String getAlcoholicProductionCountryFromProductId(int id) throws SQLException {
		String q = getFrom("select productionCountry from alcoholicbeverage where productId =", id);
		return q;
	}

	public String getAlcoholicTypeFromProductId(int id) throws SQLException {
		String q = getFrom("select type from alcoholicbeverage where productId =", id);
		return q;
	}

	public ArrayList<Integer> getNonAlcoholicBeveragesIds() throws SQLException {
		return getProductIds("nonalcoholicbeverage");
	}

	public String getNonAlcoholicTypeFromProductId(int id) throws SQLException {
		String q = getFrom("select type from nonalcoholicbeverage where productId =", id);
		return q;
	}

	public ArrayList<Integer> getMeatAndSeaFoodIds() throws SQLException {
		return getProductIds("meatandseafood");
	}

	public String getMeatAndSeaFoodProductionCountryFromProductId(int id) throws SQLException {
		String q = getFrom("select productioncountry from meatandseafood where productId =", id);
		return q;
	}

	public ArrayList<Integer> getFruitsAndVegetablesIds() throws SQLException {
		return getProductIds("fruitsAndVegetables");
	}

	public String getFruitAndVegetablesProductionCountryFromProductId(int id) throws SQLException {
		String q = getFrom("select productioncountry from fruitsandvegetables where productId =", id);
		return q;
	}

	public ArrayList<Integer> getColonialIds() throws SQLException {
		return getProductIds("colonial");
	}

	public String getColonialProductionCountryFromProductId(int id) throws SQLException {
		String q = getFrom("select productioncountry from colonial where productId =", id);
		return q;
	}

	public ArrayList<Integer> getFrozenFoodIds() throws SQLException {
		return getProductIds("frozenfood");
	}

	public ArrayList<Integer> getDairyAndEggsIds() throws SQLException {
		return getProductIds("dairyandeggs");
	}

	public ArrayList<Order> getAllOrders()
	{
		ArrayList<Order> orders = new ArrayList<>();
		try (Connection connection = getConnection())
		{
			PreparedStatement statement = connection.prepareStatement("select * from order_");
			ResultSet result = statement.executeQuery();

			while (result.next())
			{
				int CVR = (int) result.getObject(1);
				int orderNo = (int) result.getObject(2);
				LocalDate orderDate = LocalDate.parse((String) result.getObject(3));
				double sum = (double) result.getObject(4);

				Order order = new Order(CVR, orderNo, orderDate, sum, null); //TODO: Basket er null til der er styr på DB.

				orders.add(order);
			}
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		return orders;
	}

	public ArrayList<Integer> getOrderNumbers() {
		ArrayList<Integer> str = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("select orderno from order_");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				str.add(result.getInt("orderno"));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return str;
	}

	public String getCvrFromOrderNumber(int id) throws SQLException {
		return getFrom("select cvr from order_ where orderno =", id);
	}

	public String getOrderDateFromOrderNumber(int id) throws SQLException {
		return getFrom("select orderdate from order_ where orderno =", id);
	}

	public String getTotalPriceFromOrderNumber(int id) throws SQLException {
		return getFrom("select totalprice from order_ where orderno =", id);
	}

	public String getFromSQLStatement(String sql, String id) throws SQLException {
		//IKKE FÆRDIG ENDNU
		String str = "";
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql + id);
			ResultSet result = statement.executeQuery();
			result.next();
			str = result.getString(1);
		}
		return str;
	}

	//Hjælpe funktioner
	public String getFrom(String SQLStatement, int i) throws SQLException {
		String str = "";
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQLStatement + i);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				str = result.getString(1);
			}
		}
		return str;
	}

	public ArrayList<Integer> getProductIds(String from) throws SQLException {
		ArrayList<Integer> str = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("select productid from " + from);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				str.add(result.getInt("productid"));
			}
		}
		return str;
	}

//	public ArrayList<Product> getAlcoholProducts() {
//		ArrayList<Product> alcoholList = new ArrayList<>();
//		try (Connection connection = getConnection()) {
//			PreparedStatement statement = connection.prepareStatement("select * from alcoholicBeverage");
//			ResultSet result = statement.executeQuery();
//			while (result.next()) {
//				alcoholList.add(new Alcohol(result.getString("productName"), result.getString("measurement"), result.getDate("bbDate").toLocalDate(), result.getInt("productId"), 0, result.getDouble("salesPrice"), result.getInt("minPurchase"), result.getString("productionCountry"), result.getDouble("alcoholPercentage"), result.getString("type")));
//			}
//			return alcoholList;
//		} catch (SQLException throwables) {
//			throwables.printStackTrace();
//		}
//		System.out.println("Could not connect :( DAOMODEL");
//		return alcoholList;
//	}
//
//	public ArrayList<Product> getNonAlcoholic() {
//		ArrayList<Product> nonAlcoholic = new ArrayList<>();
//		try (Connection connection = getConnection()) {
//			PreparedStatement statement = connection.prepareStatement(
//					"select * from nonAlcoholicBeverage");
//
//			ResultSet result = statement.executeQuery();
//			while (result.next()) {
//				nonAlcoholic.add(new Drink(result.getString("productName"), result.getString("measurement"),
//						result.getDate("bbDate").toLocalDate(), result.getInt("productId"), 0, result.getDouble("salesPrice"),
//						result.getInt("minPurchase"), result.getString("type")));
//			}
//
//			return nonAlcoholic;
//		} catch (SQLException throwables) {
//			throwables.printStackTrace();
//		}
//		System.out.println("Could not connect :( DAOMODEL");
//		return nonAlcoholic;
//	}
//
//	public ArrayList<Product> getMeatAndSeafood() {
//		ArrayList<Product> meatAndSeafood = new ArrayList<>();
//		try (Connection connection = getConnection()) {
//			PreparedStatement statement = connection.prepareStatement("select * from meatAndSeafood");
//			ResultSet result = statement.executeQuery();
//			while (result.next()) {
//				meatAndSeafood.add(new MeatAndFish(result.getString("productName"), result.getString("measurement"), result.getDate("bbDate").toLocalDate(), result.getInt("productId"), 0, result.getDouble("salesPrice"), result.getInt("minPurchase"), result.getString("productionCountry")));
//			}
//			return meatAndSeafood;
//		} catch (SQLException throwables) {
//			throwables.printStackTrace();
//		}
//		System.out.println("Could not connect :( DAOMODEL");
//		return meatAndSeafood;
//	}
//
//	public ArrayList<Product> getFruitAndVegetable() {
//		ArrayList<Product> fruitAndVegetable = new ArrayList<>();
//		try (Connection connection = getConnection()) {
//			PreparedStatement statement = connection.prepareStatement(
//					"select * from fruitsAndVegetables");
//
//			ResultSet result = statement.executeQuery();
//			while (result.next()) {
//				fruitAndVegetable.add(new FruitsAndVegetable(result.getString("productName"), result.getString("measurement"),
//						result.getDate("bbDate").toLocalDate(), result.getInt("productId"), 0, result.getDouble("salesPrice"),
//						result.getInt("minPurchase"), result.getString("productionCountry")));
//			}
//
//			return fruitAndVegetable;
//		} catch (SQLException throwables) {
//			throwables.printStackTrace();
//		}
//		System.out.println("Could not connect :( DAOMODEL");
//		return fruitAndVegetable;
//	}
}
