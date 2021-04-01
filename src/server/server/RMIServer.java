package server.server;

import shared.network.RMIServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer implements RMIServerInterface {

    private int things = 5;

    public RMIServer() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
    }


    @Override
    public int getThingFromServer() throws RemoteException {
        return things;
    }

    @Override
    public void setThingOnServer(int val) throws RemoteException {
        things = val;
    }

    @Override
    public boolean ping() throws RemoteException{
        return true;
    }



}
