package client.grosserclient.views.grosserwares;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class GrosserWaresViewController implements GrosserViewController, PropertyChangeListener {
	private ViewHandler viewHandler;
	private GrosserWaresViewModel viewModel;

	@FXML private TableView wareList;
	@FXML private TableColumn wares;
	@FXML private TableColumn amounts;

	@Override
	public void init(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
		viewModel = ViewModelFactory.getInstance().grosserWaresViewModel();
		viewModel.addListener(this);
		viewModel.updateWareList();

		wares.setCellValueFactory(new PropertyValueFactory<>("Product"));
		amounts.setCellValueFactory(new PropertyValueFactory<>("Antal"));
	}

	private void setTable() {
		wareList.setItems(FXCollections.observableList(viewModel.getThisIsGettingRatherAnnoying()));
	}

	public void changeAmount() {
		Object o = wareList.getSelectionModel().getSelectedItem();
		System.out.println(o);
//		viewModel.changeAmount(p, v);
	}

	public void deleteItem() {
		Object o = wareList.getSelectionModel().getSelectedItem();
		System.out.println(o);
		viewModel.deleteItem(o);
		refresh();
	}


	@FXML
	private void addNewProductScene() throws IOException {
		swapScene("GrosserAddNewProduct");
	}

	@FXML
	private void homeScreen() throws IOException {
		swapScene("GrosserMain");
	}

	@FXML private void refresh(){
		init(viewHandler);
	}

	@Override
	public void swapScene(String sceneName) throws IOException {
		viewHandler.openView(sceneName);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		setTable();
	}
}
