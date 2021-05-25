import client.core.LoginManager;
import client.core.factories.ClientFactory;
import client.core.factories.ModelFactory;
import client.customerclient.model.CustomerModelInterface;
import client.customerclient.views.customerbasket.BasketViewModel;
import client.customerclient.views.customerbrowser.CustomerBrowserViewModel;
import client.network.GrosserClient;
import javafx.util.Pair;
import org.junit.jupiter.api.*;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.objects.ProductAndInt;
import shared.wares.Alcohol;
import shared.wares.Drink;
import shared.wares.FruitsAndVegetable;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.MissingResourceException;

import static org.junit.jupiter.api.Assertions.*;
// Andreas Young

public class CustomerTest {
	static Product testProduct1, testProduct2, testProduct3;
	static int a = 1;
	static CustomerModelInterface customerModel;

	@Test
	void jUnitTest() {
		assertTrue(false != true, "JUnit does not seem to work at the current time, please try again later...");
	}

	static class CustomerBrowserVMTest {
		private CustomerBrowserViewModel vm;

		@BeforeAll
		static void init() throws SQLException {
			try {
				RMIServerInterface server = new RMIServer();
				assertDoesNotThrow(server::startServer);
			} catch (SQLException throwable) {
				throwable.printStackTrace();
			}
			ClientFactory.getInstance().getClient().start();
			GrosserClient grosser = ClientFactory.getInstance().getGrosserClient();
			testProduct1 = new Alcohol("Royal Export", "Liter", LocalDate.now().plusMonths(10), 2001, 10, 4.5, "Royal Unibrew", "", "Danmark", 4.6, "Øl");
			testProduct2 = new Drink("Jolly Cola Sukkerfri", "Liter", LocalDate.now().plusMonths(3), 2002, 20, 5.95, "Jolly", "Sukkerfri", "Sodavand");
			testProduct3 = new FruitsAndVegetable("Banan", "Kg", LocalDate.now().plusDays(7), 2003, 1, 2.3, "Palmer", "GMO", "Spanien");
			grosser.createProduct(new Pair<>(testProduct1, 100));
			grosser.createProduct(new Pair<>(testProduct2, 100));
			grosser.createProduct(new Pair<>(testProduct3, 100));
		}

		@BeforeEach
		void setUp() throws SQLException {
			vm = new CustomerBrowserViewModel();
			customerModel = ModelFactory.getInstance().getCustomerModel();
		}

		@AfterAll
		static void adminCleanUp() {
			GrosserClient admin = ClientFactory.getInstance().getGrosserClient();
			admin.deleteWare(testProduct1);
			admin.deleteWare(testProduct2);
			admin.deleteWare(testProduct3);
		}

		@Test
		void loadAllProductsToModel() {
			assertTrue(vm.activeItemListProperty().isEmpty());
			vm.loadAllProductsToModel();
			assertFalse(vm.activeItemListProperty().isEmpty());
		}

		@Test
		void addToBasket() {
			vm.loadAllProductsToModel();
			assertTrue(customerModel.getMyBasket().getBasket().isEmpty());
			vm.addToBasket(2001, 1);
			System.out.println(vm.activeItemListProperty().get().get(1).getWareNumber());
			assertFalse(customerModel.getMyBasket().getBasket().isEmpty());
		}
	}

	static class CustomerBasketVMTest {
		private BasketViewModel vm;

		@BeforeAll
		static void init() throws SQLException {
			try {
				RMIServerInterface server = new RMIServer();
				assertDoesNotThrow(server::startServer);
			} catch (SQLException throwable) {
				throwable.printStackTrace();
			}
			ClientFactory.getInstance().getClient().start();
			GrosserClient grosser = ClientFactory.getInstance().getGrosserClient();
			testProduct1 = new Alcohol("Royal Export", "Liter", LocalDate.now().plusMonths(10), 2001, 10, 4.5, "Royal Unibrew", "", "Danmark", 4.6, "Øl");
			testProduct2 = new Drink("Jolly Cola Sukkerfri", "Liter", LocalDate.now().plusMonths(3), 2002, 20, 5.95, "Jolly", "Sukkerfri", "Sodavand");
			testProduct3 = new FruitsAndVegetable("Banan", "Kg", LocalDate.now().plusDays(7), 2003, 1, 2.3, "Palmer", "GMO", "Spanien");
			grosser.createProduct(new Pair<>(testProduct1, 100));
			grosser.createProduct(new Pair<>(testProduct2, 100));
			grosser.createProduct(new Pair<>(testProduct3, 100));
		}

		@BeforeEach
		void setUp() {
			vm = new BasketViewModel();
			customerModel = ModelFactory.getInstance().getCustomerModel();
		}

