package client.grosserclient.views.grosseraddproduct;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;
import shared.wares.*;

import java.io.IOException;

// Frederik Bergmann, Andreas Østergaard, Andreas Young

public class GrosserAddProductViewController implements GrosserViewController {
	private ViewHandler viewHandler;
	private GrosserAddProductViewModel viewModel;

	@FXML private TextField productName;
	@FXML private TextField productPrice;
	@FXML private TextField productMinAmount;
	@FXML private TextField productMeasurement;
	@FXML private DatePicker productBestBefore;
	@FXML private TextField productDeliveryDays;
	@FXML private TextField productAmount;
	@FXML private TextField productBy;

	@FXML private TextField alcoholType;
	@FXML private TextField alcoholPercent;
	@FXML private TextField alcoholCountry;

	@FXML private TextField drikType;
	@FXML private TextField colonialCountry;
	@FXML private TextField greenCountry;
	@FXML private TextField meatCountry;

	@FXML private TabPane tabPane;



	@Override
	public void init(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
		viewModel = ViewModelFactory.getInstance().grosserAddProductViewModel();
	}

	private boolean numCheckAndNotNull(String text) {
		if (!(text.isBlank())) {
			for (char c : text.toCharArray()) {
				if (Character.isDigit(c)) {
					if (c >= 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void createWarning(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
		alert.showAndWait();
	}

	@FXML
	private void createProduct() {

		//Check for tal i tal felter
		if (numCheckAndNotNull(productPrice.getText()) || numCheckAndNotNull(productAmount.getText()) || numCheckAndNotNull(productDeliveryDays.getText())) {
			createWarning("Et af felterne 'Pris', 'Ledig mængde' eller 'Leverings dage' indeholder bogstaver");
			return;
		}

		if (productName.getText().isBlank() || productBy.getText().isBlank() || productMeasurement.getText().isBlank()) {
			createWarning("En af felterne er ikke udfyldte");
			return;
		}

		String className = tabPane.getSelectionModel().getSelectedItem().getText();
		switch (className) {
			case "Alkohol" -> {
				// procent
				if (!numCheckAndNotNull(alcoholPercent.getText())) {
					System.out.println("fejl");
					return;
				}

				// check om type er øl/vin/spiritus
				if (!alcoholType.getText().toLowerCase().contains("øl")) {
					createWarning("Alkohol typen kan kun være enten; 'Øl', 'Vin' eller 'Spiritus'");
					return;
				}


				Alcohol newProduct = new Alcohol(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(productMinAmount.getText()), productBy.getText(), alcoholCountry.getText(), Double.parseDouble(alcoholPercent.getText()), alcoholType.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
			case "Drikkevarer" -> {
				Drink newProduct = new Drink(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(productMinAmount.getText()), productBy.getText(), drikType.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
			case "Kolonial" -> {
				Colonial newProduct = new Colonial(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(productMinAmount.getText()), productBy.getText(), colonialCountry.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
			case "Mejeri og æg" -> {
				CooledAndDairy newProduct = new CooledAndDairy(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(productMinAmount.getText()), productBy.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
			case "Frugt og grønt" -> {
				FruitsAndVegetable newProduct = new FruitsAndVegetable(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(productMinAmount.getText()), productBy.getText(), greenCountry.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
			case "Kød og fisk" -> { // TODO: Line, se den her
				//Object[] params = {productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, productDeliveryDays.getText(), productPrice.getText(), productMinAmount.getText(), productBy.getText()};
				//viewModel.createProduct(params);

				MeatAndFish newProduct = new MeatAndFish(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(productMinAmount.getText()), productBy.getText(), meatCountry.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
		}
		new Alert(Alert.AlertType.INFORMATION, "Produkt er nu tilføjet til Lageret", ButtonType.CLOSE).showAndWait();
	}

	@Override
	public void swapScene(String sceneName) throws IOException {
		viewHandler.openView(sceneName);
	}

	public void waresScene() throws IOException {
		swapScene("GrosserWares");
	}
}
