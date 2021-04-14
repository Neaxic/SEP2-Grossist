package client.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class GrosserViewHandler implements ViewHandler {
	private Stage primaryStage;
	private Scene currentScene;

	private ViewModelFactory viewModelFactory;

	@Override
	public void start(Stage primaryStage, ViewModelFactory viewModelFactory) throws IOException {
		this.viewModelFactory = viewModelFactory;
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

	private Scene sceneLoader(String view) throws IOException {
		Scene scene;
		Parent root;

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("../CustomerClient.Views/" + view.toLowerCase(Locale.ROOT)+"/" +view + ".fxml"));
		root = loader.load();

		GrosserViewHandler viewController = loader.getController();
		// TODO: Der ingen Controller til groist endnu
		// viewController.init(this, getViewModelByViewName(view));

		scene = new Scene(root);
		return scene;
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
