package client.network;

import shared.network.CallbackClient;
import shared.network.RMIServerInterface;
import shared.util.Util;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIClient implements Client, CallbackClient {
	private RMIServerInterface server;
	private int serverID;

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
			serverID = server.registerClient(this);
		} catch (RemoteException remoteException) {
			System.out.println("RMIClient [registerOnServer()] > \tServer Connection missing");
		}
		System.out.println(serverID);
	}

	@Override
	public void getWares() {
		try {
			server.getWares(serverID);
		} catch (RemoteException remoteException) {
			System.out.println("RMIClient [getWares()] > \t" + remoteException.getMessage());
		}
	}

	@Override
	public void update(List<String[]> list) {
		for ( String[] subList : list) {
			for (String s : subList) {
				System.out.print(s + ", ");
			}
			System.out.print("\n");
		};
	}
}
