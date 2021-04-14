package client.customerclient.views.customersubscriptions;

import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import client.customerclient.views.placeorder.PlaceOrderViewModel;
import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class CustomerSubscriptionsViewController implements CustomerViewController {
	@FXML private VBox subscriptionList;
	private ViewHandler viewHandler;
	private PlaceOrderViewModel viewModel;

	@Override
	public void init(ViewHandler viewHandler, CustomerViewModel customerViewModel) {
		this.viewHandler = viewHandler;
		this.viewModel = (PlaceOrderViewModel) customerViewModel;
	}

	@Override
	public void swapScene(String sceneName) throws IOException {
		viewHandler.openView(sceneName);
	}

	public void removeFromList(HBox subscription) {
		String subscriptionTitle = ((Text) ((VBox) subscription.getChildren()).getChildren().get(0)).getText();
		viewModel.cancelSubscription(subscriptionTitle);
		subscriptionList.getChildren().remove(subscription);
	}

	public void openBrowse(ActionEvent actionEvent) {
	}

	public void openBasket(ActionEvent actionEvent) {
	}
}
