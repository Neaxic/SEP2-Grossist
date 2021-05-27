import client.core.factories.ClientFactory;
import client.grosserclient.views.grosseraddcustomer.GrosserAddCustomerViewModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.objects.CustomerContainer;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

// Young

public class GrosserAddCustomerTest {
	static private GrosserAddCustomerViewModel vm;
	private CustomerContainer c;

	@BeforeAll
	static void init() {
		try {
			RMIServerInterface server = new RMIServer();
			assertDoesNotThrow(server::startServer);
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}
		ClientFactory.getInstance().getClient().start();

		vm = new GrosserAddCustomerViewModel();
	}

	@Test
	void validCustomer() {
		c = new CustomerContainer(99999999, "Skanderborgvej 123", "Kodeord123", "Thomas");
		assertDoesNotThrow(() -> vm.addCustomer(c));
		ClientFactory.getInstance().getGrosserClient().removeCustomer(99999999); // Admin Cleanup of Customer
		assertEquals(true, vm.addCustomer(c));
		assertEquals(false, vm.addCustomer(c)); //Second customer with same CVR should not be made and should return false
		ClientFactory.getInstance().getGrosserClient().removeCustomer(99999999); // Admin Cleanup of Customer
	}

	@Test
	void invalidCVR() {
		c = new CustomerContainer(-1, "Skanderborgvej 123", "Kodeord123", "Thomas");
		assertThrows(IllegalArgumentException.class, () -> vm.addCustomer(c));
	}

	@Test
	void invalidAddress() {
		c = new CustomerContainer(99999999, "", "Kodeord123", "Thomas");
		assertThrows(IllegalArgumentException.class, () -> vm.addCustomer(c));
	}

	@Test
	void invalidPassword() {
		c = new CustomerContainer(99999999, "Skanderborgvej 123", "", "Thomas");
		assertThrows(IllegalArgumentException.class, () -> vm.addCustomer(c));
	}

	@Test
	void invalidName() {
		CustomerContainer c = new CustomerContainer(99999999, "Skanderborgvej 123", "Kodeord123", "");
		assertThrows(IllegalArgumentException.class, () -> vm.addCustomer(c));
	}
}
