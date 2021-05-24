package client.grosserclient.model;

import javafx.util.Pair;
import shared.network.Subject;
import shared.wares.Product;

public interface GrosserModelInterface extends Subject {
	void reduceStock(Pair<Product, Integer> productAndAmountToReduce);

	void createNewProduct(Pair<Product, Integer> newProduct);

	void getAllOrders();

	void requestAllWaresAndAmounts();

	void deleteItem(Product productID);

	void increaseStock(Pair<Product, Integer> newPair);
}
