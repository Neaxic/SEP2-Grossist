package client.core;

import client.network.Client;
import client.network.RMIClient;

// Andreas Østergaard, Frederik Bergmann

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
		if (client == null) {
			client = new RMIClient();
		}
		return client;
	}
}
