package client.grosserclient.views.grosseraddproduct;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;
import shared.wares.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

// Frederik Bergmann, Andreas Østergaard, Andreas Young.

public class GrosserAddProductViewController implements GrosserViewController {
	private ViewHandler viewHandler;
	private GrosserAddProductViewModel viewModel;

	@FXML private TextField productName;
	@FXML private TextField productPrice;
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

	@FXML private CheckBox checkØko;
	@FXML private CheckBox checkNøgle;
	@FXML private CheckBox checkGMO;
	@FXML private CheckBox checkLaktosefri;
	@FXML private CheckBox checkVegetar;
	@FXML private CheckBox checkFedtfattig;
	@FXML private CheckBox checkMSE;
	@FXML private CheckBox checkSukkerfri;
	@FXML private CheckBox checkVegansk;
	@FXML private CheckBox checkHalal;
	@FXML private CheckBox checkGlutenfri;
	@FXML private CheckBox checkStråforkortere;
	@FXML private CheckBox checkAlkoholfri;


	private ArrayList<CheckBox> checkValues;
	private String activeTags;

	@Override
	public void init(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
		viewModel = ViewModelFactory.getInstance().grosserAddProductViewModel();
		checkValues = new ArrayList<CheckBox>();
		activeTags = "";
	}

	private boolean numCheckAndNotNull(String text) {
		if (text.isBlank()) {
			return true;
		}
		for (char c : text.toCharArray()) {
			if (!Character.isDigit(c) && !(c == '.')) {
				return true;
			}
		}
		return false;
	}

	private void fillTagsArr() {
		checkValues.add(checkØko);
		checkValues.add(checkNøgle);
		checkValues.add(checkGMO);
		checkValues.add(checkLaktosefri);
		checkValues.add(checkVegetar);
		checkValues.add(checkVegansk);
		checkValues.add(checkFedtfattig);
		checkValues.add(checkMSE);
		checkValues.add(checkSukkerfri);
		checkValues.add(checkStråforkortere);
		checkValues.add(checkHalal);
		checkValues.add(checkGlutenfri);
		checkValues.add(checkAlkoholfri);
	}

	private String getAllTags() {
		String tempTags = "";
		for (CheckBox i : checkValues) {
			if (i.isSelected()) {
				tempTags += i.getText() + ", ";
			}
		}

		if (tempTags.length() > 0) {
			activeTags = tempTags.substring(0, tempTags.length() - 2);
		}
		return activeTags;
	}


	private void createWarning(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
		alert.showAndWait();
	}

	private boolean checkAllDefaults() {
		if (numCheckAndNotNull(productPrice.getText()) || numCheckAndNotNull(productAmount.getText()) || numCheckAndNotNull(productDeliveryDays.getText())) {
			createWarning("Et af felterne 'Pris', 'Ledig mængde' eller 'Leverings dage' indeholder bogstaver, eller tomme.");
			return false;
		}

		if (productName.getText().isBlank() || productBy.getText().isBlank() || productMeasurement.getText().isBlank()) {
			createWarning("En af felterne er ikke udfyldte");
			return false;
		}

		if (productBestBefore.getValue() == null) {
			createWarning("Udløbsdatoen er ikke sat");
			return false;
		}
		else if (!(productBestBefore.getValue().isAfter(LocalDate.now().minusDays(1)))) {
			createWarning("Daoten sat er før idag og er derfor ugyldig");
			return false;
		}

		return true;
	}

