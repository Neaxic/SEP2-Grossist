package client.core.viewhandlers;

import client.core.ViewHandler;
import client.customerclient.views.CustomerViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

// Andreas Østergaard, Frederik Bergmann, Andreas Young.

public class CustomerViewHandler implements ViewHandler {

	private Stage primaryStage;
	private final ViewHandler proxyViewHandler;

	public CustomerViewHandler(ViewHandler proxyViewHandler) {
		this.proxyViewHandler = proxyViewHandler;
	}

	private Scene sceneLoader(String view) throws IOException, SQLException {
		Scene scene;
		Parent root;

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("../../customerclient/views/" + view +"/" +view + ".fxml"));
		root = loader.load();

		CustomerViewController viewController = loader.getController();
		viewController.init(proxyViewHandler);

		scene = new Scene(root);
		return scene;
	}

	@Override
	public void start(Stage primaryStage) throws IOException, SQLException {
		this.primaryStage = primaryStage;
		openView("CustomerBrowser");
	}

	@Override
	public void openView(String viewToOpen) throws IOException, SQLException {
		Scene scene = sceneLoader(viewToOpen);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
