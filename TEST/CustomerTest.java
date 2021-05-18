import client.core.ClientFactory;
import client.core.LoginManager;
import client.core.ModelFactory;
import client.customerclient.model.CustomerModel;
import client.customerclient.model.CustomerModelInterface;
import client.customerclient.views.customerbasket.BasketViewModel;
import client.customerclient.views.customerbasket.ProductAndInt;
import client.customerclient.views.customerbrowser.CustomerBrowserViewModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.server.RMIServer;
import shared.network.RMIServerInterface;
import shared.wares.Alcohol;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.MissingResourceException;

import static org.junit.jupiter.api.Assertions.*;
// Andreas Young

public class CustomerTest {
	static Product p = new Alcohol("Soplica Malinowa", "Liter", LocalDate.now(), 420, 10, 10, 10, "Poland", 30d, "Vodka");
	static int a = 1;
	static CustomerModelInterface customerModel;

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
			//customerModel = (CustomerModel) ModelFactory.getInstance().getCustomerModel();
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
			vm.addToBasket(1, 1);
			assertFalse(customerModel.getMyBasket().getBasket().isEmpty());
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
			customerModel = (CustomerModel) ModelFactory.getInstance().getCustomerModel();
		}

		@Test
		void removeFromBasket() {
			customerModel.addToBasket(p, a);
			assertFalse(customerModel.getMyBasket().getBasket().isEmpty());
			vm.removeFromBasket(new ProductAndInt(p.getWareName(), p.getWareNumber(), a));
			assertTrue(customerModel.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void sendOrder() {
			LoginManager.cvr = 1;
			assertThrows(MissingResourceException.class, () -> vm.sendOrder());
			customerModel.addToBasket(p, a);
			assertDoesNotThrow(() -> vm.sendOrder());
		}

		@Test
		void loadAllProducts() {
			assertEquals(customerModel.getMyBasket().getBasket(), vm.loadAllProducts());
		}
	}

	static class CustomerCustomerModelTest
	{
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
			customerModel = (CustomerModel) ModelFactory.getInstance().getCustomerModel();
		}

		@Test
		void addToBasket() {
			assertTrue(customerModel.getMyBasket().getBasket().isEmpty());
			assertDoesNotThrow(() -> customerModel.addToBasket(p, a));
			assertFalse(customerModel.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void removeFromBasket() {
			assertDoesNotThrow(() -> customerModel.removeFromBasket(p)); // Documentation does not state, it shall throw an Error when the basket is empty and you attempt to remove an item from it
			customerModel.addToBasket(p, a);
			assertFalse(customerModel.getMyBasket().getBasket().isEmpty());
			assertDoesNotThrow(() -> customerModel.removeFromBasket(p));
			assertTrue(customerModel.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void changeAmount() {
			int n = 2;
			customerModel.addToBasket(p, a);
			assertDoesNotThrow(() -> customerModel.changeAmount(p, n));
			assertEquals(n, customerModel.getMyBasket().getAmount(p));
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
			customerModel.addToBasket(p, a);
			assertFalse(customerModel.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void sendOrder() {
			assertThrows(MissingResourceException.class, () -> customerModel
					.sendOrder(customerModel.getMyBasket(), 10));
			customerModel.addToBasket(p, a);
			assertDoesNotThrow(() -> customerModel
					.sendOrder(customerModel.getMyBasket(), 10));
		}

		@Test
		void getAllWares() {
			assertTrue(customerModel.getAllWares().isEmpty());
			customerModel.updateWares();
			assertFalse(customerModel.getAllWares().isEmpty());
		}

		@Test
		void getCategory() {
			assertTrue(customerModel.getCategory("Drink").isEmpty());
			customerModel.updateWares();
			assertTrue(customerModel.getAllWares().containsAll(customerModel.getCategory("Drink")));
		}
	}
}
