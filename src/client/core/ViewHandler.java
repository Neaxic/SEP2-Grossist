package client.core;

import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

// Andreas Østergaard

public interface ViewHandler {
	void start(Stage primaryStage) throws IOException, SQLException;
	void openView(String viewToOpen) throws IOException, SQLException;
}
