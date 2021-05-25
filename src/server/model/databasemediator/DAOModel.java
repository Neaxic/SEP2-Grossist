package server.model.databasemediator;

import javafx.util.Pair;
import shared.objects.Basket;
import shared.objects.Order;
import shared.util.SchemaMap;
import shared.wares.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Lavet af hele teamet

public class DAOModel extends BaseDAO implements DAOCustomerInterface, DAOGrosserInterface {
	ArrayList<Product> salesProducts;
	HashMap<String, ArrayList<Product>> map;

	public DAOModel() throws SQLException {
		DriverManager.registerDriver(new org.postgresql.Driver());
		salesProducts = new ArrayList<>();
		map = new HashMap<>();
	}

	// Methods accessed through the Interfaces

	// DAOCustomerInterface
	@Override
	public List<Product> requestAllProducts() throws SQLException {
		updateInternalStorage();


		return salesProducts;
	}

	@Override
	public Map<Integer, String> getLoginInfo() {
		return getCustomerLoginMap();
	}

	@Override
	public boolean createOrder(int cvr, LocalDateTime orderTime, Basket basket) throws SQLException {
		int orderNo = createOrderInDatabase(cvr, Timestamp.valueOf(orderTime), basket.getSum());
		return createOrderSpecInDatabase(orderNo, basket);
	}

	//DAOGrosserInterface
	@Override
	public List<Pair<Product, Integer>> getAllWaresAndAmounts() throws SQLException {
		updateInternalStorage();
		List<Pair<Product, Integer>> pairList = new ArrayList<>();
		for (Product p : salesProducts) {
			pairList.add(new Pair<>(p, getAmountOfProduct(p)));
		}
		return pairList;
	}

	@Override
	public List<Order> getAllOrders() throws SQLException {
		return getAllOrdersFromDatabase();
	}

	@Override
	public boolean addNewProduct(Pair<Product, Integer> newProductAndAmount) throws SQLException, IllegalArgumentException {
		if (newProductAndAmount.getValue() <= 0 || newProductAndAmount.getKey().getWareName().isBlank() || newProductAndAmount.getKey().getMeasurementType().isBlank() || newProductAndAmount.getKey().getBestBefore().isBefore(LocalDate.now().plusDays(1)) || newProductAndAmount.getKey().getDeliveryDays() <= 0 || newProductAndAmount.getKey().getPrice() <= 0 || newProductAndAmount.getKey().getProducedBy().isBlank()) {
			throw new IllegalArgumentException();
		}
		int productID = addProductToSuperTable(newProductAndAmount.getKey(), newProductAndAmount.getValue());
		return addProductToDesignatedTable(productID, newProductAndAmount.getKey(), newProductAndAmount.getValue());
	}

	@Override
	public boolean increaseAmountInStock(Pair<Product, Integer> productAndAmountToAdd) throws SQLException {
		return changeAmountInStock(productAndAmountToAdd.getKey(), productAndAmountToAdd.getValue());
	}

	@Override
	public boolean reduceAmountInStock(Pair<Product, Integer> productAndAmountToRemove) throws SQLException {
		return changeAmountInStock(productAndAmountToRemove.getKey(), -productAndAmountToRemove.getValue());
	}

	@Override
	public boolean removeProductFromSystem(Product productToRemove) throws SQLException {
		return removeProductFromDatabase(productToRemove);
	}

	@Override
	public boolean addNewCustomer(int cvr, String name, String password, String address) throws SQLException {
		return addCustomerToDatabase(cvr, name, password, address);
	}

	@Override
	public void deleteLatestOrder() throws SQLException {
		int orderNo = getLatestOrderNo();
		deleteOrder(orderNo);
	}


