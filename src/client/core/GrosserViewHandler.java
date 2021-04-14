package client.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import client.CustomerClient.Views.ViewModel;

public class GrosserViewHandler implements ViewHandler {
	private Stage primaryStage;
	private Scene currentScene;

	private ViewModelFactory viewModelFactory;

	public GrosserViewHandler(ViewModelFactory viewModelFactory)
	{
		this.viewModelFactory = viewModelFactory;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.currentScene = new Scene(new Region());
		openView("GrosserMain");
	}

	@Override
	public void openView(String viewToOpen) {
		Region root = null;
		switch(viewToOpen){
			case "GrosserMain":
				root = loadGrosserMain(viewToOpen+".fxml");
				break;
			case "GrosserAddItemAmount":
				root = loadGrosserAddItemAmount(viewToOpen+".fxml");
				break;
		}
		currentScene.setRoot(root);

		primaryStage.setTitle("Grossist");
		primaryStage.setScene(currentScene);
		primaryStage.setWidth(root.getPrefWidth());
		primaryStage.setHeight(root.getPrefHeight());
		primaryStage.show();
	}

	@Override
	public ViewModel getViewModelByViewName(String viewName) {
		return swtich(viewName){
			case "Main" -> viewModelFactory.getMain();
		};
		//return viewModel;
		return null;
	}


	private Region loadGrosserMain(String fxmlFile)
	{
		if (GrosserViewController == null)
		{
			try
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource( "../GrosserClient.Views/GrosserMain" +fxmlFile));
				Region root = loader.load();
				GrosserViewController = loader.getController();
				GrosserViewController.init(this, viewModelFactory.getViewModelSettings(), root);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			GrosserViewController.reset();
		}
		return GrosserViewController.getRoot();
	}




}
