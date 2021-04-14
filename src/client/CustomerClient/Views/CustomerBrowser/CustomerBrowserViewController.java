package client.CustomerClient.Views.CustomerBrowser;

import client.CustomerClient.Views.CustomerViewController;
import client.CustomerClient.Views.ViewModel;
import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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

	private Object[] activeItemList = new Object[2]; // FIXME: Needs to be of the Object, used to create a Product

	public void tempMethodForSettingController() {
		System.out.println("duh");
	}

	@Override
	public void init(ViewHandler viewHandler, ViewModel viewModel) {
		this.viewHandler = viewHandler;
		this.viewModel = (CustomerBrowserViewModel) viewModel;
		loadAllProducts();
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
		viewModel.populate(category);
		for (Object item : activeItemList) { // FIXME: Needs to be of the Object, used to create a Product
			productItemList.getChildren().add(createEntry(item));
		}
	}

	private HBox createEntry(Object item){ // FIXME: Needs to be of the Object, used to create a Product
		HBox entry = new HBox();
		return entry;
	}

	// SCENE MANAGING
	@Override
	public void swapScene(String sceneName) {
		viewHandler.openView(sceneName);
	}

	public void openBasket() {
		swapScene("basket");
	}

	public void openSubscriptions() {
		swapScene("subscriptions");
	}
}
