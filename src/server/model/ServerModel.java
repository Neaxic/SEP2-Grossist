package server.model;

import javafx.util.Pair;
import server.model.databasemediator.BaseDAO;
import server.model.databasemediator.DAOInterface;
import server.model.databasemediator.DAOModel;
import shared.wares.Basket;
import shared.wares.Order;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

// Lavet af hele teamet

public class ServerModel {
	private BaseDAO base = new BaseDAO(); // question: Den her er til for?
	private DAOInterface model = new DAOModel();


	public ServerModel() throws SQLException {
	}

	public HashMap<String, ArrayList<Product>> getAllProducts() {
		return model.getAllProducts();
	}

	public ArrayList<Order> getAllOrders() {
		return model.getAllOrders();
	}

	public Pair<Boolean, ArrayList<Product>> verifyOrder(Basket orderItems) {
		int inStock;
		ArrayList<Product> products = new ArrayList<>();
		for (Product p : orderItems.getBasket().keySet()) {
			try {
				inStock = model.getProductAmountInStockFromProductId(p.getWareNumber());
				if (inStock < orderItems.getAmount(p)) {
					products.add(p);
				}
			} catch (SQLException throwable) {
				throwable.printStackTrace();
			}
		}
		return new Pair<>(!orderItems.getBasket().isEmpty() && products.isEmpty(), products);
	}

	public void createOrder(int cvr, double sum, LocalDateTime dateTime) {
		model.createOrder(cvr, sum, dateTime);
	}

	public void createOrderSpec(Basket basket, int CVR, LocalDateTime date, double sum) {
		model.createOrderSpec(basket, CVR, date, sum);
	}

	public void createProduct(Pair<Product, Integer> newProduct) {
		try {
			model.createProduct(newProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HashMap<Product, Integer> grosserProductList() {
		try {
			return model.grosserProductList();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	public void delete(int productID) {
		try {
			model.delete(productID);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


	public void changeAmount(Pair<Product, Integer> productWithNewAmount) {
		try {
			model.changeAmount(productWithNewAmount);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}