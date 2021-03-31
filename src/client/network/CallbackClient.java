package client.network;

import shared.network.RMIServerInterface;
import shared.util.Util;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CallbackClient implements Remote {
    private RMIServerInterface server;
    private int number;

    public CallbackClient() {
        number = 0;
    }

    public void start() {
        try {
            System.out.println("Connecting to Server...");
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (RMIServerInterface) registry.lookup(Util.SERVERNAME); // Util klassen har gemt servernavnet som en statisk variabel
            UnicastRemoteObject.exportObject(this, 0);
            server.ping();
            System.out.println("Connection Established");

        } catch (RemoteException | NotBoundException e) {
            System.out.println("CallbackClient [start()] > \t" + e.getMessage());
        }
    }

    public void printNumber() {
        System.out.println(number);
    }

    public void updateClientNumber(){
        number = server.getThingFromServer();
    }

    public void setServerNumber(int number){
        server.setThingOnServer(number);
    }
}
