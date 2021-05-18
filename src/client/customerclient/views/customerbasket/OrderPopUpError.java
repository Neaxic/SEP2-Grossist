package client.customerclient.views.customerbasket;

// Young

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shared.wares.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderPopUpError {
	private static Stage popupWindow;
	private static String decision;

	public static String display(ArrayList<Product> productCausingError) {
		popupWindow = new Stage();
		popupWindow.initModality(Modality.APPLICATION_MODAL);
		popupWindow.setTitle("Ordre Fejl");

		Label infoLabel1 = new Label("Der er en fejl med din ordre");
		Label infoLabel2 = new Label("Skal vi fjerne den udsolgte vare fra din kurv?");
		Label errorProduct = new Label(listFormat(productCausingError) + " fjernes fra hvis du Godkender");

		Button confirm = new Button("Godkend");
		Button cancel = new Button("Afvis");

		confirm.setOnAction(e -> confirm());
		cancel.setOnAction(e -> cancel());

		VBox layout = new VBox(10);
		HBox buttonLayout = new HBox(10);

		buttonLayout.getChildren().addAll(confirm, cancel);
		buttonLayout.setAlignment(Pos.CENTER);
		buttonLayout.setSpacing(5);
		layout.getChildren().addAll(infoLabel1, infoLabel2, buttonLayout, errorProduct);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 250);
		popupWindow.setScene(scene1);
		popupWindow.showAndWait();
		return decision;
	}

	private static String listFormat(List list) {
		StringBuilder s = new StringBuilder();
		for (Object o : list) {
			s.append(o.toString()).append(", ");
		}
		return s.toString();
	}

	private static void confirm() {
		decision = "confirm";
		closeWindow();
	}

	private static void cancel() {
		decision = "cancel";
		closeWindow();
	}

	private static void closeWindow() {
		popupWindow.close();
	}
}
