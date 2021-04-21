package server.server;

import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.util.Util;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RMIServer implements RMIServerInterface {
	int currentID = 0;
	private HashMap<Integer, CallbackClient> callbackClients = new HashMap<>();

	// Dummy Data:
	private List<String[]> wares = new ArrayList<>();

	private void createDummyData() {
		wares.add(new String[]{"itemName", "itemPrice", "itemStock", "expectedDeliveryTime", "bestBefore"});
		wares.add(new String[]{"pork chops", "30", "1200", "10", "02-10-2021"});
		wares.add(new String[]{"tenderloin", "35", "1240", "7", "04-09-2021"});
		wares.add(new String[]{"chicken breasts", "20", "1400", "15", "20-08-2021"});
		wares.add(new String[]{"frozen peas", "10", "3800", "21", "18-05-2022"});
		wares.add(new String[]{"dignity", "wut?", "0", "00-00-0000", "10-04-2021"}); //Feels bad man
		wares.add(new String[]{"fanta", "15", "2300", "8", "09-06-2021"});
		wares.add(new String[]{"soplica malinowa", "50", "800", "30", "31-07-2022"});
		wares.add(new String[]{"canned beans", "14", "10000", "14", "idk man, forever?"});
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
		} while (callbackClients.get(currentID) != null);
		callbackClients.put(currentID, callbackClient);
		return currentID;
	}

	@Override
	public void removeClient(int clientID) throws RemoteException{
		callbackClients.remove(clientID);
	}

	@Override
	public void getWares(int id) throws RemoteException { // TODO: Can overload this, creating a getWares(int id, String category)
		callbackClients.get(id).update(wares);
	}
}
