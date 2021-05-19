package shared.network;

import javafx.util.Pair;
import shared.wares.Basket;
import shared.wares.Product;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// Andreas Young, Andreas Østergaard

public interface RMIServerInterface extends Remote {
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

	/**
	 * Sends an order to the Server Implementation and waits for a verification
	 * @param cvr User CVR number, registered through client
	 * @param orderItems Items desired to be ordered for purchase
	 * @param sum Total sum of the items ordered
	 * @return True if the order can be placed, false otherwise
	 * @throws RemoteException
	 */
	Pair<Boolean, ArrayList<Product>> sendOrder(int cvr, Basket orderItems, double sum) throws RemoteException;

	/**
	 * Requests all registered orders for the grosser main view.
	 * @throws RemoteException
	 */
	void getAllOrders(int clientId) throws RemoteException;

	/**
	 * Sends a new product to the server
	 * @param newProduct
	 * @throws RemoteException
	 */
	void createProduct(Pair<Product, Integer> newProduct) throws RemoteException;
}
