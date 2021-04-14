package client.core;

import javafx.stage.Stage;

import java.io.IOException;

public interface ViewHandler {
	void start(Stage primaryStage, ViewModelFactory viewModelFactory) throws IOException;
	void openView(String viewToOpen) throws IOException;
	ViewModel getViewModelByViewName(String viewName);
}
