package server.model;

import javafx.util.Pair;
import server.model.databasemediator.*;
import shared.objects.Basket;
import shared.objects.Order;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Lavet af hele teamet

public class ServerModel {
	private BaseDAO base = new BaseDAO(); // question: Den her er til for?
	private CollectionDAOInterface daoModel = new DAOModel();
	private List<Pair<Product, Integer>> wareAndAmountList;
	private List<Product> wares;

	public ServerModel() throws SQLException {
	}

	public List<Product> getAllProducts() {
		try {
			wares = ((DAOCustomerInterface) daoModel).requestAllProducts();
			return wares;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	public List<Order> getAllOrders() {
		try {
			return ((DAOGrosserInterface) daoModel).getAllOrders();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	public Pair<Boolean, ArrayList<Product>> verifyOrder(Basket orderItems) {
		try {
			wares = ((DAOCustomerInterface) daoModel).requestAllProducts();
			wareAndAmountList = ((DAOGrosserInterface) daoModel).getAllWaresAndAmounts();
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
			((DAOCustomerInterface) daoModel).createOrder(cvr, dateTime, basket);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public void createProduct(Pair<Product, Integer> newProduct) {
		try {
			((DAOGrosserInterface) daoModel).addNewProduct(newProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Pair<Product, Integer>> grosserProductList() {
		try {
			wareAndAmountList = ((DAOGrosserInterface) daoModel).getAllWaresAndAmounts();
			return wareAndAmountList;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	public void delete(Product productToRemove) {
		try {
			((DAOGrosserInterface) daoModel).removeProductFromSystem(productToRemove);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


	public void changeAmount(Pair<Product, Integer> productWithNewAmount) {
		try {
			((DAOGrosserInterface) daoModel).changeAmountInStockOfProduct(productWithNewAmount);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
