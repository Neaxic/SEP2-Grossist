package shared.network;

import shared.wares.NewProduct;
import shared.wares.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface CallbackClient extends Remote {
	void update(HashMap<NewProduct, Integer> list) throws RemoteException;
}
