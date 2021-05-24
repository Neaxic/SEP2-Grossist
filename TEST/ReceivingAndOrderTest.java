import client.core.LoginManager;
import client.core.factories.ClientFactory;
import client.network.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.objects.Basket;
import shared.wares.Alcohol;
import shared.wares.Drink;
import shared.wares.FruitsAndVegetable;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceivingAndOrderTest {
	Basket testBasket;
	Product testProduct1, testProduct2, testProduct3;
	static Client userClient;

	@BeforeAll
	static void init() {
		try {
			RMIServerInterface server = new RMIServer();
			assertDoesNotThrow(server::startServer);
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}
		ClientFactory.getInstance().getClient().start();
		userClient = ClientFactory.getInstance().getClient();
		LoginManager.cvr = 1;
	}

	@BeforeEach
	void setUp() {
		testBasket = new Basket();
		testProduct1 = new Alcohol("Royal Export", "Liter", LocalDate.now().plusMonths(10), 2001, 10, 4.5, "Royal Unibrew", "", "Danmark", 4.6, "Øl");
		testProduct2 = new Drink("Jolly Cola Sukkerfri", "Liter", LocalDate.now().plusMonths(3), 2002, 20, 5.95, "Jolly", "Sukkerfri", "Sodavand");
		testProduct3 = new FruitsAndVegetable("Banan", "Kg", LocalDate.now().plusDays(7), 2003, 1, 2.3, "Palmer", "GMO", "Spanien");
		testBasket.addProduct(testProduct1, 1);
		testBasket.addProduct(testProduct2, 2);
		testBasket.addProduct(testProduct3, 3);
	}

	@Test
	void clientSendOrder() {
		System.out.println(testBasket.getBasket());
		assertEquals(true, userClient.sendOrder(LoginManager.cvr, testBasket));
	}

	@Test
	void serverReceivesCorrectOrder() {

	}
}
