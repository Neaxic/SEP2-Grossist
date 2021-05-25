package server.model.databasemediator;

import javafx.util.Pair;
import shared.objects.Order;
import shared.wares.Product;

import java.sql.SQLException;
import java.util.List;

public interface DAOGrosserInterface extends CollectionDAOInterface {
	// GETTING

	/**
	 * Accesses the Database and creates a list of Pairs, containing each Product as a Key and the amount in stock as the value for that key
	 *
	 * @return List containing Products and amounts paired up
	 * @throws SQLException Thrown when Database access is not possible <br>
	 *                      Often caused by Database not being online
	 */
	List<Pair<Product, Integer>> getAllWaresAndAmounts() throws SQLException;

	/**
	 * Accesses the Database and creates a list of all Orders
	 *
	 * @return List containing all orders currently present in the Database
	 * @throws SQLException Thrown when Database access is not possible <br>
	 *                      Often caused by Database not being online
	 */
	List<Order> getAllOrders() throws SQLException;

	// SETTING

	/**
	 * Accesses the Database and creates an SQL statement, using postgresql, to add the product to the super-table as well as the designated sub-table
	 *
	 * @param newProductAndAmount Pair consisting of the Product as well as the Amount of the product to be added to the Database
	 * @return True if SQL execution was successful, false otherwise
	 * @throws SQLException Thrown when Database access is not possible <br>
	 *                      Often caused by Database not being online
	 */
	boolean addNewProduct(Pair<Product, Integer> newProductAndAmount) throws SQLException, IllegalArgumentException;

	/**
	 * Accesses the Database and creates an SQL statement, using postgresql, to change the amount in stock of a product in the system. <br>
	 * <br>
	 * Firstly changes the amount in the super-table then in the designated sub-table
	 *
	 * @param productAndAmountToAdd Pair consisting of the Product as well as the Amount added to the system
	 * @return True if SQL execution was successful, false otherwise
	 * @throws SQLException Thrown when Database access is not possible <br>
	 *                      Often caused by Database not being online
	 */
	boolean increaseAmountInStock(Pair<Product, Integer> productAndAmountToAdd) throws SQLException;

	/**
	 * Acceses the Database and creates an SQL statement, using postgresql, to change the amount in stock of a product in the system.
	 * <br>
	 * Firstly changes the amount in the super-table then in the designated sub-table
	 *
	 * @param productAndAmountToRemove
	 * @return
	 * @throws SQLException
	 */
	boolean reduceAmountInStock(Pair<Product, Integer> productAndAmountToRemove) throws SQLException;

	/**
	 * Accesses the Database and creates an SQL statement, using postgresql, to delete a product from the system. <br>
	 * <br>
	 * Firstly removes the product from the super-table then from the designated sub-table
	 *
	 * @param productToRemove Product which is to be removed from the Database
	 * @return True if SQL execution was successful, false otherwise
	 * @throws SQLException Thrown when Database access is not possible <br>
	 *                      Often caused by Database not being online
	 */
	boolean removeProductFromSystem(Product productToRemove) throws SQLException;

	/**
	 * Accesses the Database and creates a new Customer entry using the information given
	 *
	 * @param cvr      Customer CVR, used for Order Identification and User Login
	 * @param name     Name of Customer, used for safety verification
	 * @param password Customer Password, used for User Login
	 * @param address  Delivery Address, used by delivery men for routes
	 * @return True if SQL execution was successful, false otherwise
	 * @throws SQLException Thrown when Database access is not possible <br>
	 *                      Often caused by Database not being online
	 */
	boolean addNewCustomer(int cvr, String name, String password, String address) throws SQLException;

	/**
	 * Accesses the Database and removes the most recent Order made. <br>
	 * <b>Intentional Use:</b> Cleaning up after Testing. <br>
	 * <b>Possible Other Use:</b> Undo button.
	 */
	void deleteLatestOrder() throws SQLException;

	/**
	 * Accesses the Database and removes the Customer with the given CVR
	 *
	 * @param customerCVR CVR of the Customer whom is to be removed
	 */
	void removeCustomer(int customerCVR) throws SQLException;
}
