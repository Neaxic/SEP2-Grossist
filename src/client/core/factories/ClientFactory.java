package client.core.factories;

import client.network.Client;
import client.network.GrosserClient;
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

	//TODO: custommerClient der ligner grosser. 1 for login, og derefter en for hver type bruger

	public GrosserClient getGrosserClient() {
		if (client == null) {
			client = new RMIClient();
		}
		return (GrosserClient) client;
	}
}
