package client.network;

import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.network.Subject;
import shared.util.Util;
import shared.wares.OLD_Product;
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

public class RMIClient implements Client, CallbackClient, Subject {
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
			System.out.println("RMIClient [start()] > \t" + e.getMessage());
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
			System.out.println("RMIClient [registerOnServer()] > \tServer Connection missing");
		}
	}

	@Override
	public void getWares() {
		try {
			server.getWares(clientID);
		} catch (RemoteException remoteException) {
			System.out.println("RMIClient [getWares()] > \t");
			remoteException.printStackTrace();
		}
	}

	@Override
	public void update(HashMap<String, ArrayList<Product>> list) { // Model.java is a listener
		support.firePropertyChange("UpdatedWareList", null, list);
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
