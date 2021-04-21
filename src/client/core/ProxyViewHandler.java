package client.core;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class ProxyViewHandler implements ViewHandler {
	private ViewHandler viewHandler;
	private final ViewModelFactory viewModelFactory;
	private final Stage stage;

	public ProxyViewHandler(Stage stage) {
		this.stage = stage;
		viewModelFactory = ViewModelFactory.getInstance();
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		viewHandler.start(primaryStage);
	}

	@Override
	public void openView(String viewToOpen) throws IOException {
		viewHandler.openView(viewToOpen);
	}

	@Override
	public ViewModel getViewModelByViewName(String viewName) {
		return viewHandler.getViewModelByViewName(viewName);
	}

	public void login() throws IOException {
		Dialog<Pair<Integer, String>> loginDialog = new Dialog<>();
		loginDialog.setTitle("Login");
		loginDialog.setHeaderText("Indtast konto oplysninger.");

		ImageView loginPNG = new ImageView(Objects.requireNonNull(
				this.getClass().getResource("../../shared/images/login.png")).toString());
		loginPNG.setFitHeight(64);
		loginPNG.setFitWidth(64);

		Stage stage = (Stage) loginDialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(Objects.requireNonNull(
				this.getClass().getResource("../../shared/images/login.png")).toString()));

		loginDialog.setGraphic(loginPNG);

		ButtonType loginButtonType = new ButtonType("Login",
				ButtonBar.ButtonData.OK_DONE);
		loginDialog.getDialogPane().getButtonTypes()
				.addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("CVR");
		username.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
		PasswordField password = new PasswordField();
		password.setPromptText("Kodeord");

		grid.add(new Label("CVR:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Kodeord:"), 0, 1);
		grid.add(password, 1, 1);

		Node loginButton = loginDialog.getDialogPane()
				.lookupButton(loginButtonType);
		loginButton.setDisable(true);

		username.textProperty().addListener((Observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.isEmpty());
		});

		loginDialog.getDialogPane().setContent(grid);

		Platform.runLater(username::requestFocus);

		loginDialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(Integer.parseInt(username.getText()), password.getText());
			} else if (dialogButton == ButtonType.CANCEL) {
				return new Pair<>(-1, null);
			}
			return null;
		});

		Optional<Pair<Integer, String>> loginInfo = loginDialog.showAndWait();
		checkLogin(loginInfo);
	}

	private void checkLogin(Optional<Pair<Integer, String>> loginInfo)
			throws IOException {
		int cvr = 0;
		String pw = "";

		if (loginInfo.isPresent()) {
			cvr = loginInfo.get().getKey();
			pw = loginInfo.get().getValue();
		}

		if (cvr == -1) {
			System.out.println("Terminated");
		} else if (cvr == 1 && pw.equals("1234")) {
			viewHandler = new CustomerViewHandler(this);
			start(stage);
		} else if (cvr == 2 && pw.equals("1234")) {
			viewHandler = new GrosserViewHandler(this);
			start(stage);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fejl!");
			alert.setHeaderText(null);
			alert.setContentText("De indtastede kontooplysninger er forkerte!");

			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(Objects.requireNonNull(
					this.getClass().getResource("../../shared/images/login.png")).toString()));

			alert.showAndWait();

			login();
		}
	}
}
