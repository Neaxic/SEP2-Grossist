package client.core;

import javafx.stage.Stage;

import java.io.IOException;

// Andreas Østergaard

public interface ViewHandler {
	void start(Stage primaryStage) throws IOException;
	void openView(String viewToOpen) throws IOException;
	ViewModel getViewModelByViewName(String viewName);
}
