package client.customerclient.views.customerbasket;

import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import client.core.ViewHandler;

import java.io.IOException;

public class BasketViewController implements CustomerViewController {
	private ViewHandler viewHandler;
	private BasketViewModel viewModel;

	@Override
	public void init(ViewHandler viewHandler, CustomerViewModel viewModel) {
		this.viewHandler = viewHandler;
		this.viewModel = (BasketViewModel) viewModel;
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
