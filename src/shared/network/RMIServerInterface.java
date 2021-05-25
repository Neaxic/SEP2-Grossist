package shared.network;

import javafx.util.Pair;
import shared.objects.Basket;
import shared.objects.CustomerContainer;
import shared.wares.Product;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

// Andreas Young, Andreas Østergaard, Frederik Bergmann

public interface RMIServerInterface extends Remote
{
  /**
   * Registers RMIServer Implementation on port 1099 and binds it with a namespace
   *
   * @throws RemoteException       when connection cannot be established
   * @throws AlreadyBoundException when a server already is bound with the same name
   */
  void startServer() throws RemoteException, AlreadyBoundException;

	/**
	 * Registers the callbackClient on the server, allowing the server to easily update the callbackClient using update()
	 *
	 * @param callbackClient Userclient which should be registered on the servers list
	 * @return ID for the given Userclient
	 * @throws RemoteException
	 */
	int registerClient(CallbackClient callbackClient) throws RemoteException;

	/**
	 * Removes the Client from the Server List, leaving the ID available for another user
	 *
	 * @param clientID ID for Client to be removed
	 * @throws RemoteException
	 */
	void removeClient(int clientID) throws RemoteException;

	/**
	 * Asks server for wares in stock
	 *
	 * @param clientID Identification so the server can update the correct client
	 * @throws RemoteException
	 */
	void getWares(int clientID) throws RemoteException;

	void grosserProductList(int id) throws RemoteException;

	/**
	 * Sends an order to the Server Implementation and waits for a verification
	 *
	 * @param cvr        User CVR number, registered through client
	 * @param orderItems Items desired to be ordered for purchase
	 * @return True if the order can be placed, false otherwise
	 * @throws RemoteException
	 */
	Pair<Boolean, ArrayList<Product>> sendOrder(int cvr, Basket orderItems)
			throws RemoteException, SQLException;

	/**
	 * Requests all registered orders for the grosser main view.
	 *
	 * @param clientId User CVR number, registered through client
	 * @throws RemoteException
	 */
	void getAllOrders(int clientId) throws RemoteException;

	/**
	 * Sends a new product to the server
	 *
	 * @param newProduct Pair consisting of the Product and the amount of that product there is
	 * @throws RemoteException
	 */
	void createProduct(Pair<Product, Integer> newProduct) throws RemoteException, SQLException;

	/**
	 * Removes a ware entirely from the Database
	 *
	 * @param product Ware which is to be removed
	 * @throws RemoteException
	 */
	void deleteWare(Product product) throws RemoteException;

	/**
	 * Changes the amount of a ware on the Database
	 *
	 * @param productWithNewAmount Contains the Product and the Amount to increase by
	 */
	void increaseAmountInSystem(Pair<Product, Integer> productWithNewAmount)
			throws RemoteException;

	/**
	 * Changes the amount of a ware on the Database
	 *
	 * @param productAndAmountToRemove Contains the Product and the Amount to reduce by
	 * @throws RemoteException
	 */
	void reduceAmountInSystem(Pair<Product, Integer> productAndAmountToRemove) throws RemoteException;

  /**
   *
   * @param customer Container with info about customer.
   * @throws RemoteException
   */
  boolean addCustomer(CustomerContainer customer) throws RemoteException;

	Map<Integer, String> getLoginInfo() throws RemoteException;

	/**
	 * Deletes the latest order made from the Database. Can be used as an Undo or in Cleaning up after Testing
	 */
	void deleteLatestOrder() throws RemoteException;
}
