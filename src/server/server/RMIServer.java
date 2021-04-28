package server.server;

import server.model.DataModelImpl;
import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.util.Util;
import shared.wares.OLD_Product;
import shared.wares.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class RMIServer implements RMIServerInterface {
	private HashMap<Integer, CallbackClient> callbackClients = new HashMap<>();
	private HashMap<OLD_Product, Integer> wares = new HashMap<>();
	private HashMap<Product, Integer> wares2 = new HashMap<>();

	// Dummy Data:
	private DataModelImpl dataModel = new DataModelImpl();

	public RMIServer() throws SQLException
	{
	}

	private void createDummyData() {
		wares2.put(new Alcohol("Soplica Wisniowa", "L", LocalDate.now(), 123, 20, 70, 100, "Poland", 30, "Vodka"), 1000);
//		wares2.put(new Colonial("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
//		wares2.put(new CooledAndDairy("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
//		wares2.put(new Drink("", "", "", new Date(), new Date(), 20, 209, 20), 1000);
//		wares2.put(new Frozen("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
//		wares2.put(new FruitAndVegetable("", "", "", new Date(), "banana", 20, 20, 20), 1000);
//		wares2.put(new MeatAndFish("", "", "", new Date(), new Date(), 20, 20, 20), 1000);
	}

	private void getAlcohol(){
		for (Product i : dataModel.getAlcohol())
		{
			//wares.put(i, dataModel.getAlcohol().size());
		}
		System.out.println("TEST ALCO: " +dataModel.getAlcohol());
	}
	private void getAllProdcts(){
		for (Product i : dataModel.getAlcohol())
		{
			//wares.put(i, dataModel.getAlcohol().size());
		}
		System.out.println("TEST ALCO: " +dataModel.getAlcohol());
	}

	public void getAllProducts(){
		System.out.println("HASHMAP FROM DB: " +dataModel.getAllProducts());
		for (Object i: dataModel.getAllProducts().get("Alcohol")) {
			wares2.put((Product) i, dataModel.getAllProducts().get("Alcohol").size());
		}
		System.out.println(wares2);
	}

	@Override
	public void startServer() throws RemoteException, AlreadyBoundException {
		System.out.println("Server starting...");
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind(Util.SERVERNAME, this);
		UnicastRemoteObject.exportObject(this, 0);
		createDummyData();
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
	public void getWares(int id) throws RemoteException { // TODO: Can overload this, creating a getWares(int id, String category)
		callbackClients.get(id).update(wares2);
	}
}
