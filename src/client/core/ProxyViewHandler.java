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
	public void start(Stage primaryStage, ViewModelFactory viewModelFactory, ViewHandler proxyViewHandler) throws IOException {
		viewHandler.start(primaryStage, viewModelFactory, proxyViewHandler);
	}

	@Override
	public void openView(String viewToOpen) throws IOException {
		viewHandler.openView(viewToOpen);
	}

	@Override
	public ViewModel getViewModelByViewName(String viewName) {
		return viewHandler.getViewModelByViewName(viewName);
	}
}
