package client.CustomerClient.Views.CustomerBrowser;

import client.CustomerClient.Views.CustomerViewController;
import client.CustomerClient.Views.ViewModel;
import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
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

	public void tempMethodForSettingController() {
		System.out.println("duh");
	}

	@Override
	public void init(ViewHandler viewHandler, ViewModel viewModel) {
		// TODO
	}

	@Override
	public void swapScene(String sceneName) {
		// TODO
	}

	public void loadAllProducts() {
		// TODO: Load All Products from the Database
		// productItemList needs to be populated
	}

	public void loadSpecificCategory(MouseEvent mouseEvent) {
		Node selected = mouseEvent.getPickResult().getIntersectedNode();
		if (selected instanceof Text){
			System.out.println(((Text) selected).getText()); // Should return text as a string
		}
	}

	private void populate(String category){
		switch (category.toLowerCase()){
			case "all":
				// TODO: Implement Population
				return;
			case "colonial":
				// TODO: Implement Population
				return;
			case "diary and eggs":
				// TODO: Implement Population
				return;
			case "frozen food":
				// TODO: Implement Population
				return;
			case "fruits and vegetables":
				// TODO: Implement Population
				return;
			case "meat and seafood":
				// TODO: Implement Population
				return;
			case "beverages (non-alcohol)":
				// TODO: Implement Population
				return;
			case "beverages (alcohol)":
				// TODO: Implement Population
				return;
			default:
				throw new IllegalArgumentException("Invalid Category for Population of Items");
		}
	}
}
