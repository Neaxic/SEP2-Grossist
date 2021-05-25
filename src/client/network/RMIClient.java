package client.network;

import javafx.util.Pair;
import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.objects.CustomerContainer;
import shared.util.Util;
import shared.objects.Basket;
import shared.objects.Order;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Andreas Young og Andreas Østergaard

public class RMIClient implements Client, GrosserClient, CallbackClient, LoginInfoInterface {
	private RMIServerInterface server;
	private PropertyChangeSupport support;
	private int clientID;

	public RMIClient() {
		support = new PropertyChangeSupport(this);
	}

	@Override
	public void start() {
		System.out.println("Connecting to Server...");
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			server = (RMIServerInterface) registry.lookup(Util.SERVERNAME);
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		registerOnServer();
		System.out.println("Connection Established...");
	}

	/**
	 * Registers the client on connected server, expecting an ID returned which will be set on the client as a field
	 */
	private void registerOnServer() {
		try {
			clientID = server.registerClient(this);
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
	}

	@Override
	public void getWares() {
		try {
			server.getWares(clientID);
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
	}

	public Pair<Boolean, ArrayList<Product>> sendOrder(int cvr, Basket basket) throws SQLException {
		try {
			return server.sendOrder(cvr, basket);
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
		return new Pair<>(false, null);
	}


	public void getAllOrders() {
		try {
			server.getAllOrders(clientID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createProduct(Pair<Product, Integer> newProduct) throws SQLException, IllegalArgumentException {
		try {
			server.createProduct(newProduct);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(String info, List list) { // CustomerModel.java is a listener
		support.firePropertyChange(info, null, list);
	}

	@Override
	public void updateAllOrders(List<Order> orders) {
		support.firePropertyChange("orderList", null, orders);
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	@Override
	public void requestGrosserProducts() {
		try {
			server.grosserProductList(clientID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteWare(Product ware) {
		try {
			server.deleteWare(ware);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void increaseStock(Pair<Product, Integer> productAndAmountToIncrease) {
		try {
			server.increaseAmountInSystem(productAndAmountToIncrease);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reduceStock(Pair<Product, Integer> productAndAmountToReduce) {
		try {
			server.reduceAmountInSystem(productAndAmountToReduce);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteLatestOrder() {
		try {
			server.deleteLatestOrder();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override public boolean addCustomer(CustomerContainer customer)
	{
		try
		{
			return server.addCustomer(customer);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<Integer, String> getLoginInfo()
	{
		try
		{
			return server.getLoginInfo();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void removeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
}
