package client.customerclient.views.customerbrowser;

import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import client.core.ViewHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class CustomerBrowserViewController implements CustomerViewController
{
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

  @Override public void init(ViewHandler viewHandler,
      CustomerViewModel viewModel)
  {
    this.viewHandler = viewHandler;
    this.viewModel = (CustomerBrowserViewModel) viewModel;
    //		loadAllProducts();
  }

  public void loadAllProducts()
  {
    viewModel.loadAllProducts();
    // TODO: Load All Products from the Database
  }

  public void loadSpecificCategory(MouseEvent mouseEvent)
  {
    Node selected = mouseEvent.getPickResult().getIntersectedNode();
    if (selected instanceof Text)
    {
      String category = ((Text) selected).getText();
      System.out.println(category); // Should return text as a string
      populate(category);
    }
  }

  private void populate(String category)
  {
    viewModel.populate(category);
    for (Object item : activeItemList)
    { // FIXME: Needs to be of the Object, used to create a Product
      productItemList.getChildren().add(createEntry(item));
    }
  }

  private HBox createEntry(Object item)
  { // FIXME: Needs to be of the Object, used to create a Product
    HBox entry = new HBox();
    return entry;
  }

  // Mega hygger mig med at undgå at bruge deres ID fordi det skal være forskellige fra boks til boks
  public void addToBasket(MouseEvent mouseEvent)
  {
    Node node = (Node) mouseEvent.getTarget();
    Button button = (Button) node.lookup("Button");
    if (button != null)
    {
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
  @Override public void swapScene(String sceneName) throws IOException
  {
    viewHandler.openView(sceneName);
  }

  public void openProductBrowser() throws IOException
  {
    swapScene("CustomerBrowser");
  }

  public void openSubscriptions() throws IOException
  {
    swapScene("CustomerSubscriptions");
  }

  public void openBasket() throws IOException
  {
    swapScene("CustomerBasket");
  }
}
