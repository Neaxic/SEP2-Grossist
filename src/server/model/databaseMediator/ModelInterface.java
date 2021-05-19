package server.model.databaseMediator;

import javafx.util.Pair;
import shared.wares.Basket;
import shared.wares.Order;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

// Lavet af hele teamet

public interface ModelInterface {
	HashMap<String, ArrayList<Product>> getAllProducts();

	ArrayList<Order> getAllOrders();

	ArrayList<Product> getProduct(String schemaName, String productClass); //TEMP FIX

	void createOrder(int cvr, double sum, LocalDateTime dateTime);

	void createOrderSpec(Basket basket, int CVR, LocalDateTime date, double sum);

	int getProductAmountInStockFromProductId(int id) throws SQLException;

	void createProduct(Pair<Product, Integer> newProduct) throws SQLException;
}
