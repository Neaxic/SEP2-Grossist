package server.model.databasemediator;

import javafx.util.Pair;
import shared.wares.Order;
import shared.wares.Product;

import java.util.List;

public interface DAOGrosserInterface {
	// GETTING

	/**
	 * Accesses the Database and creates a list of Pairs, containing each Product as a Key and the amount in stock as the value for that key
	 *
	 * @return List containing Products and amounts paired up
	 */
	List<Pair<Product, Integer>> getAllWaresAndAmounts();

	/**
	 * Accesses the Database and creates a list of all Orders
	 *
	 * @return List containing all orders currently present in the Database
	 */
	List<Order> getAllOrders();

	// SETTING

	/**
	 * Accesses the Database and creates an SQL statement, using postgresql, to add the product to the super-table as well as the designated sub-table
	 *
	 * @param newProductAndAmount Pair consisting of the Product as well as the Amount of the product to be added to the Database
	 * @return True if SQL execution was successful, false otherwise
	 */
	boolean addNewProduct(Pair<Product, Integer> newProductAndAmount);

	/**
	 * Accesses the Database and creates an SQL statement, using postgresql, to change the amount in stock of a product in the system. <br>
	 * Firstly changes the amount in the super-table then in the designated sub-table
	 *
	 * @param productAndNewAmount Pair consisting of the Product as well as the new updated Amount to be changed
	 * @return True if SQL execution was successful, false otherwise
	 */
	boolean changeAmountInStockOfProject(Pair<Product, Integer> productAndNewAmount);

	/**
	 * Accesses the Database and creates an SQL statement, using postgresql, to delete a product from the system. <br>
	 * Firstly removes the product from the super-table then from the designated sub-table
	 *
	 * @param productToRemove Product which is to be removed from the Database
	 * @return True if SQL execution was successful, false otherwise
	 */
	boolean removeProductFromSystem(Product productToRemove);

	/**
	 * Accesses the Database and creates a new Customer entry using the information given
	 *
	 * @param cvr      Customer CVR, used for Order Identification and User Login
	 * @param name     Name of Customer, used for safety verification
	 * @param password Customer Password, used for User Login
	 * @param address  Delivery Address, used by delivery men for routes
	 * @return True if SQL execution was successful, false otherwise
	 */
	boolean addNewCustomer(int cvr, String name, String password, String address);
}
