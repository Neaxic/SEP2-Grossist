package client.grosserclient.model;

import javafx.util.Pair;
import server.model.RISK_ASSESSMENT.RiskContainer;
import shared.network.Subject;
import shared.objects.CustomerContainer;
import shared.wares.Product;

import java.sql.SQLException;
import java.util.ArrayList;

//Lavet af: Frederik Bergmann, Andreas Young.

public interface GrosserModelInterface extends Subject {
	void reduceStock(Pair<Product, Integer> productAndAmountToReduce);

	void createNewProduct(Pair<Product, Integer> newProduct) throws IllegalArgumentException, SQLException;

	void getAllOrders();

	void requestAllWaresAndAmounts();

	void deleteItem(Product productID);

	void increaseStock(Pair<Product, Integer> newPair);

	boolean addCustomer(CustomerContainer customer);

	void getRiskData();
}
