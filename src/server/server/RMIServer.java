package server.server;

import TEMP.Alcohol;
import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.util.Util;
import shared.wares.Product;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;

public class RMIServer implements RMIServerInterface {
	private HashMap<Integer, CallbackClient> callbackClients = new HashMap<>();

	// Dummy Data:
	private HashMap<Product, Integer> wares = new HashMap<>();

	private void createDummyData() {
		wares.put(new Alcohol("Soplica Wisniowa", "Vodka", "30%", "Poland", "Soplica", new Date(), new Date(), 20.0, 100, 10), 1000);
//		wares.put(new Colonial("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
//		wares.put(new CooledAndDairy("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
//		wares.put(new Drink("", "", "", new Date(), new Date(), 20, 209, 20), 1000);
//		wares.put(new Frozen("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
//		wares.put(new FruitAndVegetable("", "", "", new Date(), "banana", 20, 20, 20), 1000);
//		wares.put(new MeatAndFish("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
	}


	@Override
	public void startServer() throws RemoteException, AlreadyBoundException {
		System.out.println("Server starting...");
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind(Util.SERVERNAME, this);
		UnicastRemoteObject.exportObject(this, 0);
		createDummyData();
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
