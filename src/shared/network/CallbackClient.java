package shared.network;

import shared.wares.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CallbackClient extends Remote {
	void update(List<Product> list) throws RemoteException;
}
