package client.customerclient.views.customerbasket;

import client.core.ViewModelFactory;
import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import client.core.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import shared.wares.Product;

import java.io.IOException;

// Andreas Østergaard, Andreas Young, Line Guld

public class BasketViewController implements CustomerViewController {
    @FXML
    private TableView basketTable;
    @FXML
    private TableColumn basketAntal;
    @FXML
    private TableColumn basketProduct;
    @FXML
    private Text basketSum;

    private ViewHandler viewHandler;
    private BasketViewModel viewModel;
    private ProductAndInt productAndInt;

    private final ObservableList<Product> data =
            FXCollections.observableArrayList();

    @Override
    public void init(ViewHandler viewHandler, CustomerViewModel viewModel) {
        this.viewHandler = viewHandler;
        this.viewModel = (BasketViewModel) ViewModelFactory.getInstance().basketViewModel();
        loadAllProducts();
    }

    public void loadAllProducts() {
        basketProduct.setCellValueFactory(new PropertyValueFactory<>("Product"));
        basketAntal.setCellValueFactory(new PropertyValueFactory<>("Antal"));

        double price = 0;

        for (Product i : viewModel.loadAllProducts().keySet()) {
            ProductAndInt productAndInt = new ProductAndInt(i.getWareName(), i.getWareNumber(),viewModel.loadAllProducts().get(i));
            price += i.getPrice() * viewModel.loadAllProducts().get(i);
            basketTable.getItems().add(productAndInt);
        }

        basketSum.setText("SUM: "+price +", DDK");
    }

    public void removeItemFromBasket() {

        ObservableList<Object> list = basketTable.getSelectionModel().getSelectedItems();
        viewModel.removeFromBasket(list);

        //TODO: Viewet refresher ikke, efter fjerelse

        // kunne ikke få det til at virke gliende så tager care af vigtigere ting

        // Men selve det at fjerne virker behind the scenes.

    }

    public void saveBasketToBin(ActionEvent actionEvent) {
        //TODO: (SaveBasket) Ekstra funktionallitet, slet ikke vigtig
    }

    public void SendOrder(){
		viewModel.sendOrder();
		basketTable.getItems().clear();
		basketSum.setText("");
		viewModel.loadAllProducts().clear();
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
