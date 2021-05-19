package server.network;

import shared.network.RMIServerInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
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
