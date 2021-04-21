package client.customerclient.views.customerbrowser;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import shared.wares.Product;

import java.io.IOException;

public class CustomerBrowserViewController implements CustomerViewController {
	// Category Labels
	@FXML private Text categoryColonial;
	@FXML private Text categoryDiaryAndEggs;
	@FXML private Text categoryFrozenFoods;
	@FXML private Text categoryFruitAndVegetables;
	@FXML private Text categoryMeatAndSeafoods;
	@FXML private Text categoryDrinks;
	@FXML private Text categoryAlcohol;
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
		viewModel.loadAllProducts();
		// TODO: Load All Products from the Database
	}

	public void loadSpecificCategory(MouseEvent mouseEvent) {
		Node selected = mouseEvent.getPickResult().getIntersectedNode();
		if (selected instanceof Text) {
			String category = ((Text) selected).getText();
			System.out.println(category); // Should return text as a string
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
		// Nodes regarding the Item
		Text title = new Text(product.getName());
		Text price = new Text("" + product.getPrice());
		TextField amount = new TextField();
		Button addButton = new Button("Tilføj til Kurv");
		// Design of Nodes
		title.setFont(Font.font("Segoe UI", FontWeight.BLACK, 32));
		price.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, 24));
		amount.setPromptText("Mængde");
		amount.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 16));
		addButton.setOnMouseClicked(mouseEvent -> addToBasket(mouseEvent));
		// Adding nodes to HBox
		entry.getChildren().add(title);
		entry.getChildren().add(price);
		entry.getChildren().add(amount);
		entry.getChildren().add(addButton);
		return entry;
	}

	// Mega hygger mig med at undgå at bruge deres ID fordi det skal være forskellige fra boks til boks
	public void addToBasket(MouseEvent mouseEvent) {
//			ObservableList<Node> nodes = mouseEvent.getPickResult().getIntersectedNode().getParent().getParent().getChildrenUnmodifiable();
//			String itemName = ((Text) nodes.get(0)).getText();
//			int itemAmount = Integer.parseInt(((TextField) nodes.get(2)).getText());
//			viewModel.addToCart(new Product(itemName, itemAmount, 20, "kg"));
		Node node = (Node) mouseEvent.getTarget();
		Button button = (Button) node.lookup("Button");
		if (button != null) {
			// button = (Button) node;
			//Getting which item to put in the basket
			//Vi kan eventuelt indsætte et skjult felt med varenummer og tage det i stedet for titlen. Gerne med et LABEL
			VBox box = (VBox) button.getParent().getParent();
			TextField itemName = (TextField) box.lookup("TextField");
			String item = itemName.getText();

			//Getting the amount to put in basket
			HBox amountBox = (HBox) button.getParent();
			TextField amountField = (TextField) amountBox.lookup("TextField");
			int amount = Integer.parseInt(amountField.getText());

			viewModel.addToBasket(item, amount);
			System.out.println("blergh");
		}
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
