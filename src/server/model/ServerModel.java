package server.model;

import javafx.util.Pair;
import server.model.databasemediator.*;
import shared.objects.Basket;
import shared.objects.CustomerContainer;
import shared.objects.Order;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
			System.out.println(i); //SOUT
			for (Pair<Product, Integer> wareAndAmount : wareAndAmountList) {
				if (wareAndAmount.getKey().getWareNumber() == p.getWareNumber() &&  wareAndAmount.getValue() < orderItems.getAmount(p)) {
					products.add(p);
				}
			}
		}
		Pair<Boolean, ArrayList<Product>> returnPair = new Pair<>(!orderItems.getBasket().isEmpty() && products.isEmpty(), products);
		System.out.println(returnPair); //SOUT
		return returnPair;
	}

	public void createOrder(int cvr, LocalDateTime dateTime, Basket basket) {
		try {
			DAOCustomer.createOrder(cvr, dateTime, basket);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public void createProduct(Pair<Product, Integer> newProduct) {
		try {
			DAOGrosser.addNewProduct(newProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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


	public void changeAmount(Pair<Product, Integer> productWithNewAmount) {
		try {
			DAOGrosser.changeAmountInStockOfProduct(productWithNewAmount);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
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
}
