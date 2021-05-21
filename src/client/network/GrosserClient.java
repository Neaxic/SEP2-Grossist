package client.network;

// Andreas Østergaard

import javafx.util.Pair;
import shared.wares.Product;

import java.beans.PropertyChangeListener;

public interface GrosserClient {
	void createProduct(Pair<Product, Integer> newProduct);

	void getAllOrders();

	void addListener(PropertyChangeListener listener);

	void requestGrosserProducts();

	void deleteWare(Product productID);

	void increaseStock(Pair<Product, Integer> productWithNewAmount);

	void reduceStock(Pair<Product, Integer> productAndAmountToReduce);
}


//TODO: tilføj alle methoder til interfacet client