package client.core;

import javafx.stage.Stage;

import java.io.IOException;

public class ProxyViewHandler implements ViewHandler {
	private ViewHandler viewHandler;
	private final ViewModelFactory viewModelFactory;
	private final Stage stage;

	public ProxyViewHandler(Stage stage) {
		this.stage = stage;
		viewModelFactory = ViewModelFactory.getInstance();
		login();
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		viewHandler.start(primaryStage);
	}

	@Override
	public void openView(String viewToOpen) throws IOException {
		viewHandler.openView(viewToOpen);
	}

	@Override
	public ViewModel getViewModelByViewName(String viewName) {
		return viewHandler.getViewModelByViewName(viewName);
	}

	private void login()
	{
		new LoginManager(this).login();
	}

	public void customerLogin()
	{
		try
		{
			viewHandler = new CustomerViewHandler(this);
			start(stage);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void grosserLogin()
	{
		try
		{
			viewHandler = new GrosserViewHandler(this);
			start(stage);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
