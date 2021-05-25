package server.model;

import javafx.util.Pair;
import server.model.databasemediator.DAOCustomerInterface;
import server.model.databasemediator.DAOGrosserInterface;
import server.model.databasemediator.DAOModel;
import shared.objects.Basket;
import shared.objects.CustomerContainer;
import shared.objects.Order;
import shared.util.md5;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Lavet af hele teamet

public class ServerModel {
	private final DAOGrosserInterface DAOGrosser = new DAOModel();
	private final DAOCustomerInterface DAOCustomer = new DAOModel();
	private List<Pair<Product, Integer>> wareAndAmountList;
	private List<Product> wares;

	public ServerModel() throws SQLException {
	}

	public List<Product> getAllProducts() {
		try {
			wares = DAOCustomer.requestAllProducts();
			return wares;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	public List<Order> getAllOrders() {
		try {
			return DAOGrosser.getAllOrders();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	/**
	 * Verifies the Order by comparing the items in stock to the requested items in the basket, as well as the amount wished to be purchased
	 *
	 * @param orderItems Basket containing Wares and Amounts of each ware
	 * @return Pair [true, Empty Arraylist] if the verification was completed <br>
	 * Pair [false, Arraylist] where the arraylist contains all products that either are not in stock, or not enough are in stock <br>
	 * Pair [false, null] if the <b>orderItems</b> was empty or null
	 * @throws SQLException
	 */
	public Pair<Boolean, ArrayList<Product>> verifyOrder(Basket orderItems) throws SQLException {
		if (orderItems == null || orderItems.getBasket().size() < 1) {
			return new Pair<>(false, null);
		}
		wares = DAOCustomer.requestAllProducts();
		wareAndAmountList = DAOGrosser.getAllWaresAndAmounts();
	public Map<Integer, String> getLoginInfo()
	{
		return DAOCustomer.getLoginInfo();
	}

	public Pair<Boolean, ArrayList<Product>> verifyOrder(Basket orderItems) {
		try {
			wares = DAOCustomer.requestAllProducts();
			wareAndAmountList = DAOGrosser.getAllWaresAndAmounts();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		// CHECK IF ALL WARES IN THE BASKET ALSO EXISTS IN THE DATABASE

		ArrayList<Product> products = new ArrayList<>();
		int i = 0;
		for (Product p : orderItems.getBasket().keySet()) {
			i++;
			for (Pair<Product, Integer> wareAndAmount : wareAndAmountList) {
				if (wareAndAmount.getKey().getWareNumber() == p.getWareNumber() && wareAndAmount.getValue() < orderItems.getAmount(p)) {
					products.add(p);
				}
			}
		}
		Pair<Boolean, ArrayList<Product>> returnPair = new Pair<>(!orderItems.getBasket().isEmpty() && products.isEmpty(), products);
		return returnPair;
	}

	public void createOrder(int cvr, LocalDateTime dateTime, Basket basket) throws SQLException {

		DAOCustomer.createOrder(cvr, dateTime, basket);
		for (Product p : basket.getBasket().keySet()) {
			reduceAmount(new Pair<>(p, basket.getAmount(p)));
		}

	}

	public void createProduct(Pair<Product, Integer> newProduct) throws SQLException {
		DAOGrosser.addNewProduct(newProduct);
	}

	public List<Pair<Product, Integer>> grosserProductList() {
		try {
			wareAndAmountList = DAOGrosser.getAllWaresAndAmounts();
			return wareAndAmountList;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	public void delete(Product productToRemove) {
		try {
			DAOGrosser.removeProductFromSystem(productToRemove);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public boolean increaseAmount(Pair<Product, Integer> productAndAmountToAdd) {
		try {
			DAOGrosser.increaseAmountInStock(productAndAmountToAdd);
		} catch (SQLException throwables) {
			return false;
		}
		return true;
	}

	public boolean reduceAmount(Pair<Product, Integer> productAndAmountToRemove) {
		try {
			DAOGrosser.reduceAmountInStock(productAndAmountToRemove);
		} catch (SQLException throwables) {
			return false;
		}
		return true;
	}

	public boolean addCustomer(CustomerContainer customer) {
		int cvr = customer.getCVR();
		String name = customer.getName();
		String password = customer.getPw();
		String address = customer.getAddress();
		try {
			return DAOGrosser.addNewCustomer(cvr, name, password, address);
		} catch (SQLException throwables) {
			return false;
		}
	}

	public void deleteLatestOrder() {
		try {
			DAOGrosser.deleteLatestOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
