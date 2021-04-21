package client.core;

import client.customerclient.model.CustomerModelInterface;
import client.customerclient.model.Model;
import client.network.Client;
import client.network.RMIClient;

import java.rmi.RemoteException;

public class ModelFactory {
    private static ModelFactory instance;
    CustomerModelInterface model;

    private ModelFactory() {
    }

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    public CustomerModelInterface getCustomerModel() {
        if (model == null) {
            model = new Model(ClientFactory.getInstance().getClient());
        }
        return model;
    }
}
