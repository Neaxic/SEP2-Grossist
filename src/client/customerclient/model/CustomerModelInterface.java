package client.customerclient.model;

import javafx.util.Pair;
import shared.network.Subject;
import shared.objects.Basket;
import shared.wares.Product;

import java.util.ArrayList;
import java.util.List;

// Andreas Young, Andreas Østergaard, Frederik Bergmann.

public interface CustomerModelInterface extends Subject {
	void updateWares();

	void addToBasket(Product product, int amount);

	void removeFromBasket(Product product);

	void changeAmount(Product product, int newAmount);

	Basket getMyBasket();

	Pair<Boolean, ArrayList<Product>> sendOrder(Basket basket, double sum);

	List<Product> getAllWares();

	void emptyBasket();
}
