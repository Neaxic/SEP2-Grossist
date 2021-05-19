package client.network;

import javafx.util.Pair;
import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.util.Util;
import shared.wares.Basket;
import shared.wares.Order;
import shared.wares.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

// Andreas Young og Andreas Østergaard

public class RMIClient implements Client, GrosserClient, CallbackClient {
	private RMIServerInterface server;
	private PropertyChangeSupport support;
	private int clientID;

	public RMIClient() {
		support = new PropertyChangeSupport(this);
	}

	@Override
	public void start() {
		System.out.println("Connecting to Server...");//SOUT
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			server = (RMIServerInterface) registry.lookup(Util.SERVERNAME);
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException | NotBoundException e) {
			System.out.println("RMIClient [start()] > \t" + e.getMessage());//SOUT
		}
		registerOnServer();
		System.out.println("Connection Established...");//SOUT
	}

	/**
	 * Registers the client on connected server, expecting an ID returned which will be set on the client as a field
	 */
	private void registerOnServer() {
		try {
			clientID = server.registerClient(this);
		} catch (RemoteException remoteException) {
			System.out.println("RMIClient [registerOnServer()] > \tServer Connection missing"); //SOUT
		}
	}

	@Override
	public void getWares() {
		try {
			server.getWares(clientID);
		} catch (RemoteException remoteException) {
			System.out.println("RMIClient [getWares()] > \t");//SOUT
			remoteException.printStackTrace();
		}
	}

	public Pair<Boolean, ArrayList<Product>> sendOrder(int cvr, Basket basket, double sum) {
		try {
			return server.sendOrder(cvr, basket, sum);
		} catch (RemoteException remoteException) {
			System.out.println("RMICLIENT [sendOrder()] > \t");//SOUT
			remoteException.printStackTrace();
		}
		return new Pair<>(false, null);
	}


	public void getAllOrders() {
		try {
			server.getAllOrders(clientID);
		} catch (RemoteException e) {
			System.out.println("RMICLIENT [getAllOrders()] > \t");//SOUT
			e.printStackTrace();
		}
	}

	@Override
	public void createProduct(Pair<Product, Integer> newProduct) {
		try {
			server.createProduct(newProduct);
		} catch(RemoteException e) {
			System.out.println("RMICLIENT [createProduct()] > \t");//SOUT
			e.printStackTrace();
		}
	}

	@Override
	public void update(HashMap<String, ArrayList<Product>> list) { // CustomerModel.java is a listener
		support.firePropertyChange("UpdatedWareList", null, list);
	}

	@Override
	public void updateAllOrders(ArrayList<Order> orders) {
		support.firePropertyChange("orderList", null, orders);
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	@Override
	public void removeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
}
