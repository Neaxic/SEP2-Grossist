package client.customerclient.views.customersubscriptions;

import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import client.customerclient.views.placeorderEXCLUDED.PlaceOrderViewModel;
import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

// Andreas Young, Line Guld

public class CustomerSubscriptionsViewController implements CustomerViewController {
	@FXML private VBox subscriptionList;
	private ViewHandler viewHandler;
	private PlaceOrderViewModel viewModel;

	@Override
	public void init(ViewHandler viewHandler, CustomerViewModel customerViewModel) {
		this.viewHandler = viewHandler;
		this.viewModel = (PlaceOrderViewModel) customerViewModel;
	}


	public void removeFromList(HBox subscription) {
		String subscriptionTitle = ((Text) ((VBox) subscription.getChildren()).getChildren().get(0)).getText();
		viewModel.cancelSubscription(subscriptionTitle);
		subscriptionList.getChildren().remove(subscription);
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
