package client.customerclient.views.customerbrowser;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import shared.wares.Product;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomerBrowserViewController implements CustomerViewController {
	// Category Labels
	@FXML private Text categoryColonial;
	@FXML private Text categoryDiaryAndEggs;
	@FXML private Text categoryFrozenFoods;
	@FXML private Text categoryFruitAndVegetables;
	@FXML private Text categoryMeatAndSeafoods;
	@FXML private Text categoryDrinks;
	@FXML private Text categoryAlcohol;
	@FXML private ScrollPane SPane;
	// Item List for Product Population
	@FXML private VBox productItemList;

	private ViewHandler viewHandler;
	private CustomerBrowserViewModel viewModel;

	private ListProperty<Product> itemList;

	@Override
	public void init(ViewHandler viewHandler, CustomerViewModel removeMe) {
		this.viewHandler = viewHandler;
		viewModel = (CustomerBrowserViewModel) ViewModelFactory.getInstance().customerBrowseViewModel();
		itemList = new SimpleListProperty<>();
		itemList.bind(viewModel.activeItemListProperty());
		loadAllProducts();
		populate("all");
	}

	public void loadAllProducts() {
		viewModel.loadAllProductsToModel();
		// TODO: Load All Products from the Database
	}

	public void loadSpecificCategory(MouseEvent mouseEvent) {
		Node selected = mouseEvent.getPickResult().getIntersectedNode();
		if (selected instanceof Text) {
			String category = ((Text) selected).getText().substring(2);
			System.out.println(category);
			populate(category);
		}
	}

	private void populate(String category) {
		productItemList.getChildren().clear();
		// viewModel.populate(category);
		for (Product item : itemList) {
			productItemList.getChildren().add(createEntry(item));
		}
	}

	private HBox createEntry(Product product) {
		// HBox for the Food Item Entry
		HBox entry = new HBox();

		VBox vBox = new VBox();
		HBox btnHBox = new HBox();
		HBox prisHBox = new HBox();
		HBox main = new HBox();
		main.styleProperty().setValue("-fx-border-color: lightgray;");
		// Nodes regarding the Item
		Text title = new Text(product.getWareName());
		main.setMaxWidth(615);
		Text desc = new Text("\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ");
		//desc.wrappingWidthProperty().bind(SPane.widthProperty());
		TextFlow textFlow = new TextFlow(desc);
		//main.width
		Image image;
		try{
			image = new Image("shared/images/" + product.getClass().toString().substring(19) +".jpg");
		} catch (RuntimeException e){
			image = new Image("shared/images/150placeholder.png");
		}
		ImageView iv2 = new ImageView();
		iv2.setImage(image);
		iv2.setFitWidth(150);
		iv2.setPreserveRatio(true);
		iv2.setSmooth(true);
		iv2.setCache(true);

		Text price = new Text("" + product.getPrice() + "DKK ,-");
		price.setTextAlignment(TextAlignment.RIGHT);
		TextField amount = new TextField();
		Button addButton = new Button("Tilføj til Kurv");
		addButton.setPrefSize(100, 50);
		// Design of Nodes
		title.setFont(Font.font("Segoe UI", FontWeight.BLACK, 21));
		desc.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 15));
		price.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, 21));
		amount.setPromptText("Mængde");
		amount.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 16));
		addButton.setOnMouseClicked(mouseEvent -> addToBasket(mouseEvent));
		// Adding nodes to HBox

		prisHBox.getChildren().add(title);
		prisHBox.getChildren().add(price);
		vBox.getChildren().add(prisHBox);

		textFlow.setPadding(new Insets(5, 30, 10, 0));
		vBox.getChildren().add(textFlow);

		btnHBox.getChildren().add(amount);
		btnHBox.getChildren().add(addButton);
		btnHBox.alignmentProperty().setValue(Pos.CENTER_RIGHT);
		btnHBox.setPadding(new Insets(0, 5, 5, 0));

		vBox.getChildren().add(btnHBox);

		main.getChildren().add(iv2);
		vBox.setPadding(new Insets(0, 0, 0, 10));
		main.getChildren().add(vBox);
		//main.setPrefWidth(150);

		entry.getChildren().add(main);
		entry.setPadding(new Insets(5, 0, 5, 0));
		return entry;
	}

	// Mega hygger mig med at undgå at bruge deres ID fordi det skal være forskellige fra boks til boks
	public void addToBasket(MouseEvent mouseEvent) {
//			ObservableList<Node> nodes = mouseEvent.getPickResult().getIntersectedNode().getParent().getParent().getChildrenUnmodifiable();
//			String itemName = ((Text) nodes.get(0)).getText();
//			int itemAmount = Integer.parseInt(((TextField) nodes.get(2)).getText());
//			viewModel.addToCart(new Product(itemName, itemAmount, 20, "kg"));
		Node node = (Node) mouseEvent.getTarget(); // Giver Tekst feltet på Knappen hvis man klikker hvor teksten er
		Button button;
		if (!(node instanceof Button)) {
			button = (Button) node.getParent();
		} else {
			button = (Button) node;
		}
		if (button != null) {
			// button = (Button) node;
			// Getting which item to put in the basket
			// Vi kan eventuelt indsætte et skjult felt med varenummer og tage det i stedet for titlen. Gerne med et LABEL
			VBox box = (VBox) button.getParent().getParent();
			Text itemName = (Text) box.lookup("Text");
			String item = itemName.getText();

			// Getting the amount to put in basket
			HBox amountBox = (HBox) button.getParent();
			TextField amountField = (TextField) amountBox.lookup("TextField");
			if (amountField.getText().isEmpty() || containsLetters(amountField.getText())) {
				System.out.println("Amount field only accepts Integer Numbers");
			} else {
				int amount = Integer.parseInt(amountField.getText());
				viewModel.addToBasket(item, amount);
			}
		}
	}
	// Ved ikke om denne skal være her? Tænker det giver mest mening da den tjekker på noget der er på vores view
	private boolean containsLetters(String text) {
		for (char c : text.toCharArray()) {
			if (!Character.isDigit(c)) {
				return true;
			}
		}
		return false;
	}

	// SCENE MANAGING
	@Override
	public void swapScene(String sceneName) throws IOException {
		viewHandler.openView(sceneName);
	}

	public void openProductBrowser() throws IOException {
		swapScene("CustomerBrowser");
	}

	public void openSubscriptions() throws IOException {
		swapScene("CustomerSubscriptions");
	}

	public void openBasket() throws IOException {
		swapScene("CustomerBasket");
	}
}
