package client.CustomerClient.Views.placeorder;

import client.CustomerClient.Views.CustomerViewController;
import client.CustomerClient.Views.ViewModel;
import client.core.ViewHandler;

public class PlaceOrderViewController implements CustomerViewController {
	private ViewHandler viewHandler;
	private PlaceOrderViewModel viewModel;

	@Override
	public void init(ViewHandler viewHandler, ViewModel viewModel) {
		this.viewHandler = viewHandler;
		this.viewModel = (PlaceOrderViewModel) viewModel;
	}

	@Override
	public void swapScene(String sceneName) {
//TODO
	}
}
