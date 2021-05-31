package client.core.viewhandlers;

import client.core.ViewHandler;
import client.grosserclient.views.GrosserViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

// Andreas Østergaard, Frederik Bergmann, Andreas Young.

public class GrosserViewHandler implements ViewHandler {
	private Stage primaryStage;
	private Scene currentScene;

	public GrosserViewHandler(ProxyViewHandler proxyViewHandler) {
	}

	private Scene sceneLoader(String view) throws IOException {
		Scene scene;
		Parent root;

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("../../grosserclient/views/" + view + "/" + view + ".fxml"));
		root = loader.load();

		GrosserViewController viewController = loader.getController();
		viewController.init(this);

		scene = new Scene(root);
		return scene;
	}

	private void openCustomer(String viewToOpen) throws IOException
	{
		Stage secondStage = new Stage();
		secondStage.initModality(Modality.APPLICATION_MODAL);
		currentScene = sceneLoader(viewToOpen);
		secondStage.setScene(currentScene);
		secondStage.show();
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.currentScene = new Scene(new Region());
		openView("GrosserMain");
	}

	@Override
	public void openView(String viewToOpen) throws IOException {
		if (viewToOpen.equals("GrosserAddCustomer"))
		{
			openCustomer(viewToOpen);
			return;
		}

		Scene scene = sceneLoader(viewToOpen);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}


}
