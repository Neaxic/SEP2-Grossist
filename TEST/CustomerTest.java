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
import shared.wares.Product;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.MissingResourceException;

import static org.junit.jupiter.api.Assertions.*;
// Young og en overdosis af Anastasia Te. Det her kan umuligt være sundt

public class CustomerTest {
	static class CustomerBrowserTest {
		static Model model;
		private CustomerBrowserViewModel vm;

		@BeforeAll
		static void init() {
			try {
				RMIServerInterface server = new RMIServer();
				server.startServer();
			} catch (SQLException | RemoteException | AlreadyBoundException throwable) {
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

	static class CustomerBasketTest {
		static Model model;
		private BasketViewModel vm;

		@BeforeAll
		static void init() {
			try {
				RMIServerInterface server = new RMIServer();
				server.startServer();
			} catch (SQLException | RemoteException | AlreadyBoundException throwable) {
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
			Product p = new Alcohol("Soplica Malinowa", "Liter", LocalDate.now(), 420, 10, 10, 10, "Poland", 30d, "Vodka");
			int a = 1;
			model.addToBasket(p, a);
			assertFalse(model.getMyBasket().getBasket().isEmpty());
			vm.removeFromBasket(new ProductAndInt(p.getWareName(), p.getWareNumber(), a));
			assertTrue(model.getMyBasket().getBasket().isEmpty());
		}

		@Test
		void sendOrder() {
			LoginManager.cvr = 1;
			assertThrows(MissingResourceException.class, () -> vm.sendOrder());
			Product p = new Alcohol("Soplica Malinowa", "Liter", LocalDate.now(), 420, 10, 10, 10, "Poland", 30d, "Vodka");
			int a = 1;
			model.addToBasket(p, a);
			assertDoesNotThrow(() -> vm.sendOrder());
		}

		@Test
		void loadAllProducts() {
			assertEquals(model.getMyBasket().getBasket(), vm.loadAllProducts());
		}
	}
}
