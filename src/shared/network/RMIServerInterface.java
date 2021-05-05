package shared.network;

import shared.wares.Basket;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

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

	void sendOrder(int cvr, Basket basket, double sum) throws RemoteException;

}
