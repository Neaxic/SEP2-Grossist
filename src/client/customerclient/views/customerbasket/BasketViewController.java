package client.customerclient.views.customerbasket;

import client.core.ViewModelFactory;
import client.customerclient.views.CustomerViewController;
import client.customerclient.views.CustomerViewModel;
import client.core.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
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

        for (Product i : viewModel.loadAllProducts().keySet()) {
            ProductAndInt productAndInt = new ProductAndInt(i.getWareName(), viewModel.loadAllProducts().get(i));
            basketTable.getItems().add(productAndInt);
        }
    }

    public void removeItemFromBasket() {
    /*
        ObservableList<Product> list = basketTable.getSelectionModel().getSelectedItems();
        viewModel.removeFromBasket(list);
        basketTable.getSelectionModel().getSelectedCells().removeAll(list);
        */
    }

    public void saveBasketToBin(ActionEvent actionEvent) {
        //TODO
    }

    public void SendOrder(){
		viewModel.sendOrder();
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