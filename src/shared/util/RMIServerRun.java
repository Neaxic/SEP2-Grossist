package shared.util;

import shared.network.RMIServerInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServerRun {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        System.out.println("Server starting...");
        RMIServerInterface server = new RMIServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind(Util.SERVERNAME, server);
        System.out.println("Server starded");
    }
}
