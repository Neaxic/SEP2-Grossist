package client.grosserclient.views.grosserwares;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.objects.ProductAndInt;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

// Young

public class GrosserWaresViewController implements GrosserViewController, PropertyChangeListener {
	private ViewHandler viewHandler;
	private GrosserWaresViewModel viewModel;

	@FXML private TableView wareList;
	@FXML private TableColumn wares;
	@FXML private TableColumn amounts;
	@FXML private TextField newWareAmountField;

	@Override
	public void init(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
		viewModel = ViewModelFactory.getInstance().grosserWaresViewModel();
		viewModel.addListener(this);
		// Mangler lige indikation af, at der er varer på vej fra Databasen
		Thread t = new Thread(() -> viewModel.updateWareList());
		t.setDaemon(true);
		t.start();

		wares.setCellValueFactory(new PropertyValueFactory<>("product"));
		amounts.setCellValueFactory(new PropertyValueFactory<>("amount"));
	}

	private void setTable() {
		wareList.setItems(FXCollections.observableList(viewModel.getThisIsGettingRatherAnnoying()));
	}

	@FXML
	private void changeAmount() {
		Object o = wareList.getSelectionModel().getSelectedItem();
		if (o != null && ((ProductAndInt) o).getAmount() >= 0) {
			try {
				viewModel.changeAmount(o, Integer.parseInt(newWareAmountField.getText()));
			} catch (NumberFormatException e) {
				new Alert(Alert.AlertType.WARNING, "Nyt Antal skal være en heltals værdi");
			} finally {
				newWareAmountField.clear();
				refresh();
			}
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
	}
}
