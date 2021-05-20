package client.grosserclient.model;

import javafx.util.Pair;
import shared.network.Subject;
import shared.wares.Product;

public interface GrosserModelInterface extends Subject {
	void createNewProduct(Pair<Product, Integer> newProduct);

	void getAllOrders();

	void requestAllWaresAndAmounts();

	void deleteItem(Product productID);

	void changeAmount(Pair<Product, Integer> newPair);
}
