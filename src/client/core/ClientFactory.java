package client.core;

import client.network.Client;
import client.network.RMIClient;

public class ClientFactory {
	private static ClientFactory instance;
	private Client client;

	private ClientFactory() {
	}

	public static ClientFactory getInstance() {
		if (instance == null) {
			instance = new ClientFactory();
		}
		return instance;
	}

	public Client getClient() {
		try {
			if (client == null) {
				client = new RMIClient();
			}
			return client;

			//kun fordi vores client ikke er remote endnu - skal være remote
		} catch (Exception e) {
			System.out.println("Remote ClientFactory fail");
		}
		return client;
	}
}
