package client.CustomerClient.Views.basket;

import client.CustomerClient.Views.CustomerViewController;
import client.CustomerClient.Views.ViewModel;
import client.core.ViewHandler;

public class BasketViewController implements CustomerViewController {
	private ViewHandler viewHandler;
	private BasketViewModel viewModel;

	@Override
	public void init(ViewHandler viewHandler, ViewModel viewModel) {
		this.viewHandler = viewHandler;
		this.viewModel = (BasketViewModel) viewModel;
	}

	@Override
	public void swapScene(String sceneName) {
		//TODO
	}
}