	// Private Methods for accessing the Database
	private void updateInternalStorage() throws SQLException {
		map.clear();
		map.put("Alcohol", getProductsFromTable(SchemaMap.Mapping(Alcohol.class), Alcohol.class));
		map.put("Colonial", getProductsFromTable(SchemaMap.Mapping(Colonial.class), Colonial.class));
		map.put("CooledAndDairy", getProductsFromTable(SchemaMap.Mapping(CooledAndDairy.class), CooledAndDairy.class));
		map.put("Drinks", getProductsFromTable(SchemaMap.Mapping(Drink.class), Drink.class));
		map.put("Frozen", getProductsFromTable(SchemaMap.Mapping(Frozen.class), Frozen.class));
		map.put("FruitsAndVegetables", getProductsFromTable(SchemaMap.Mapping(FruitsAndVegetable.class), FruitsAndVegetable.class));
		map.put("MeatAndFish", getProductsFromTable(SchemaMap.Mapping(MeatAndFish.class), MeatAndFish.class));

		salesProducts.clear();
		for (ArrayList<Product> value : map.values()) {
			salesProducts.addAll(value);
		}
	}

	private ArrayList<Product> getProductsFromTable(String table, Class<? extends Product> productClass) throws SQLException {
		ArrayList<Product> l = new ArrayList<>();
		try (Connection conn = getConnection()) {
			PreparedStatement s = conn.prepareStatement("select * from " + table);
			ResultSet result = s.executeQuery();
			Constructor<?> c;
			Object[] params;
			int colCount = result.getMetaData().getColumnCount();
			while (result.next()) {
				params = new Object[colCount];
				for (int i = 0; i < colCount; i++) {
					params[i] = (result.getObject(i + 1));
				}
				c = Class.forName(productClass.getName()).getDeclaredConstructor(Object[].class);
				l.add((Product) c.newInstance((Object) params));
			}
		} catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		return l;
	}

	private int createOrderInDatabase(int cvr, Timestamp orderTime, double sum) throws SQLException {
		try (Connection conn = getConnection()) {
			conn.prepareStatement("INSERT INTO order_(cvr, orderTime, totalPrice) VALUES (" + cvr + ", '" + orderTime + "', " + sum + ")").execute();
			ResultSet r = conn.prepareStatement("SELECT orderNo FROM order_ WHERE orderTime = '" + orderTime + "' AND totalPrice = " + sum).executeQuery();
			if (r.next()) {
				return r.getInt("orderNo");
			}
			return -1;
		}
	}

