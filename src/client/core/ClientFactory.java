package client.core;

import client.network.Client;
import client.network.RMIClient;

import java.rmi.RemoteException;

public class ClientFactory {

    private Client client;

    public Client getClient() {
        try {
            if (client == null) {
                client = new RMIClient();
            }
            return client;
        } catch (RemoteException e){
            System.out.println("Remote ClientFactory fail");
        }
        return client;
    }
}
