package client.network;

// Andreas Østergaard

import javafx.util.Pair;
import server.model.RISK_ASSESSMENT.RiskContainer;
import server.model.RISK_ASSESSMENT.RiskReport;
import shared.objects.CustomerContainer;
import shared.wares.Product;

import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;

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


//TODO: tilføj alle methoder til interfacet client