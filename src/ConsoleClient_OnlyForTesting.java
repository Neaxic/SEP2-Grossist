import client.network.Client;
import client.network.RMIClient;

import java.util.Scanner;

public class ConsoleClient_OnlyForTesting {
	public static void main(String[] args) {
		Client client = new RMIClient();
		client.start();
		while(true){
			new Scanner(System.in).nextLine();
			client.getWares();
		}
	}
}
