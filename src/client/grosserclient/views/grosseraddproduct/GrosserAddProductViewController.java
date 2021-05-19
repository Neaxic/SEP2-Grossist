package client.grosserclient.views.grosseraddproduct;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;
import shared.wares.*;

import java.io.IOException;
import java.util.Optional;

// Frederik Bergmann

public class GrosserAddProductViewController implements GrosserViewController {
	private ViewHandler viewHandler;
	private GrosserAddProductViewModel viewModel;

	@FXML private TextField productName;
	@FXML private TextField productPrice;
	@FXML private TextField ProductMinAmount;
	@FXML private TextField productMeasurement;
	@FXML private DatePicker productBestBefore;
	@FXML private TextField productDeliveryDays;
	@FXML private TextField productAmount;
	@FXML private TextField productBy;

	@FXML private TextField AlcoholType;
	@FXML private TextField AlcoholPercent;
	@FXML private TextField AlcoholCountry;

	@FXML private TextField DrikType;
	@FXML private TextField ColonialCountry;
	@FXML private TextField GreenCountry;
	@FXML private TextField meatCountry;

	@FXML private TabPane tabPane;

	@Override
	public void init(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
		viewModel = ViewModelFactory.getInstance().grosserAddProductViewModel();

	}

	@FXML
	private void createProduct() {
		String className = tabPane.getSelectionModel().getSelectedItem().getText();
		switch (className) {
			case "Alkohol" -> {
				Alcohol newProduct = new Alcohol(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), productBy.getText(), AlcoholCountry.getText(), Double.parseDouble(AlcoholPercent.getText()), AlcoholType.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
			case "Drikkevarer" -> {
				Drink newProduct = new Drink(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), productBy.getText(), DrikType.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
			case "Kolonial" -> {
				Colonial newProduct = new Colonial(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), productBy.getText(), ColonialCountry.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
			case "Mejeri og æg" -> {
				CooledAndDairy newProduct = new CooledAndDairy(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), productBy.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
			case "Frugt og grønt" -> {
				FruitsAndVegetable newProduct = new FruitsAndVegetable(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), productBy.getText(), GreenCountry.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
			case "Kød og fisk" -> {
				MeatAndFish newProduct = new MeatAndFish(productName.getText(), productMeasurement.getText(), productBestBefore.getValue(), 0, Integer.parseInt(productDeliveryDays.getText()), Double.parseDouble(productPrice.getText()), Integer.parseInt(ProductMinAmount.getText()), productBy.getText(), meatCountry.getText());
				Pair<Product, Integer> liste = new Pair<>(newProduct, Integer.parseInt(productAmount.getText()));
				viewModel.sendOrder(liste);
			}
		}
		new Alert(Alert.AlertType.INFORMATION, "Produkt er nu tilføjet til Lageret", ButtonType.CLOSE).showAndWait();
	} // TODO: Østergaard, FÆRDIGGØR NU DE FUCKING CHECKS DIN ABE

	@Override
	public void swapScene(String sceneName) throws IOException {
		viewHandler.openView(sceneName);
	}

	public void waresScene() throws IOException {
		swapScene("GrosserWares");
	}
}
