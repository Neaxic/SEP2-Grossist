package server.server;

import TEMP.*;
import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.util.Util;
import shared.wares.Product;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RMIServer implements RMIServerInterface {
	int currentID;
	private HashMap<Integer, CallbackClient> callbackClients = new HashMap<>();

	// Dummy Data:
	private List<Product> wares = new ArrayList<>();

	private void createDummyData() {
		wares.add(new Alcohol("", "", "", "", "", new Date(), new Date(), 20.0, 10, 10));
//		wares.add(new Colonial("", "", "", new Date(), new Date(), 20, 20, 20));
//		wares.add(new CooledAndDairy("", "", "", new Date(), new Date(), 20, 20, 20));
//		wares.add(new Drink("", "", "", new Date(), new Date(), 20, 209, 20));
//		wares.add(new Frozen("", "", "", new Date(), new Date(), 20, 20, 20));
//		wares.add(new FruitAndVegetable("", "", "", new Date(), "banana", 20, 20, 20));
//		wares.add(new MeatAndFish("", "", "", new Date(), new Date(), 20, 20, 20));
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
