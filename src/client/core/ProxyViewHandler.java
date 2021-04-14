package client.core;

import javafx.stage.Stage;

import java.io.IOException;

public class ProxyViewHandler implements ViewHandler {
	private ViewHandler viewHandler;
	private ViewModelFactory viewModelFactory;

	public ProxyViewHandler(ViewModelFactory viewModelFactory) {
		this.viewModelFactory = viewModelFactory;

		// TODO: Ret constucter til if når login ting er lavet
		viewHandler = new CustomerViewHandler();
	}

	@Override
	public void start(Stage primaryStage, ViewModelFactory viewModelFactory) throws IOException {
		viewHandler.start(primaryStage, viewModelFactory);
	}

	@Override
	public void openView(String viewToOpen) throws IOException {
		viewHandler.openView(viewToOpen);
	}

	@Override
	public ViewModel getViewModelByViewName(String viewName) {
		return null;
	}
/*
	@Override
	public CustomerViewModel getViewModelByViewName(String viewName) {
		return null;
	}
 */
}
