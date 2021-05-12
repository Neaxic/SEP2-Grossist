package server.server;

import shared.network.RMIServerInterface;
import shared.util.Util;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

// Andreas Young

public class RMIServerRun {
    public static void main(String[] args)
        throws RemoteException, AlreadyBoundException, SQLException
    {
        RMIServerInterface grosserServer = new RMIServer();
        grosserServer.startServer();
    }
}
