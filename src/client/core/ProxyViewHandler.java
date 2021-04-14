package client.core;

import client.CustomerClient.Views.ViewModel;
import javafx.stage.Stage;

import javax.swing.text.View;

public class ProxyViewHandler implements ViewHandler {
	private ViewHandler viewHandler;
	private ViewModelFactory viewModelFactory;

	public ProxyViewHandler(ViewModelFactory viewModelFactory) {
		this.viewModelFactory = viewModelFactory;

		// TODO: Ret constucter til if når login ting er lavet
		viewHandler = new GrosserViewHandler(viewModelFactory);
	}

	@Override
	public void start(Stage primaryStage) {
		viewHandler.start(primaryStage);
	}

	@Override
	public void openView(String viewToOpen) {
		viewHandler.openView(viewToOpen);
	}

	@Override
	public ViewModel getViewModelByViewName(String viewName) {
		return null;
	}
}
