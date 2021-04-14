package client.core;

import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerViewHandler implements ViewHandler{

	private Stage primaryStage;
	private Scene currentScene;
	private ViewModelFactory viewModelFactory;

	@Override
	public void start(Stage primaryStage, ViewModelFactory viewModelFactory) throws IOException {
		this.viewModelFactory = viewModelFactory;

		this.primaryStage = primaryStage;
		this.currentScene = new Scene(new Region());
		openView("customerbrowse");
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

		loader.setLocation(getClass().getResource("../CustomerClient/Views/" + view +"/" +view + ".fxml"));
		root = loader.load();

		CustomerViewController viewController = loader.getController();
		viewController.init(this, (CustomerViewModel) getViewModelByViewName(view));

		scene = new Scene(root);
		return scene;
	}

	//Problemet er ViewModel interface forventer viewmodel men vi har kun Customer eller GrosistViewmodel..
	@Override
	public ViewModel getViewModelByViewName(String viewName) {
		return switch(viewName){
			case "customerbrowse" -> viewModelFactory.customerBrowseViewModel();
			case "customerbasket" -> viewModelFactory.basketViewModel();
			case "customersubscriptions" -> viewModelFactory.customerBrowseViewModel();
			default -> null;
		};
	}
}
