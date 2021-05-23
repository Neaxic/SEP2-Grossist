package client.grosserclient.views.grosseraddproduct;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;
import shared.wares.*;

import java.io.IOException;
import java.util.ArrayList;

// Frederik Bergmann, Andreas Østergaard, Andreas Young

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
		// Ved ikke præcist hvad 0f var, og det fuckede op Har et punktum check over^
		// return Double.parseDouble(text) > 0f;
	}

	private void fillTagsArr(){
		//man kunne kigge på noden og tage alle checkboxe childs.
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

	private String getAllTags(){
		String tempTags = "";
		for(CheckBox i: checkValues){
			if(i.isSelected()){
				tempTags += i.getText() + ", ";
			}
		}

		// cut det sidste komma fra
		if(tempTags.length() > 0){
			activeTags = tempTags.substring(0,tempTags.length()-2);
		}
		return activeTags;
	}


	private void createWarning(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
		alert.showAndWait();
	}

	@FXML
	private void createProduct() {

		fillTagsArr();

		//Check for tal i tal felter
		if (numCheckAndNotNull(productPrice.getText()) || numCheckAndNotNull(productAmount.getText()) || numCheckAndNotNull(productDeliveryDays.getText())) {
			createWarning("Et af felterne 'Pris', 'Ledig mængde' eller 'Leverings dage' indeholder bogstaver, eller tomme.");
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

				//    DATABASE check ( type in ('Rødvin', 'Hvidvin', 'Rose', 'Spiritus', 'Øl', 'Cider') )
		//		if (!alcoholType.getText().toLowerCase().contains("øl")) {
		//			createWarning("Alkohol typen kan kun være enten; 'Øl', 'Vin' eller 'Spiritus'");
		//			return;
		//		}

				Alcohol newProduct = new Alcohol(
						productName.getText(),
						productMeasurement.getText(),
						productBestBefore.getValue(),
						0,
						Integer.parseInt(productDeliveryDays.getText()),
						Double.parseDouble(productPrice.getText()),
						productBy.getText(),
						getAllTags(),
						alcoholCountry.getText(),
						Double.parseDouble(alcoholPercent.getText()),
						alcoholType.getText());

				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.createProduct(liste);
			}
			case "Drikkevarer" -> {
				Drink newProduct = new Drink(
						productName.getText(),
						productMeasurement.getText(),
						productBestBefore.getValue(),
						0,
						Integer.parseInt(productDeliveryDays.getText()),
						Double.parseDouble(productPrice.getText()),
						productBy.getText(),
						getAllTags(),
						drikType.getText());

				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.createProduct(liste);
			}
			case "Kolonial" -> {
				Colonial newProduct = new Colonial(
						productName.getText(),
						productMeasurement.getText(),
						productBestBefore.getValue(),
						0,
						Integer.parseInt(productDeliveryDays.getText()),
						Double.parseDouble(productPrice.getText()),
						productBy.getText(),
						getAllTags(),
						colonialCountry.getText());

				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.createProduct(liste);
			}
			case "Mejeri og æg" -> {
				CooledAndDairy newProduct = new CooledAndDairy(
						productName.getText(),
						productMeasurement.getText(),
						productBestBefore.getValue(),
						0,
						Integer.parseInt(productDeliveryDays.getText()),
						Double.parseDouble(productPrice.getText()),
						productBy.getText(),
						getAllTags());



						Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.createProduct(liste);
			}
			case "Frugt og grønt" -> {
				FruitsAndVegetable newProduct = new FruitsAndVegetable(
						productName.getText(),
						productMeasurement.getText(),
						productBestBefore.getValue(),
						0,
						Integer.parseInt(productDeliveryDays.getText()),
						Double.parseDouble(productPrice.getText()),
						productBy.getText(),
						getAllTags(),
						greenCountry.getText());

				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.createProduct(liste);
			}
			case "Kød og fisk" -> {
				MeatAndFish newProduct = new MeatAndFish(
						productName.getText(),
						productMeasurement.getText(),
						productBestBefore.getValue(),
						0,
						Integer.parseInt(productDeliveryDays.getText()),
						Double.parseDouble(productPrice.getText()),
						productBy.getText(),
						getAllTags(),
						meatCountry.getText());

				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.createProduct(liste);
			}
			case "Frost" -> {
				Frozen newProduct = new Frozen(
						productName.getText(),
						productMeasurement.getText(),
						productBestBefore.getValue(),
						0,
						Integer.parseInt(productDeliveryDays.getText()),
						Double.parseDouble(productPrice.getText()),
						productBy.getText(),
						getAllTags()
				);
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.createProduct(liste);
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
