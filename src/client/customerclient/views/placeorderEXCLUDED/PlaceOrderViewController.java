package client.customerclient.views.placeorderEXCLUDED;

import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import client.core.ViewHandler;

public class PlaceOrderViewController implements CustomerViewController {
	private ViewHandler viewHandler;
	private PlaceOrderViewModel viewModel;

	@Override
	public void init(ViewHandler viewHandler, CustomerViewModel viewModel) {
		this.viewHandler = viewHandler;
		this.viewModel = (PlaceOrderViewModel) viewModel;
	}

	@Override
	public void swapScene(String sceneName) {
//TODO
	}
}
