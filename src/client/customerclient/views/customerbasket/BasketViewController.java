package client.customerclient.views.customerbasket;

import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import client.core.ViewHandler;

public class BasketViewController implements CustomerViewController {
	private ViewHandler viewHandler;
	private BasketViewModel viewModel;

	@Override
	public void init(ViewHandler viewHandler, CustomerViewModel viewModel) {
		this.viewHandler = viewHandler;
		this.viewModel = (BasketViewModel) viewModel;
	}

	@Override
	public void swapScene(String sceneName) {
		//TODO
	}
}
