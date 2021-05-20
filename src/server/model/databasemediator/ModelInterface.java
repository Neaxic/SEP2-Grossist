package server.model.databasemediator;

import javafx.util.Pair;
import shared.wares.Basket;
import shared.wares.Order;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

// Lavet af hele teamet

public interface ModelInterface {
	HashMap<Product, Integer> grosserProductList() throws SQLException;

	HashMap<String, ArrayList<Product>> getAllProducts();

	ArrayList<Order> getAllOrders();

	ArrayList<Product> getProduct(String schemaName, String productClass); //TEMP FIX

	void createOrder(int cvr, double sum, LocalDateTime dateTime);

	void createOrderSpec(Basket basket, int CVR, LocalDateTime date, double sum);

	int getProductAmountInStockFromProductId(int id) throws SQLException;

	void createProduct(Pair<Product, Integer> newProduct) throws SQLException;

	void delete(int productID) throws SQLException;

	void changeAmount(Pair<Product, Integer> productWithNewAmount) throws SQLException;
}
