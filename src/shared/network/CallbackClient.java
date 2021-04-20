package shared.network;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CallbackClient extends Remote {
	void update(List<String[]> list) throws RemoteException;
}
