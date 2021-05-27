package server.network;

import javafx.util.Pair;
import server.model.ServerModel;
import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.objects.Basket;
import shared.objects.CustomerContainer;
import shared.util.Util;
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
import java.util.List;
import java.util.Map;

// Andreas Østergaard, Andreas Young, Frederik Bergmann

public class RMIServer implements RMIServerInterface {
	private HashMap<Integer, CallbackClient> callbackClients;
	private List<Product> wares;

	// Dummy Data:
	private final ServerModel serverModel = new ServerModel();

	public RMIServer() throws SQLException {
		callbackClients = new HashMap<>();
	}

	@Override
	public void startServer() throws RemoteException, AlreadyBoundException {
		System.out.println("Server starting...");
		Registry registry = LocateRegistry.createRegistry(1098);
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
		callbackClients.get(id).update("grosserProductList", serverModel.grosserProductList());
	}

	@Override
	public Pair<Boolean, ArrayList<Product>> sendOrder(int cvr, Basket basket) throws RemoteException, SQLException {
		Pair<Boolean, ArrayList<Product>> verification = serverModel.verifyOrder(basket);
		if (verification.getKey()) {
			serverModel.createOrder(cvr, LocalDateTime.now(), basket);
		}
		return verification;
	}


	@Override
	public void getAllOrders(int clientId) throws RemoteException {
		callbackClients.get(clientId).updateAllOrders(serverModel.getAllOrders());
	}

	@Override
	public void createProduct(Pair<Product, Integer> newProduct) throws RemoteException, SQLException, IllegalArgumentException {
		serverModel.createProduct(newProduct);
	}

	@Override
	public void deleteWare(Product product) throws RemoteException {
		serverModel.delete(product);
	}

	@Override
	public void increaseAmountInSystem(Pair<Product, Integer> productAndAmountToAdd) throws RemoteException {
		boolean b = serverModel.increaseAmount(productAndAmountToAdd);
	}

	@Override
	public void reduceAmountInSystem(Pair<Product, Integer> productAndAmountToRemove) throws RemoteException {
		boolean b = serverModel.reduceAmount(productAndAmountToRemove);
	}

	@Override
	public boolean addCustomer(CustomerContainer customer) throws RemoteException {
		return serverModel.addCustomer(customer);
	}

	@Override
	public Map<Integer, String> getLoginInfo()
	{
		return serverModel.getLoginInfo();
	}

	@Override
	public void deleteLatestOrder() {
		serverModel.deleteLatestOrder();
	}

	@Override
	public void removeCustomer(int customerCVR) throws RemoteException, SQLException {
		serverModel.removeCustomer(customerCVR);
	}


	private void getAllProducts() {
		wares = serverModel.getAllProducts();
	}
}
