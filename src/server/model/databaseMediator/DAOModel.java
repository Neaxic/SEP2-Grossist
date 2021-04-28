package server.model.databaseMediator;

import shared.wares.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOModel extends BaseDAO implements ModelInterface {
	ArrayList<Product> salesProducts;
	HashMap<String, ArrayList<Product>> map = new HashMap<>();

	public DAOModel() throws SQLException {
		DriverManager.registerDriver(new org.postgresql.Driver());
		salesProducts = new ArrayList<>();
	}

	public HashMap<String, ArrayList<Product>> getAllProducts() {
		map.put("Alcohol", getProduct("alcoholicBeverage", Alcohol.class.getName()));
		map.put("Drink", getProduct("nonAlcoholicBeverage", Drink.class.getName()));
		map.put("MeatAndSeafood", getProduct("meatAndSeafood", MeatAndFish.class.getName()));
		map.put("Colonial", getProduct("colonial", Colonial.class.getName()));
		map.put("CooledAndDairy", getProduct("dairyandeggs", CooledAndDairy.class.getName()));
		map.put("Frozen", getProduct("frozenfood", Frozen.class.getName()));
		map.put("FruitsAndVegetable", getProduct("fruitsandvegetables", FruitsAndVegetable.class.getName()));
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
