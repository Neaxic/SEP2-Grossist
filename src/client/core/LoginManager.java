package client.core;

import client.core.factories.ClientFactory;
import client.core.viewhandlers.ProxyViewHandler;
import client.network.LoginInfoInterface;
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
import shared.util.md5;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

// Frederik Bergmann, Andreas Østergaard, Young (Young er på her, fordi jeg fik Systemet til at stoppe når man lukker Login vinduet :D)

public class LoginManager {
	private final ProxyViewHandler PVH;
	private final LoginInfoInterface LII;

	public static int cvr = 0;


	public LoginManager(ProxyViewHandler PVH) {
		this.PVH = PVH;
		LII = (LoginInfoInterface) ClientFactory.getInstance().getClient();
	}

	//For at vi kan få CVR til vores order sending


	public void login() {
		Dialog<Pair<String, String>> loginDialog = new Dialog<>();
		loginDialog.setTitle("Login");
		loginDialog.setHeaderText("Indtast konto oplysninger.");

		ImageView loginPNG = new ImageView();
		Image imagelogin = new Image("shared/images/login.png");
		loginPNG.setImage(imagelogin);
		loginPNG.setFitHeight(64);
		loginPNG.setFitWidth(64);

		Stage stage = (Stage) loginDialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("../../shared/images/login.png")).toString()));

		loginDialog.setGraphic(loginPNG);

		ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
		loginDialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("CVR");
		PasswordField password = new PasswordField();
		password.setPromptText("Kodeord");

		grid.add(new Label("CVR:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Kodeord:"), 0, 1);
		grid.add(password, 1, 1);

		Node loginButton = loginDialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		username.textProperty().addListener((Observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.isEmpty());
		});

		loginDialog.getDialogPane().setContent(grid);

		Platform.runLater(username::requestFocus);

		loginDialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			} else if (dialogButton == ButtonType.CANCEL) {
				return new Pair<>("cancel", null);
			}
			return null;
		});

		Optional<Pair<String, String>> loginInfo = loginDialog.showAndWait();
		checkLogin(loginInfo);
	}

	private void checkLogin(Optional<Pair<String, String>> loginInfo) {
		String pw = "";
		String inputName = "";
		ViewHandler viewHandler;

		if (loginInfo.isPresent()) {
			inputName = loginInfo.get().getKey();
			pw = loginInfo.get().getValue();
		}

		if (intCheck(inputName))
		{
			cvr = Integer.parseInt(inputName);

			Map<Integer, String> loginMap = new HashMap<>();
			loginMap = LII.getLoginInfo();

			if (loginMap.get(cvr) != null)
			{
				if (md5.compare(pw, loginMap.get(cvr)))
				{
					PVH.customerLogin();
				}
				else
				{
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Kodeord fejl");
					alert.setHeaderText(null);
					alert.setContentText("Kodeord er forkert, prøv igen.");

					alert.showAndWait();

					login();
				}

			}
			else
			{
				cvrError();
			}
		}
		else
		{
			if (inputName.equalsIgnoreCase("admin")) PVH.grosserLogin();
			else if (inputName.equals("cancel")) System.exit(-1);
			else cvrError();
		}
	}

	private boolean intCheck(String str)
	{
		if (str == null) return false;

		try
		{
			int i = Integer.parseInt(str);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	private void cvrError()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("CVR fejl");
		alert.setHeaderText(null);
		alert.setContentText(
				"Det indtastede CVR nummer er ikke registreret, prøv igen.");

		alert.showAndWait();

		login();
	}
}
