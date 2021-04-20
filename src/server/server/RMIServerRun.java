package server.server;

import shared.network.RMIServerInterface;
import shared.util.Util;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServerRun {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        RMIServerInterface grosserServer = new RMIServer();
        grosserServer.startServer();
    }
}
