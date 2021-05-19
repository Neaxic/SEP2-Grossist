package shared.network;

import shared.wares.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

// Andreas Young

public interface CallbackClient extends Remote {
	void update(String info, HashMap list) throws RemoteException;

	void updateAllOrders(ArrayList<Order> orders) throws RemoteException;
}
