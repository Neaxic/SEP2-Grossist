package server.network;

import javafx.util.Pair;
import server.model.ServerModel;
import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.util.Util;
import shared.wares.Basket;
import shared.wares.Order;
import shared.wares.Product;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

// Andreas Østergaard, Andreas Young, Frederik Bergmann

public class RMIServer implements RMIServerInterface {
	private HashMap<Integer, CallbackClient> callbackClients = new HashMap<>();
	private HashMap<String, ArrayList<Product>> wares = new HashMap<>();

	// Dummy Data:
	private ServerModel dataModel = new ServerModel();

	public RMIServer() throws SQLException {
	}

	public void getAllProducts() {
		wares = dataModel.getAllProducts();
	}

	@Override
	public void startServer() throws RemoteException, AlreadyBoundException {
		System.out.println("Server starting...");
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind(Util.SERVERNAME, this);
		UnicastRemoteObject.exportObject(this, 0);
		System.out.println("Connecting to database... (This might take a while)");
		getAllProducts();
		System.out.println("Server started");
	}

	@Override
	public int registerClient(CallbackClient callbackClient) throws RemoteException {
		int currentID;
		do {
			currentID = 1 + (int) (Math.random() * 2048);
		} while (callbackClients.get(currentID) != null); // TODO: Rettes til CVR senere
		callbackClients.put(currentID, callbackClient);
		return currentID;
	}

	@Override
	public void removeClient(int clientID) throws RemoteException {
		callbackClients.remove(clientID);
	}

	@Override
	public void getWares(int id) throws RemoteException { // Can overload this, creating a getWares(int id, String category)
		getAllProducts();
		callbackClients.get(id).update("UpdatedWareList", wares);
	}

	@Override
	public void grosserProductList(int id) throws RemoteException {
		callbackClients.get(id).update("grosserProductList", dataModel.grosserProductList());
	}

	@Override
	public Pair<Boolean, ArrayList<Product>> sendOrder(int cvr, Basket basket, double sum) throws RemoteException { // TODO: Når Database er fikset, så skal kurven tilføjes til ordren
		System.out.println("SERVER: CVR: " + cvr + "\tORDER SIZE: " + basket.getBasket().size() + "\tSUM: " + sum);
		Pair<Boolean, ArrayList<Product>> verification = dataModel.verifyOrder(basket);
		if (verification.getKey()) {
			LocalDateTime orderTime = LocalDateTime.now();
			dataModel.createOrder(cvr, sum, orderTime);
			dataModel.createOrderSpec(basket, cvr, orderTime, sum);
		}
		return verification;
	}


	@Override
	public void getAllOrders(int clientId) throws RemoteException {
		ArrayList<Order> orders = dataModel.getAllOrders();
		callbackClients.get(clientId).updateAllOrders(orders);
	}

	@Override
	public void createProduct(Pair<Product, Integer> newProduct) throws RemoteException {
		dataModel.createProduct(newProduct);
	}

	@Override
	public void deleteWare(int productID) throws RemoteException{
		dataModel.delete(productID);
	}

	@Override
	public void changeAmount(Pair<Product, Integer> productWithNewAmount) throws RemoteException{
		dataModel.changeAmount(productWithNewAmount);
	}

}
