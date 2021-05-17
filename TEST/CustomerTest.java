import client.core.ClientFactory;
import client.core.LoginManager;
import client.core.ModelFactory;
import client.customerclient.model.Model;
import client.customerclient.views.customerbasket.BasketViewModel;
import client.customerclient.views.customerbasket.ProductAndInt;
import client.customerclient.views.customerbrowser.CustomerBrowserViewModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.server.RMIServer;
import shared.network.RMIServerInterface;
import shared.wares.Alcohol;
import shared.wares.Basket;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.MissingResourceException;

import static org.junit.jupiter.api.Assertions.*;
// Young og en overdosis af Anastasia Te. Det her kan umuligt være sundt

public class CustomerTest {
	static Product p = new Alcohol("Soplica Malinowa", "Liter", LocalDate.now(), 420, 10, 10, 10, "Poland", 30d, "Vodka");
	static int a = 1;
	static Model model;

	@Test
	void jUnitTest() {
		assertTrue(false != true, "JUnit does not seem to work at the current time, please try again later...");
	}

	static class CustomerBrowserVMTest {
		private CustomerBrowserViewModel vm;

		@BeforeAll
		static void init() {
			try {
				RMIServerInterface server = new RMIServer();
				assertDoesNotThrow(server::startServer);
			} catch (SQLException throwable) {
				throwable.printStackTrace();
			}
			ClientFactory.getInstance().getClient().start();
		}

		@BeforeEach
		void setUp() {
			vm = new CustomerBrowserViewModel();
			model = (Model) ModelFactory.getInstance().getCustomerModel();
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
			assertTrue(model.getMyBasket().getBasket().isEmpty());
			vm.addToBasket(1, 1);
			assertFalse(model.getMyBasket().getBasket().isEmpty());
		}
	}

	static class CustomerBasketVMTest {
		private BasketViewModel vm;

		@BeforeAll
		static void init() {
			try {
				RMIServerInterface server = new RMIServer();
				assertDoesNotThrow(server::startServer);
			} catch (SQLException throwable) {
				throwable.printStackTrace();
			}
			ClientFactory.getInstance().getClient().start();
		}

		@BeforeEach
		void setUp() {
			vm = new BasketViewModel();
			model = (Model) ModelFactory.getInstance().getCustomerModel();
		}

		@Test
		void removeFromBasket() {
			model.addToBasket(p, a);
			assertFalse(model.getMyBasket().getBasket().isEmpty());
			vm.removeFromBasket(new ProductAndInt(p.getWareName(), p.getWareNumber(), a));
			assertTrue(model.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void sendOrder() {
			LoginManager.cvr = 1;
			assertThrows(MissingResourceException.class, () -> vm.sendOrder());
			model.addToBasket(p, a);
			assertDoesNotThrow(() -> vm.sendOrder());
		}

		@Test
		void loadAllProducts() {
			assertEquals(model.getMyBasket().getBasket(), vm.loadAllProducts());
		}
	}

	static class CustomerModelTest {
		@BeforeAll
		static void init() {
			try {
				RMIServerInterface server = new RMIServer();
				assertDoesNotThrow(server::startServer);
			} catch (SQLException throwable) {
				throwable.printStackTrace();
			}
			ClientFactory.getInstance().getClient().start();
		}

		@BeforeEach
		void setUp() {
			model = (Model) ModelFactory.getInstance().getCustomerModel();
		}

		@Test
		void addToBasket() {
			assertTrue(model.getMyBasket().getBasket().isEmpty());
			assertDoesNotThrow(() -> model.addToBasket(p, a));
			assertFalse(model.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void removeFromBasket() {
			assertDoesNotThrow(() -> model.removeFromBasket(p)); // Documentation does not state, it shall throw an Error when the basket is empty and you attempt to remove an item from it
			model.addToBasket(p, a);
			assertFalse(model.getMyBasket().getBasket().isEmpty());
			assertDoesNotThrow(() -> model.removeFromBasket(p));
			assertTrue(model.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void changeAmount() {
			int n = 2;
			model.addToBasket(p, a);
			assertDoesNotThrow(() -> model.changeAmount(p, n));
			assertEquals(n, model.getMyBasket().getAmount(p));
		}

		@Test
		void updateWares() { // Unsure of how we should go about testing this
			assertDoesNotThrow(() -> model.updateWares());
			assertFalse(model.getAllWares().isEmpty());
		}

		@Test
		void getMyBasket() {
			assertNotNull(model.getMyBasket());
			assertTrue(model.getMyBasket().getBasket().isEmpty());
			model.addToBasket(p, a);
			assertFalse(model.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void sendOrder() {
			assertThrows(MissingResourceException.class, () -> model.sendOrder(model.getMyBasket(), 10));
			model.addToBasket(p, a);
			assertDoesNotThrow(() -> model.sendOrder(model.getMyBasket(), 10));
		}

		@Test
		void getAllWares() {
			assertTrue(model.getAllWares().isEmpty());
			model.updateWares();
			assertFalse(model.getAllWares().isEmpty());
		}

		@Test
		void getCategory() {
			assertTrue(model.getCategory("Drink").isEmpty());
			model.updateWares();
			assertTrue(model.getAllWares().containsAll(model.getCategory("Drink")));
		}
	}
}