	private boolean createOrderSpecInDatabase(int orderNo, Basket basket) {
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO orderspec(orderno, productid, amount) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

			for (Product p : basket.getBasket().keySet()) {
				statement.setLong(1, orderNo);
				statement.setInt(2, p.getWareNumber());
				statement.setInt(3, basket.getAmount(p));

				statement.executeUpdate();
			}
		} catch (SQLException e) {
			return false;
		}
		return true;

	}

	private Integer getAmountOfProduct(Product p) throws SQLException {
		int productID;
		try (Connection conn = getConnection()) {
			PreparedStatement s = conn.prepareStatement("SELECT amountInStock FROM product WHERE productID = " + p.getWareNumber());
			ResultSet r = s.executeQuery();
			r.next();
			productID = r.getInt("amountInStock");
		}
		return productID;
	}

	private List<Order> getAllOrdersFromDatabase() throws SQLException {
		List<Order> l = new ArrayList<>();
		try (Connection conn = getConnection()) {
			PreparedStatement s = conn.prepareStatement("SELECT * FROM order_");
			ResultSet r = s.executeQuery();
			while (r.next()) {
				l.add(new Order(r.getInt("cvr"), r.getInt("orderNo"), r.getTimestamp("orderTime").toLocalDateTime(), r.getDouble("totalPrice")));
			}
		}
		return l;
	}

	private int addProductToSuperTable(Product p, Integer v) throws SQLException {
		try (Connection conn = getConnection()) {
			if (p.getWareNumber() < 0) {
				conn.prepareStatement("INSERT INTO product(productname, measurement, producedby, salesprice, bbdate, amountinstock, tags) VALUES ('" + p.getWareName() + "', '" + p.getMeasurementType() + "', '" + p.getProducedBy() + "', " + p.getPrice() + ", '" + p.getBestBefore() + "', " + v + ", '" + p.getTags() + "')").execute();
			} else {
				conn.prepareStatement("INSERT INTO product(productID, productName, measurement, producedBy, salesPrice, bbDate, amountInStock, tags) VALUES (" + p.getWareNumber() + ", '" + p.getWareName() + "', '" + p.getMeasurementType() + "', '" + p.getProducedBy() + "', " + p.getPrice() + ", '" + p.getBestBefore() + "', " + v + ", '" + p.getTags() + "')").execute();
			}
			ResultSet r = conn.prepareStatement("SELECT productID FROM product WHERE productName = '" + p.getWareName() + "' AND producedBy = '" + p.getProducedBy() + "' AND bbDate = '" + p.getBestBefore() + "'").executeQuery();
			r.next();
			return r.getInt("productID");
		}
	}

	private boolean addProductToDesignatedTable(int productID, Product p, Integer v) {
		try (Connection conn = getConnection()) {
			PreparedStatement s = conn.prepareStatement("INSERT INTO " + SchemaMap.Mapping(p.getClass()) + " (productID, " + p.sqlTemplate() + ", amountInStock) VALUES (" + productID + "," + p.sqlInformation() + "," + v + ")");
			s.execute();
		} catch (SQLException throwables) {
			return false;
		}
		return true;
	}

	private boolean changeAmountInStock(Product p, Integer v) {
		int newAmount = v;
		try (Connection conn = getConnection()) {
			ResultSet r = conn.prepareStatement("SELECT amountInStock FROM product WHERE productID = " + p.getWareNumber()).executeQuery();
			if (r.next()) {
				newAmount = r.getInt("amountInStock") + v;
			}
			conn.prepareStatement("UPDATE product SET amountInStock = " + newAmount + " WHERE productID = " + p.getWareNumber()).execute();
			conn.prepareStatement("UPDATE " + SchemaMap.Mapping(p.getClass()) + " SET amountInStock = " + newAmount + " WHERE productID = " + p.getWareNumber()).execute();
		} catch (SQLException throwables) {
			return false;
		}
		return true;
	}

	private boolean removeProductFromDatabase(Product p) throws SQLException {
		try (Connection conn = getConnection()) {
			conn.prepareStatement("DELETE FROM product WHERE productID = " + p.getWareNumber()).execute();
			conn.prepareStatement("DELETE FROM " + SchemaMap.Mapping(p.getClass()) + " WHERE productID = " + p.getWareNumber()).execute();
		}
		return true;
	}

	private Map<Integer, String> getCustomerLoginMap() {
		Map<Integer, String> customerLoginMap = new HashMap<>();

		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT CVR, password FROM customer");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				customerLoginMap.put(result.getInt(1), result.getString(2));
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return customerLoginMap;
	}

	private boolean addCustomerToDatabase(int cvr, String name, String password, String address) throws SQLException {
		try (Connection conn = getConnection()) {
			conn.prepareStatement("INSERT INTO customer VALUES (" + cvr + ",  '" + name + "', '" + password + "', '" + address + "')").execute();
			return true;
		}
	}

	private int getLatestOrderNo() throws SQLException {
		try (Connection conn = getConnection()) {
			ResultSet r = conn.prepareStatement("SELECT orderNo FROM order_ ORDER BY orderTime DESC").executeQuery();
			System.out.println(r.getFetchSize());
			r.next();
			return r.getInt("orderNo");
		}
	}

	private boolean deleteOrder(int orderNo) throws SQLException {
		try (Connection conn = getConnection()) {
			conn.prepareStatement("DELETE FROM orderSpec WHERE orderNo = " + orderNo).execute();
			conn.prepareStatement("DELETE FROM order_ WHERE orderNo = " + orderNo).execute();
		}
		return true;
	}
}
