package server.model;

import server.model.databaseMediator.BaseDAO;
import server.model.databaseMediator.DAOModel;
import server.model.databaseMediator.ModelInterface;
import shared.wares.Basket;
import shared.wares.Order;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

// Lavet af hele teamet

public class DataModelImpl {
	private BaseDAO base = new BaseDAO();
	private ModelInterface model = new DAOModel();


	public DataModelImpl() throws SQLException {
	}

	public HashMap<String, ArrayList<Product>> getAllProducts() {
		return model.getAllProducts();
	}

	public ArrayList<Order> getAllOrders()
	{
		return model.getAllOrders();
	}

	public boolean verifyOrder(Basket orderItems) {
		int inStock;
		for (Product p : orderItems.getBasket().keySet()) {
			try {
				inStock = model.getProductAmountInStockFromProductId(p.getWareNumber());
				if (inStock < orderItems.getAmount(p)) { //SOUT
					System.out.println(inStock + " : " + model.getProductAmountInStockFromProductId(p.getWareNumber()));
					return false;
				}
			} catch (SQLException throwable) {
				throwable.printStackTrace();
			}
		}
		return !orderItems.getBasket().isEmpty(); // Hvis den er tom, så vil hele loopet herover blive sprunget over
	}

	public void createOrder(int cvr, double sum, LocalDate date) {
		model.createOrder(cvr, sum, date);
	}

	public void createOrderSpec(Basket basket, int CVR, LocalDate date, double sum) throws SQLException
	{
		model.createOrderSpec(basket, CVR, date, sum);
	}
}
