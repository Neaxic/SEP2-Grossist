package server.server;

import TEMP.Alcohol;
import server.model.DataModelImpl;
import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.util.Util;
import shared.wares.NewProduct;
import shared.wares.Product;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class RMIServer implements RMIServerInterface {
	private HashMap<Integer, CallbackClient> callbackClients = new HashMap<>();
	//private HashMap<Product, Integer> wares = new HashMap<>();
	private HashMap<NewProduct, Integer> wares = new HashMap<>();

	// Dummy Data:
	private DataModelImpl dataModel = new DataModelImpl();

	public RMIServer() throws SQLException
	{
	}

	private void createDummyData() {
//		wares.put(new Alcohol("Soplica Wisniowa", "Vodka", "30%", "Poland", "Soplica", new Date(), new Date(), 20.0, 100, 10), 1000);
//		wares.put(new Colonial("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
//		wares.put(new CooledAndDairy("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
//		wares.put(new Drink("", "", "", new Date(), new Date(), 20, 209, 20), 1000);
//		wares.put(new Frozen("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
//		wares.put(new FruitAndVegetable("", "", "", new Date(), "banana", 20, 20, 20), 1000);
//		wares.put(new MeatAndFish("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
	}

	private void getAlcohol(){
		System.out.println("TEST ALCO: " +dataModel.getAlcohol());
	}

	public void getAllProducts(){
		System.out.println("HASHMAP FROM DB: " +dataModel.getAllProducts());
		//Skal vi have for hver at typerne: - Vi laver et nyt hashmap for at undgå at lave alt det gamle om -
		// (sådan vi får antal i mappet) - Man kunne også ændre det på kient siden tho
		for (Object i: dataModel.getAllProducts().get("Alcohol")) {
			wares.put((NewProduct) i, dataModel.getAllProducts().get("Alcohol").size());
		}
		System.out.println("ALL PROCUTS HASHMAP: " +wares);
	}

	@Override
	public void startServer() throws RemoteException, AlreadyBoundException {
		System.out.println("Server starting...");
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind(Util.SERVERNAME, this);
		UnicastRemoteObject.exportObject(this, 0);
		createDummyData();
		System.out.println("Connectiong to database... (This might take a while)");
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
	public void getWares(int id) throws RemoteException { // TODO: Can overload this, creating a getWares(int id, String category)
		callbackClients.get(id).update(wares);
	}
}
