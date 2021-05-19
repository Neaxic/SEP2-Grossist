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

	void deleteWare(int productID);
}


//TODO: tilføj alle methoder til interfacet client