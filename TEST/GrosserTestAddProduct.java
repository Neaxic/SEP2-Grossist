import client.core.factories.ClientFactory;
import client.core.factories.ModelFactory;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.grosseraddproduct.GrosserAddProductViewModel;
import client.network.GrosserClient;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.wares.*;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//Line Guld (Young prøver at tage en beslutning der hedder, at de tests der fejler lige nu skal fjernes)
class GrosserTestAddProduct {
	private static GrosserAddProductViewModel viewModel;
	private static GrosserClient grosserClient;
	static GrosserModelInterface grosserModel;

	//Nedenstående felter er en masse konstanter som bruges i testen, IKKE ÆNDRE DEM!
	static Integer validAmount = 500;
	static Integer invalidAmountNegative = -1;
	static Integer invalidAmountZero = 0;
	static LocalDate validBestBefore = LocalDate.now().plusMonths(3);
	static LocalDate invalidBestBeforeYesterday = LocalDate.now().minusDays(1);
	static LocalDate invalidBestBeforeIsToday = LocalDate.now();
	static Pair<Product, Integer> pair;

	@BeforeAll //Hapset fra Young
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
	public void setup() {
		viewModel = new GrosserAddProductViewModel();
		grosserModel = ModelFactory.getInstance().getGrosserModel();
	}

	@Test
		//Hapset fra Young
	void jUnitTest() {
		assertTrue(false != true, "JUnit does not seem to work at the current time, please try again later...");
	}


	/// Hver af de nedenstående underklasser tester sendOrder() metoden for de
	// de forskellige produktklasser, inklusive boundrytest hvor relevant.
	static class AlcoholTest {
		@BeforeAll //Hapset fra Young
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
		public void setup() {
			viewModel = new GrosserAddProductViewModel();
			grosserModel = ModelFactory.getInstance().getGrosserModel();
		}

		@Test
		void invalidAlcoholAmountInput() {
			Alcohol validInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(validInputAlcohol,
					invalidAmountNegative);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholAmountZero() {
			Alcohol validInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(validInputAlcohol,
					invalidAmountZero);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void validAlcoholProductInput() {
			Alcohol validInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> validPair = new Pair<>(validInputAlcohol, validAmount);

			assertDoesNotThrow(() -> viewModel.createProduct(validPair));
		}

		@Test
		void invalidAlcoholProductInputName() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholProductInputMeasurement() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"",
					validBestBefore,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholProductBestBeforeYesterday() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					invalidBestBeforeYesterday,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholProductBestBeforeToday() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					invalidBestBeforeIsToday,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholDeliveryZero() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					0,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholDeliveryNegative() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					-1,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholPriceZero() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					0,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholPriceNegative() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					-1,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholInputProducedBy() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					7170,
					"",
					"",
					"Frankrig",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholInputOrigin() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"",
					12.50,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholInputPercentageZero() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					0,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholInputPercentageNegative() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					-0.05,
					"Champagne");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}

