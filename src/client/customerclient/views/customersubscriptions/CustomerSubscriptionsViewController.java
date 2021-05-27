package client.customerclient.views.customersubscriptions;

import client.customerclient.views.CustomerViewController;
import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

// Andreas Young, Line Guld

public class CustomerSubscriptionsViewController implements CustomerViewController {
	@FXML private VBox subscriptionList;
	private ViewHandler viewHandler;

	@Override
	public void init(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
	}


	public void removeFromList(HBox subscription) {
		String subscriptionTitle = ((Text) ((VBox) subscription.getChildren()).getChildren().get(0)).getText();
		subscriptionList.getChildren().remove(subscription);
	}

	// SCENE MANAGING
	@Override
	public void swapScene(String sceneName) throws IOException, SQLException {
		viewHandler.openView(sceneName);
	}

	public void openProductBrowser() throws IOException, SQLException {
		swapScene("CustomerBrowser");
	}

	public void openSubscriptions() throws IOException, SQLException {
		swapScene("CustomerSubscriptions");
	}

	public void openBasket() throws IOException, SQLException {
		swapScene("CustomerBasket");
	}
}
