package client.core;

import client.grosserclient.views.GrosserViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

// Andreas Østergaard, Frederik Bergmann, Andreas Young

public class GrosserViewHandler implements ViewHandler {
	private Stage primaryStage;
	private Scene currentScene;

	private ViewModelFactory viewModelFactory;
	private ViewHandler proxyViewHandler;

	public GrosserViewHandler(ProxyViewHandler proxyViewHandler) {
		this.proxyViewHandler = proxyViewHandler;
	}

	private Scene sceneLoader(String view) throws IOException {
		Scene scene;
		Parent root;

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("../grosserclient/views/" + view + "/" + view + ".fxml"));
		root = loader.load();

		GrosserViewController viewController = loader.getController();
		viewController.init(this);

		scene = new Scene(root);
		return scene;
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.currentScene = new Scene(new Region());
		openView("GrosserMain");
	}

	@Override
	public void openView(String viewToOpen) throws IOException {
		Scene scene = sceneLoader(viewToOpen);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public ViewModel getViewModelByViewName(String viewName) {
		return switch(viewName){
			//TODO: HEr skal også laves om på metoden fra vmf men ingen vm's klar endnu.
			case "customerBrowse" -> viewModelFactory.customerBrowseViewModel();
			default -> null;
		};
	}


}
