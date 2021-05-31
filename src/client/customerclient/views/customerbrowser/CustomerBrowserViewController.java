package client.customerclient.views.customerbrowser;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.customerclient.views.CustomerViewController;
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
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import server.model.databasemediator.DAOModel;
import shared.wares.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

// Andreas Young, Line Guld, Andreas Østergaard og Kasper Falk

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
	@FXML private TextField searchText;
	// Item List for Product Population
	@FXML private VBox productItemList;

	private ViewHandler viewHandler;
	private CustomerBrowserViewModel viewModel;

	private ListProperty<Product> itemList;
	private DAOModel daomodel2 = new DAOModel();

	public CustomerBrowserViewController() throws SQLException {
	}

	@Override
	public void init(ViewHandler viewHandler) throws SQLException {
		this.viewHandler = viewHandler;
		viewModel = (CustomerBrowserViewModel) ViewModelFactory.getInstance().customerBrowseViewModel();
		itemList = new SimpleListProperty<>();
		itemList.bind(viewModel.activeItemListProperty());
		loadAllProducts();
		populate("Alt");
	}

	public void loadAllProducts() {
		viewModel.loadAllProductsToModel();


	}

	public void loadSpecificCategory(MouseEvent mouseEvent) throws SQLException {
		Node selected = mouseEvent.getPickResult().getIntersectedNode();
		if (selected instanceof Text) {
			String category = ((Text) selected).getText().substring(2);
			populate(category);
		}
	}

	private void populate(String category) throws SQLException {
		productItemList.getChildren().clear();
		ArrayList<Product> SpecificItemList = viewModel.populate(category);

		for (Product item: SpecificItemList) {
			if (daomodel2.getDiscounted(item) == false && getDaysTo(item) <= 3)  {
				daomodel2.changePrice(item,.75);
				daomodel2.setDiscounted(item);
			}
		}


		for(Product item: SpecificItemList){
			productItemList.getChildren().add(createEntry(item));

		}
	}

	public void searchBtn() throws SQLException {
		productItemList.getChildren().clear();
		ArrayList<Product> SpecificItemList = viewModel.searchBtn(searchText.textProperty().getValue());
		for(Product item: SpecificItemList){
			productItemList.getChildren().add(createEntry(item));
		}
	}

	private HBox createEntry(Product product) throws SQLException {
		// HBox for the Food Item Entry
		HBox entry = new HBox();

		VBox vBox = new VBox();
		HBox btnHBox = new HBox();
		HBox prisHBox = new HBox();
		HBox main = new HBox();
		main.styleProperty().setValue("-fx-border-color: lightgray;");
		// Nodes regarding the Item
		Text title = new Text(product.getWareName());
		Text productID = new Text("Varenummer: "+product.getWareNumber());
		productID.setId("ProductID");
		main.setMaxWidth(615);
		Text desc = new Text("\" "+product.getTags());
		desc.wrappingWidthProperty().bind(SPane.widthProperty());
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

		Text price = new Text("" + product.getPrice() + " DKK ,-");
		price.setTextAlignment(TextAlignment.RIGHT);
		TextField amount = new TextField();
		Button addButton = new Button("Tilføj til Kurv");
		addButton.setPrefSize(100, 50);
		// Design of Nodes
		title.setFont(Font.font("Segoe UI", FontWeight.BLACK, 21));
		productID.setFont(Font.font("Segoe UI", FontWeight.BLACK, 15));
		desc.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 15));
		price.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, 21));
		amount.setPromptText("Mængde");
		amount.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 16));
		addButton.setOnMouseClicked(mouseEvent -> addToBasket(mouseEvent));
		// Adding nodes to HBox

		prisHBox.getChildren().add(title);
		prisHBox.getChildren().add(price);
		vBox.getChildren().add(prisHBox);



		//textFlow.setPadding(new Insets(5, 30, 10, 0));
		textFlow.setPadding(new Insets(5, 30, 10, 0));
		vBox.getChildren().add(productID);

		//NEDSAT VARE
		if (getDaysTo(product) <= 3) {
			Text NedsatVare = null;
			if (getDaysTo(product) == 1) {
				NedsatVare = new Text("Kort holdbarhedsdato: " +  getDaysTo(product)+ " dag");
			} else {
				NedsatVare = new Text("Kort holdbarhedsdato: " +  getDaysTo(product)+ " dage");
			}
			NedsatVare.setFill(Color.RED);
			vBox.getChildren().add(NedsatVare);
		}



		//vBox.getChildren().add(textFlow);
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

	//
	public int getDaysTo(Product product) {
		int days = LocalDate.now().until(product.getBestBefore()).getDays();
		return days;
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

			// var relevant før til at få navn, men vi bruger id nu til at få specifik produkt.
			//VBox box = (VBox) button.getParent().getParent();
			//Text itemName = (Text) box.lookup("Text");
			//String item = itemName.getText();

			Text box2 = (Text) button.getParent().getParent().lookup("#ProductID");
			int itemID = Integer.parseInt(box2.getText().substring(11).stripLeading());

			// Getting the amount to put in basket
			HBox amountBox = (HBox) button.getParent();
			TextField amountField = (TextField) amountBox.lookup("TextField");
			if (amountField.getText().isEmpty() || containsLetters(amountField.getText())) {
				System.out.println("Amount field only accepts Integer Numbers");
			} else {
				int amount = Integer.parseInt(amountField.getText());
				viewModel.addToBasket(itemID, amount);
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
	public void swapScene(String sceneName) throws IOException, SQLException {
		viewHandler.openView(sceneName);
	}

	public void openProductBrowser() throws IOException, SQLException {
		swapScene("CustomerBrowser");
	}

	/*
	public void openSubscriptions() throws IOException {
		swapScene("CustomerSubscriptions");
	}
	 */

	public void openBasket() throws IOException, SQLException {
		swapScene("CustomerBasket");
	}
}
