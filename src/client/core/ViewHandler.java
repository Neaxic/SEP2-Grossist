package client.core;

<<<<<<< Updated upstream
import client.CustomerClient.Views.ViewModel;
=======
import javafx.stage.Stage;
>>>>>>> Stashed changes

public interface ViewHandler {
	void start(Stage primaryStage);
	void openView(String viewToOpen);

	ViewModel getViewModelByViewName(String viewName);
}
