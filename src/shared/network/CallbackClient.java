package shared.network;

import shared.wares.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface CallbackClient extends Remote {
	void update(HashMap<String, ArrayList<Product>> list) throws RemoteException;
}
