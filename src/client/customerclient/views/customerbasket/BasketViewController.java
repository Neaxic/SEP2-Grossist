package client.customerclient.views.customerbasket;

import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import client.core.ViewHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class BasketViewController implements CustomerViewController {
	@FXML private TableView basketTable;
	private ViewHandler viewHandler;
	private BasketViewModel viewModel;

	@Override
	public void init(ViewHandler viewHandler, CustomerViewModel viewModel) {
		this.viewHandler = viewHandler;
		this.viewModel = (BasketViewModel) viewModel;
	}

	public void removeItemFromBasket() {
		ObservableList<Object> list = basketTable.getSelectionModel().getSelectedItems();
		viewModel.removeFromBasket(list);
		//test
		basketTable.getSelectionModel().getSelectedCells().removeAll(list);
	}

	public void saveBasketToBin(ActionEvent actionEvent) {
	}

	// SCENE MANAGING
	@Override
	public void swapScene(String sceneName) throws IOException {
		viewHandler.openView(sceneName);
	}

	public void openProductBrowser() throws IOException {
		swapScene("CustomerBrowser");
	}

	public void openSubscriptions() throws IOException {
		swapScene("CustomerSubscriptions");
	}

	public void openBasket() throws IOException {
		swapScene("CustomerBasket");
	}


}