		@Test
		void invalidAlcoholInputType() {
			Alcohol invalidInputAlcohol = new Alcohol(
					"Dom Perignon 2010, 75cl",
					"kasser af 6",
					validBestBefore,
					-1,
					30,
					7170,
					"Moët & Chandon",
					"",
					"Frankrig",
					12.50,
					"");

			Pair<Product, Integer> invalidPair = new Pair<>(invalidInputAlcohol, validAmount);

			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(invalidPair));
		}
	}

	static class DrinksTest {
		@BeforeAll //Hapset fra Young
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
		public void setup() {
			viewModel = new GrosserAddProductViewModel();
			grosserModel = ModelFactory.getInstance().getGrosserModel();
		}

		// Test af at oprette ikke-alkoholiske drikkevarer
		@Test
		void invalidDrinkAmountInput() {
			Drink validDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					validBestBefore,
					-1,
					7,
					360,
					"Monster Beverage",
					"",
					"Energidrik");

			Pair<Product, Integer> pair = new Pair<>(validDrink,
					invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDrinkAmountZero() {
			Drink validDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					validBestBefore,
					-1,
					7,
					360,
					"Monster Beverage",
					"",
					"Energidrik");

			Pair<Product, Integer> pair = new Pair<>(validDrink, invalidAmountZero);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void validDrinkProductInput() {
			Drink validDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					validBestBefore,
					-1,
					7,
					360,
					"Monster Beverage",
					"",
					"Energidrik");

			Pair<Product, Integer> validPair = new Pair<>(validDrink, validAmount);
			assertDoesNotThrow(() -> viewModel.createProduct(validPair));
		}

		@Test
		void invalidDrinkProductInputName() {
			Drink invalidDrink = new Drink(
					"",
					"Ramme af 24",
					validBestBefore,
					-1,
					7,
					360,
					"Monster Beverage",
					"",
					"Energidrik"
			);


			Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDrinkProductMeasurement() {
			Drink invalidDrink = new Drink(
					"Monster pipeline punch",
					"",
					validBestBefore,
					-1,
					7,
					360,
					"Monster Beverage",
					"",
					"Energidrik");


			Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDrinkProductBestBeforeYesterday() {
			Drink invalidDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					invalidBestBeforeYesterday,
					-1,
					7,
					360,
					"Monster Beverage",
					"",
					"Energidrik");

			Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDrinkProductBestBeforeToday() {
			Drink invalidDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					invalidBestBeforeIsToday,
					-1,
					7,
					360,
					"Monster Beverage",
					"",
					"Energidrik");

			Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDrinkProductDeliveryZero() {
			Drink invalidDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					validBestBefore,
					-1,
					0,
					360,
					"Monster Beverage",
					"",
					"Energidrik"
			);

			Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDrinkProductDeliveryNegative() {
			Drink invalidDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					validBestBefore,
					-1,
					-1,
					360,
					"Monster Beverage",
					"",
					"Energidrik"
			);

			Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDrinkProductPriceZero() {
			Drink invalidDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					validBestBefore,
					-1,
					7,
					0,
					"Monster Beverage",
					"",
					"Energidrik"
			);

			Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDrinkProductPriceNegative() {
			Drink invalidDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					validBestBefore,
					-1,
					7,
					-1,
					"Monster Beverage",
					"",
					"Energidrik"
			);

			Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDrinkProductType() {
			Drink invalidDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					validBestBefore,
					-1,
					7,
					360,
					"Monster Beverage",
					"",
					""
			);

			Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDrinkProductProducedBy() {
			Drink invalidDrink = new Drink(
					"Monster pipeline punch",
					"Ramme af 24",
					validBestBefore,
					-1,
					7,
					360,
					"",
					"",
					"Energidrik");

			Pair<Product, Integer> pair = new Pair<>(invalidDrink, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}
	}

	static class ColonialTest {
		@BeforeAll //Hapset fra Young
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
		public void setup() {
			viewModel = new GrosserAddProductViewModel();
			grosserModel = ModelFactory.getInstance().getGrosserModel();
		}

		@Test
		void invalidColonialAmountInput() {
			Colonial validColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					validBestBefore,
					-1,
					1,
					1,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(validColonial,
					invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidColonialAmountZero() {
			Colonial validColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					validBestBefore,
					-1,
					1,
					1,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(validColonial, invalidAmountZero);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void validColonialInput() {
			Colonial validColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					validBestBefore,
					-1,
					1,
					150,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> validPair = new Pair<>(validColonial, validAmount);
			assertDoesNotThrow(() -> viewModel.createProduct(validPair));
		}

		@Test
		void invalidColonialInputName() {
			Colonial invalidColonial = new Colonial(
					"",
					"Ramme af 24",
					validBestBefore,
					-1,
					1,
					150,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidColonialInputMeasurement() {
			Colonial invalidColonial = new Colonial(
					"Jordbærsyltetøj",
					"",
					validBestBefore,
					-1,
					1,
					150,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidColonialInputBestBeforeYesterday() {
			Colonial invalidColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					invalidBestBeforeYesterday,
					-1,
					1,
					150,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidColonialInputBestBeforeToday() {
			Colonial invalidColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					invalidBestBeforeIsToday,
					-1,
					1,
					150,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidColonialInputDeliveryDaysNegative() {
			Colonial invalidColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					validBestBefore,
					-1,
					-1,
					150,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidColonialInputDeliveryDaysZero() {
			Colonial invalidColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					invalidBestBeforeIsToday,
					-1,
					0,
					150,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidColonialInputPriceNegative() {
			Colonial invalidColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					validBestBefore,
					-1,
					1,
					-1,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidColonialInputPriceZero() {
			Colonial invalidColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					validBestBefore,
					-1,
					1,
					0,
					"Den gamle fabrik",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidColonialInputProducedBy() {
			Colonial invalidColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					validBestBefore,
					-1,
					1,
					50,
					"",
					"",
					"Danmark");

			Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidColonialInputOrigin() {
			Colonial invalidColonial = new Colonial(
					"Jordbærsyltetøj",
					"Ramme af 24",
					validBestBefore,
					-1,
					1,
					60,
					"Den gamle fabrik",
					"",
					"");

			Pair<Product, Integer> pair = new Pair<>(invalidColonial, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}
	}

	static class DairyTest {
		@BeforeAll //Hapset fra Young
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
		public void setup() {
			viewModel = new GrosserAddProductViewModel();
			grosserModel = ModelFactory.getInstance().getGrosserModel();
		}

		//Test mejeri og æg
		@Test
		void invalidDairyAmountInput() {
			CooledAndDairy validDairy = new CooledAndDairy(
					"Arla sødmælk",
					"Kasse af 15",
					validBestBefore,
					-1,
					1,
					50.95,
					"Arla",
					""
			);

			pair = new Pair<>(validDairy, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDairyAmountInputZero() {
			CooledAndDairy validDairy = new CooledAndDairy(
					"Arla sødmælk",
					"Kasse af 15",
					validBestBefore,
					-1,
					1,
					50.95,
					"Arla",
					""
			);

			pair = new Pair<>(validDairy, invalidAmountZero);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void validDairyInput() {
			CooledAndDairy validDairy = new CooledAndDairy(
					"Arla sødmælk",
					"Kasse af 15",
					validBestBefore,
					-1,
					1,
					50.95,
					"Arla",
					""
			);
			pair = new Pair<>(validDairy, validAmount);
			assertDoesNotThrow(() -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDairyInputName() {
			CooledAndDairy invalidDairy = new CooledAndDairy(
					"",
					"Kasse af 15",
					validBestBefore,
					-1,
					1,
					50.95,
					"Arla",
					""
			);

			pair = new Pair<>(invalidDairy, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDairyInputMeasurement() {
			CooledAndDairy invalidDairy = new CooledAndDairy(
					"Sødmælk",
					"",
					validBestBefore,
					-1,
					1,
					50.95,
					"Arla",
					""
			);

			pair = new Pair<>(invalidDairy, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDairyInputBestBeforeYesterday() {
			CooledAndDairy invalidDairy = new CooledAndDairy(
					"Sødmælk",
					"Kasse af 15",
					invalidBestBeforeYesterday,
					-1,
					1,
					50.95,
					"Arla",
					""
			);

			pair = new Pair<>(invalidDairy, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDairyInputBestBeforeToday() {
			CooledAndDairy invalidDairy = new CooledAndDairy(
					"Sødmælk",
					"Kasse af 15",
					invalidBestBeforeIsToday,
					-1,
					1,
					50.95,
					"Arla",
					""
			);

			pair = new Pair<>(invalidDairy, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDairyInputDeliveryZero() {
			CooledAndDairy invalidDairy = new CooledAndDairy(
					"Sødmælk",
					"Kasse af 15",
					validBestBefore,
					-1,
					0,
					50.95,
					"Arla",
					""
			);

			pair = new Pair<>(invalidDairy, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDairyDeliveryNegative() {
			CooledAndDairy invalidDairy = new CooledAndDairy(
					"",
					"Kasse af 15",
					validBestBefore,
					-1,
					-1,
					50.95,
					"Arla",
					""
			);

			pair = new Pair<>(invalidDairy, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDairyInputPriceZero() {
			CooledAndDairy invalidDairy = new CooledAndDairy(
					"Sødmælk",
					"Kasse af 15",
					validBestBefore,
					-1,
					1,
					0,
					"Arla",
					""
			);

			pair = new Pair<>(invalidDairy, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDairyInputPriceNegative() {
			CooledAndDairy invalidDairy = new CooledAndDairy(
					"Sødmælk",
					"Kasse af 15",
					validBestBefore,
					-1,
					1,
					-1,
					"Arla",
					""
			);

			pair = new Pair<>(invalidDairy, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidDairyInputProducedBy() {
			CooledAndDairy invalidDairy = new CooledAndDairy(
					"Sødmælk",
					"Kasse af 15",
					validBestBefore,
					-1,
					1,
					50.14,
					"",
					""
			);

			pair = new Pair<>(invalidDairy, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}
	}

	static class GreensTest {
		@BeforeAll //Hapset fra Young
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
		public void setup() {
			viewModel = new GrosserAddProductViewModel();
			grosserModel = ModelFactory.getInstance().getGrosserModel();
		}

		// Test frugt og grønt
		@Test
		void invalidGreensAmount() {
			FruitsAndVegetable validFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					validBestBefore,
					-1,
					7,
					50.05,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(validFruit, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensAmountZero() {
			FruitsAndVegetable validFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					validBestBefore,
					-1,
					7,
					50.05,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(validFruit, invalidAmountZero);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void validGreensInput() {
			FruitsAndVegetable validFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					validBestBefore,
					-1,
					7,
					50.05,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(validFruit, validAmount);
			assertDoesNotThrow(() -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensName() {
			FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
					"",
					"Kasse af 3 kg",
					validBestBefore,
					-1,
					7,
					50.05,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(invalidFruit, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensMeasurement() {
			FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
					"Bananer",
					"",
					validBestBefore,
					-1,
					7,
					50.05,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(invalidFruit, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensBestByYesterday() {
			FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					invalidBestBeforeYesterday,
					-1,
					7,
					50.05,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(invalidFruit, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensBestByToday() {
			FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					invalidBestBeforeIsToday,
					-1,
					7,
					50.05,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(invalidFruit, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensDeliveryDaysNegative() {
			FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					invalidBestBeforeYesterday,
					-1,
					-1,
					50.05,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(invalidFruit, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensDeliveryDaysZero() {
			FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					invalidBestBeforeYesterday,
					-1,
					0,
					50.05,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(invalidFruit, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensPriceNegative() {
			FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					invalidBestBeforeYesterday,
					-1,
					7,
					-1,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(invalidFruit, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensPriceZero() {
			FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					invalidBestBeforeYesterday,
					-1,
					7,
					0,
					"Itagu",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(invalidFruit, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensProducedBy() {
			FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					validBestBefore,
					-1,
					7,
					50.05,
					"",
					"",
					"Costa Rica"
			);

			pair = new Pair<>(invalidFruit, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidGreensCountry() {
			FruitsAndVegetable invalidFruit = new FruitsAndVegetable(
					"Bananer",
					"Kasse af 3 kg",
					validBestBefore,
					-1,
					7,
					50.05,
					"Itagu",
					"",
					""
			);

			pair = new Pair<>(invalidFruit, validAmount);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}
	}

	static class MeatAndFishTests {
		@BeforeAll //Hapset fra Young
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
		public void setup() {
			viewModel = new GrosserAddProductViewModel();
			grosserModel = ModelFactory.getInstance().getGrosserModel();
		}

		// Test af Kød og Fisk
		@Test
		void invalidMeatAmount() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg",
					validBestBefore,
					-1,
					70,
					10000,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatAmountZero() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg",
					validBestBefore,
					-1,
					70,
					10000,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountZero);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void validMeatInput() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg",
					validBestBefore,
					-1,
					70,
					10000,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, validAmount);
			assertDoesNotThrow(() -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatName() {
			MeatAndFish validMeat = new MeatAndFish(
					"",
					"Pakker af 50 kg",
					validBestBefore,
					-1,
					70,
					10000,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatMeasurement() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"",
					validBestBefore,
					-1,
					70,
					10000,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatBestBeforeYesterday() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg", invalidBestBeforeYesterday,
					-1,
					70,
					10000,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatBestBeforeToday() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg",
					invalidBestBeforeIsToday,
					-1,
					70,
					10000,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatDeliverytimeNegative() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg",
					validBestBefore,
					-1,
					-1,
					10000,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatDeliverytimeZero() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg",
					validBestBefore,
					-1,
					0,
					10000,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatPriceZero() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg",
					validBestBefore,
					-1,
					70,
					0,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatPriceNegative() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg",
					validBestBefore,
					-1,
					70,
					-1,
					"Matsusaka Beef",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatProducedBy() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg",
					validBestBefore,
					-1,
					70,
					10000,
					"",
					"",
					"Japan"
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}

		@Test
		void invalidMeatOrigin() {
			MeatAndFish validMeat = new MeatAndFish(
					"Wagyu Filet Mignon",
					"Pakker af 50 kg",
					validBestBefore,
					-1,
					70,
					10000,
					"Matsusaka Beef",
					"",
					""
			);

			pair = new Pair<>(validMeat, invalidAmountNegative);
			assertThrows(IllegalArgumentException.class, () -> viewModel.createProduct(pair));
		}
	}
}
