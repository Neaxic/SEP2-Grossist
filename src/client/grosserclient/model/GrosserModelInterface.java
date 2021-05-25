package client.grosserclient.model;

import javafx.util.Pair;
import shared.network.Subject;
import shared.objects.CustomerContainer;
import shared.wares.Product;

import java.util.Map;

//Lavet af: Frederik Bergmann, Andreas Young.

public interface GrosserModelInterface extends Subject {
	void reduceStock(Pair<Product, Integer> productAndAmountToReduce);

	void createNewProduct(Pair<Product, Integer> newProduct);

	void getAllOrders();

	void requestAllWaresAndAmounts();

	void deleteItem(Product productID);

	void increaseStock(Pair<Product, Integer> newPair);

	boolean addCustomer(CustomerContainer customer);
}
