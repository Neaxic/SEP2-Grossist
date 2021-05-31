package client.core.viewhandlers;

import client.core.LoginManager;
import client.core.ViewHandler;
import client.core.ViewModel;
import client.core.factories.ViewModelFactory;
import client.core.viewhandlers.CustomerViewHandler;
import client.core.viewhandlers.GrosserViewHandler;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

// Andreas Østergaard, Frederik Bergmann.

public class ProxyViewHandler implements ViewHandler {
	private ViewHandler viewHandler;
	private final Stage stage;

	public ProxyViewHandler(Stage stage) {
		this.stage = stage;
		login();
	}

	@Override
	public void start(Stage primaryStage) throws IOException, SQLException {
		viewHandler.start(primaryStage);
	}

	@Override
	public void openView(String viewToOpen) throws IOException, SQLException {
		viewHandler.openView(viewToOpen);
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
		catch (IOException | SQLException e)
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
		catch (IOException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}
