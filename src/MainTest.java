import client.network.CallbackClient;

import java.awt.event.ActionListener;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hvis man vil teste klientens metoder
        System.out.println("Test Klient? (y / n)");
        String userResponse = scanner.next();
        if (userResponse.equalsIgnoreCase("y"))
            clientTest();

        // Her kan eventuelt tilføjes flere hvis man vil teste andre ting

    }


    private static void clientTest() {
        CallbackClient client = new CallbackClient();
        client.start();
        System.out.print("> Client started... \nShould print 0 here: ");
        client.printNumber();
        System.out.println("> Input number to set for the Server");
        int num = Integer.parseInt(new Scanner(System.in).next());
        client.setServerNumber(num);
        System.out.println("> Client has updated server with a new Number");
        System.out.println("Sent to Server: " + num + "\tClient Number: ");
        client.printNumber();
        System.out.println("> Updating Client number to match the servers number");
        client.updateClientNumber();
        client.printNumber();
    }
}
