import client.core.LoginManager;
import client.core.factories.ClientFactory;
import client.network.Client;
import client.network.GrosserClient;
import javafx.util.Pair;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.objects.Basket;
import shared.wares.Alcohol;
import shared.wares.Drink;
import shared.wares.FruitsAndVegetable;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// Young
public class ReceivingAndOrderTest {
	Basket testBasket;
	static Product testProduct1, testProduct2, testProduct3;
	static Client customer;
	static GrosserClient grosser;

	@BeforeAll
	static void init() throws SQLException {
		try {
			RMIServerInterface server = new RMIServer();
			assertDoesNotThrow(server::startServer);
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}
		ClientFactory.getInstance().getClient().start();
		customer = ClientFactory.getInstance().getClient();
		LoginManager.cvr = 1;
		grosser = ClientFactory.getInstance().getGrosserClient();
		testProduct1 = new Alcohol("Royal Export", "Liter", LocalDate.now().plusMonths(10), 2001, 10, 4.5, "Royal Unibrew", "", "Danmark", 4.6, "Øl");
		testProduct2 = new Drink("Jolly Cola Sukkerfri", "Liter", LocalDate.now().plusMonths(3), 2002, 20, 5.95, "Jolly", "Sukkerfri", "Sodavand");
		testProduct3 = new FruitsAndVegetable("Banan", "Kg", LocalDate.now().plusDays(7), 2003, 1, 2.3, "Palmer", "GMO", "Spanien");
		grosser.createProduct(new Pair<>(testProduct1, 100));
		grosser.createProduct(new Pair<>(testProduct2, 100));
		grosser.createProduct(new Pair<>(testProduct3, 100));
	}

	@AfterAll
	static void adminCleanUp() {
		GrosserClient admin = ClientFactory.getInstance().getGrosserClient();
		admin.deleteWare(testProduct1);
		admin.deleteWare(testProduct2);
		admin.deleteWare(testProduct3);
	}

	@BeforeEach
	void setUp() {
		testBasket = new Basket();
		testBasket.addProduct(testProduct1, 1);
		testBasket.addProduct(testProduct2, 2);
		testBasket.addProduct(testProduct3, 3);
	}

	@Test
	void clientSendOrder() throws SQLException {
		assertEquals(true, customer.sendOrder(LoginManager.cvr, testBasket).getKey());
	}

	@Test
	void clientFakeCVR() {
		assertThrows(SQLException.class, () -> customer.sendOrder(-1, testBasket));
	}

	@Test
	void clientNoBasket() throws SQLException {
		assertEquals(false, customer.sendOrder(LoginManager.cvr, null));
	}
}
