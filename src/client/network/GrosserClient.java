package client.network;

// Andreas Østergaard, Frederik Bergmann, Andreas Young.

import javafx.util.Pair;
import shared.objects.CustomerContainer;
import shared.wares.Product;

import java.beans.PropertyChangeListener;
import java.sql.SQLException;

public interface GrosserClient {
	void createProduct(Pair<Product, Integer> newProduct) throws SQLException, IllegalArgumentException;

	void getAllOrders();

	void addListener(PropertyChangeListener listener);

	void requestGrosserProducts();

	void deleteWare(Product ware);

	void increaseStock(Pair<Product, Integer> productWithNewAmount);

	void reduceStock(Pair<Product, Integer> productAndAmountToReduce);

	boolean addCustomer(CustomerContainer customer);

	void deleteLatestOrder();

	void removeCustomer(int customerCVR);

	void getRiskData();
}