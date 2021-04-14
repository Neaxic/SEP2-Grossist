package client.core;

import client.network.Client;
import client.network.RMIClient;

import java.rmi.RemoteException;

public class ModelFactory {
    //private Model model;
    private Client client;

    public ModelFactory(Client client){
        this.client = client;
    }

    // TODO: Kan ikke laves endnu, mangler model
    public void getClientModel() {
    }


    // TODO: Kan ikke laves endnu, mangler model
    public void getGrosserModel(){
    }
}
