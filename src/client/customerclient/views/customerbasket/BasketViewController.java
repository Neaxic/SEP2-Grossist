package client.customerclient.views.customerbasket;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.customerclient.views.CustomerViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Pair;
import shared.objects.ProductAndInt;
import shared.wares.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

// Andreas Østergaard, Andreas Young, Line Guld.

public class BasketViewController implements CustomerViewController {
	@FXML
	private TableView<ProductAndInt> basketTable;
	@FXML
	private TableColumn<ProductAndInt, Integer> basketAntal;
	@FXML
	private TableColumn<ProductAndInt, String> basketProduct;
	@FXML
	private Text basketSum;

	private ViewHandler viewHandler;
	private BasketViewModel viewModel;

	@Override
	public void init(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
		this.viewModel = (BasketViewModel) ViewModelFactory.getInstance().basketViewModel();
		loadAllProducts();
	}

	public void loadAllProducts() {
		basketProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
		basketAntal.setCellValueFactory(new PropertyValueFactory<>("amount"));

		double price = 0;

		for (Product i : viewModel.loadAllProducts().keySet()) {
			ProductAndInt productAndInt = new ProductAndInt(i.getWareName(), i.getWareNumber(), viewModel.loadAllProducts().get(i));
			price += i.getPrice() * viewModel.loadAllProducts().get(i);
			basketTable.getItems().add(productAndInt);
		}

		basketSum.setText("SUM: " + price + ", kr");
	}

	public void removeItemFromBasket() {
		Object item = basketTable.getSelectionModel().getSelectedItem();
		viewModel.removeFromBasket(item);
	}

	public void sendOrder() {
		Pair<Boolean, ArrayList<Product>> verification = viewModel.sendOrder();
		if (verification.getKey()) {
			basketTable.getItems().clear();
			basketSum.setText("");
			viewModel.loadAllProducts().clear();
			new Alert(Alert.AlertType.CONFIRMATION,"Din bestilling er nu sendt ind.", ButtonType.OK).showAndWait();
		} else {
			String test = OrderPopUpError.display(verification.getValue());
			switch (test) {
				case "confirm":
					viewModel.removeFromBasket(verification.getValue());
					break;
				case "cancel":
					break;
			}
		}
	}

	public void emptyBasket() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på du vil tømme kurven?", ButtonType.YES, ButtonType.NO);
		if (alert.showAndWait().get().getButtonData() == ButtonBar.ButtonData.YES) {
			viewModel.emptyBasket();
		}
	}

	// SCENE MANAGING

	@Override
	public void swapScene(String sceneName) throws IOException, SQLException {
		viewHandler.openView(sceneName);
	}

	public void openProductBrowser() throws IOException, SQLException {
		swapScene("CustomerBrowser");
	}

	public void openBasket() throws IOException, SQLException {
		swapScene("CustomerBasket");
	}

}
