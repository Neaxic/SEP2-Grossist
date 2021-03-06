package client.network;

import javafx.util.Pair;
import shared.network.Subject;
import shared.objects.Basket;
import shared.wares.Product;

import java.sql.SQLException;
import java.util.ArrayList;

// Andreas Young

public interface Client extends Subject
{
	void start();

	void getWares();

	Pair<Boolean, ArrayList<Product>> sendOrder(int cvr, Basket basket) throws SQLException;

}