		@AfterAll
		static void adminCleanUp() {
			GrosserClient admin = ClientFactory.getInstance().getGrosserClient();
			admin.deleteWare(testProduct1);
			admin.deleteWare(testProduct2);
			admin.deleteWare(testProduct3);
		}

		@Test
		void removeFromBasket() {
			customerModel.addToBasket(testProduct1, a);
			assertFalse(customerModel.getMyBasket().getBasket().isEmpty());
			vm.removeFromBasket(new ProductAndInt(testProduct1.getWareName(), testProduct1.getWareNumber(), a));
			assertTrue(customerModel.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void sendOrder() {
			LoginManager.cvr = 1;
			assertThrows(MissingResourceException.class, () -> vm.sendOrder());
			customerModel.addToBasket(testProduct1, a);
			assertDoesNotThrow(() -> vm.sendOrder());
			ClientFactory.getInstance().getGrosserClient().deleteLatestOrder();
		}

		@Test
		void loadAllProducts() {
			assertEquals(customerModel.getMyBasket().getBasket(), vm.loadAllProducts());
		}
	}

	static class CustomerCustomerModelTest {
		@BeforeAll
		static void init() throws SQLException {
			try {
				RMIServerInterface server = new RMIServer();
				assertDoesNotThrow(server::startServer);
			} catch (SQLException throwable) {
				throwable.printStackTrace();
			}
			ClientFactory.getInstance().getClient().start();
			GrosserClient grosser = ClientFactory.getInstance().getGrosserClient();
			testProduct1 = new Alcohol("Royal Export", "Liter", LocalDate.now().plusMonths(10), 2001, 10, 4.5, "Royal Unibrew", "", "Danmark", 4.6, "Øl");
			testProduct2 = new Drink("Jolly Cola Sukkerfri", "Liter", LocalDate.now().plusMonths(3), 2002, 20, 5.95, "Jolly", "Sukkerfri", "Sodavand");
			testProduct3 = new FruitsAndVegetable("Banan", "Kg", LocalDate.now().plusDays(7), 2003, 1, 2.3, "Palmer", "GMO", "Spanien");
			grosser.createProduct(new Pair<>(testProduct1, 100));
			grosser.createProduct(new Pair<>(testProduct2, 100));
			grosser.createProduct(new Pair<>(testProduct3, 100));
		}

		@BeforeEach
		void setUp() {
			customerModel = ModelFactory.getInstance().getCustomerModel();
			LoginManager.cvr = 1;
		}

		@AfterEach
		void tearDown() {
			ModelFactory.getInstance().reset();
			customerModel = null;
		}

		@AfterAll
		static void adminCleanUp() {
			GrosserClient admin = ClientFactory.getInstance().getGrosserClient();
			admin.deleteWare(testProduct1);
			admin.deleteWare(testProduct2);
			admin.deleteWare(testProduct3);
		}

		@Test
		void addToBasket() {
			assertTrue(customerModel.getMyBasket().getBasket().isEmpty());
			assertDoesNotThrow(() -> customerModel.addToBasket(testProduct1, a));
			assertFalse(customerModel.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void removeFromBasket() {
			assertDoesNotThrow(() -> customerModel.removeFromBasket(testProduct1)); // Documentation does not state, it shall throw an Error when the basket is empty and you attempt to remove an item from it
			customerModel.addToBasket(testProduct1, a);
			assertFalse(customerModel.getMyBasket().getBasket().isEmpty());
			assertDoesNotThrow(() -> customerModel.removeFromBasket(testProduct1));
			assertTrue(customerModel.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void changeAmount() {
			int n = 2;
			customerModel.addToBasket(testProduct1, a);
			assertDoesNotThrow(() -> customerModel.changeAmount(testProduct1, n));
			assertEquals(n, customerModel.getMyBasket().getAmount(testProduct1));
		}

		@Test
		void updateWares() { // Unsure of how we should go about testing this
			assertDoesNotThrow(() -> customerModel.updateWares());
			assertFalse(customerModel.getAllWares().isEmpty());
		}

		@Test
		void getMyBasket() {
			assertNotNull(customerModel.getMyBasket());
			assertTrue(customerModel.getMyBasket().getBasket().isEmpty());
			customerModel.addToBasket(testProduct1, a);
			assertFalse(customerModel.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void sendOrder() {
			assertFalse(() -> customerModel.sendOrder(customerModel.getMyBasket(), 10).getKey());
			customerModel.addToBasket(testProduct1, a);
			assertDoesNotThrow(() -> customerModel.sendOrder(customerModel.getMyBasket(), 10));
			ClientFactory.getInstance().getGrosserClient().deleteLatestOrder();
		}

		@Test
		void getAllWares() {
			assertTrue(customerModel.getAllWares().isEmpty());
			customerModel.updateWares();
			assertFalse(customerModel.getAllWares().isEmpty());
		}

	}
}
