package client.grosserclient.views.grosserwares;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.objects.ProductAndInt;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

// Young

public class GrosserWaresViewController
		implements GrosserViewController, PropertyChangeListener {
	private ViewHandler viewHandler;
	private GrosserWaresViewModel viewModel;

	@FXML private Label header;
	@FXML private TableView wareList;
	@FXML private TableColumn wares;
	@FXML private TableColumn amounts;
	@FXML private TextField newWareAmountField;

	@Override
	public void init(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
		viewModel = ViewModelFactory.getInstance().grosserWaresViewModel();
		viewModel.addListener(this);

		wares.setCellValueFactory(new PropertyValueFactory<>("product"));
		amounts.setCellValueFactory(new PropertyValueFactory<>("amount"));
		newWareAmountField.clear();

		// Mangler lige indikation af, at der er varer på vej fra Databasen
		Thread t = new Thread(() -> viewModel.updateWareList());
		t.setDaemon(true);
		t.start();

	}

	private void setTable() {
		wareList.setItems(FXCollections
				.observableList(viewModel.getListForView()));
	}

	private void setHeader() {
		Platform.runLater(() -> header.setText("Dine varer"));
	}

	@FXML
	private void increaseAmount() {
		Object o = wareList.getSelectionModel().getSelectedItem();
		try {
			if (o != null && ((ProductAndInt) o).getAmount() >= 0 && Integer.parseInt(newWareAmountField.getText()) >= 0) {
				viewModel.increaseStock(o, Integer.parseInt(newWareAmountField.getText()));
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			new Alert(Alert.AlertType.WARNING, "Antal skal være et Positivt Heltal");
		} finally {
			refresh();
		}
	}

	@FXML
	private void reduceAmount() {
		Object o = wareList.getSelectionModel().getSelectedItem();
		try {
			if (o != null && ((ProductAndInt) o).getAmount() >= 0 && Integer.parseInt(newWareAmountField.getText()) >= 0) {
				viewModel.reduceStock(o, Integer.parseInt(newWareAmountField.getText()));
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			new Alert(Alert.AlertType.WARNING, "Antal skal være et Positivt Heltal");
		} finally {
			refresh();
		}
	}

	@FXML
	private void deleteItem() {
		Object o = wareList.getSelectionModel().getSelectedItem();
		System.out.println(o);
		viewModel.deleteItem(o);
		refresh();
	}

	@FXML
	private void homeScreen() throws IOException {
		swapScene("GrosserMain");
	}

	@FXML
	private void addNewProductScene() throws IOException {
		swapScene("GrosserAddProduct");
	}

	@FXML
	private void refresh() {
		init(viewHandler);
	} // Hiver fat i databasen hver gang, ikke det smarteste men klart det sikreste

	@Override
	public void swapScene(String sceneName) throws IOException {
		viewHandler.openView(sceneName);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		setTable();
		setHeader();
	}
}