	@FXML
	private void createProduct() {

		fillTagsArr();

		if (checkAllDefaults()) {
			String className = tabPane.getSelectionModel().getSelectedItem().getText();
			switch (className) {
				case "Alkohol" -> {
					if (numCheckAndNotNull(alcoholPercent.getText())) {
						createWarning("Procenten ikke angivet");
						return;
					}

					if (alcoholType.getText().isBlank()) {
						createWarning("Feltet 'Type' er tomt");
						return;
					} else if (!(alcoholType.getText().toLowerCase().matches("øl") || alcoholType.getText().toLowerCase().matches("hvidvin") || alcoholType.getText().toLowerCase().matches("rødvin") || alcoholType.getText().toLowerCase().matches("rose") || alcoholType.getText().toLowerCase().matches("cider") || alcoholType.getText().toLowerCase().matches("spiritus"))) {
						createWarning("Alkohol typen kan kun være enten; 'Øl', 'Hvidvin', 'Rødvin, 'Rose', 'Cider' eller 'Spiritus'");
						return;
					}

					if (alcoholCountry.getText().isBlank()) {
						createWarning("Feltet 'Oprindelses land' er tomt");
						return;
					}

					Alcohol newProduct = new Alcohol(
							productName.getText(),
							productMeasurement.getText(),
							productBestBefore.getValue(),
							-1,
							Integer.parseInt(productDeliveryDays.getText()),
							Double.parseDouble(productPrice.getText()),
							productBy.getText(),
							getAllTags(),
							alcoholCountry.getText(),
							Double.parseDouble(alcoholPercent.getText()),
							alcoholType.getText());

					Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
					try {
						viewModel.createProduct(liste);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}
				case "Drikkevarer" -> {

					if (drikType.getText().isBlank()) {
						createWarning("Feltet 'Type' er tomt");
						return;
					}

					Drink newProduct = new Drink(
							productName.getText(),
							productMeasurement.getText(),
							productBestBefore.getValue(),
							-1,
							Integer.parseInt(productDeliveryDays.getText()),
							Double.parseDouble(productPrice.getText()),
							productBy.getText(),
							getAllTags(),
							drikType.getText());

					Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
					try {
						viewModel.createProduct(liste);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

				}
				case "Kolonial" -> {

					if (colonialCountry.getText().isBlank()) {
						createWarning("Feltet 'Oprindelses land' er tomt");
						return;
					}

					Colonial newProduct = new Colonial(
							productName.getText(),
							productMeasurement.getText(),
							productBestBefore.getValue(),
							-1,
							Integer.parseInt(productDeliveryDays.getText()),
							Double.parseDouble(productPrice.getText()),
							productBy.getText(),
							getAllTags(),
							colonialCountry.getText());

					Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
					try {
						viewModel.createProduct(liste);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

				}
				case "Mejeri og æg" -> {
					CooledAndDairy newProduct = new CooledAndDairy(
							productName.getText(),
							productMeasurement.getText(),
							productBestBefore.getValue(),
							-1,
							Integer.parseInt(productDeliveryDays.getText()),
							Double.parseDouble(productPrice.getText()),
							productBy.getText(),
							getAllTags());


					Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
					try {
						viewModel.createProduct(liste);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

				}
				case "Frugt og grønt" -> {

					if (greenCountry.getText().isBlank()) {
						createWarning("Feltet 'Oprindelses land' er tomt");
						return;
					}

					FruitsAndVegetable newProduct = new FruitsAndVegetable(
							productName.getText(),
							productMeasurement.getText(),
							productBestBefore.getValue(),
							-1,
							Integer.parseInt(productDeliveryDays.getText()),
							Double.parseDouble(productPrice.getText()),
							productBy.getText(),
							getAllTags(),
							greenCountry.getText());

					Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
					try {
						viewModel.createProduct(liste);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

				}
				case "Kød og fisk" -> {

					if (meatCountry.getText().isBlank()) {
						createWarning("Feltet 'Oprindelses land' er tomt");
						return;
					}

					MeatAndFish newProduct = new MeatAndFish(
							productName.getText(),
							productMeasurement.getText(),
							productBestBefore.getValue(),
							-1,
							Integer.parseInt(productDeliveryDays.getText()),
							Double.parseDouble(productPrice.getText()),
							productBy.getText(),
							getAllTags(),
							meatCountry.getText());

					Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
					try {
						viewModel.createProduct(liste);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

				}
				case "Frost" -> {
					Frozen newProduct = new Frozen(
							productName.getText(),
							productMeasurement.getText(),
							productBestBefore.getValue(),
							-1,
							Integer.parseInt(productDeliveryDays.getText()),
							Double.parseDouble(productPrice.getText()),
							productBy.getText(),
							getAllTags()
					);
					Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
					try {
						viewModel.createProduct(liste);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

				}
			}
			new Alert(Alert.AlertType.INFORMATION, "Produkt er nu tilføjet til Lageret", ButtonType.CLOSE).showAndWait();
		}
	}

	@Override
	public void swapScene(String sceneName) throws IOException, SQLException {
		viewHandler.openView(sceneName);
	}

	public void waresScene() throws IOException, SQLException {
		swapScene("GrosserWares");
	}

	public void homeScreen() throws IOException, SQLException {
		swapScene("GrosserAddProduct");
	}

	public void addCustomerScene() throws IOException, SQLException {
		swapScene("GrosserAddCustomer");
	}
}
