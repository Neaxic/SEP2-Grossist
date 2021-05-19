package client.grosserclient.views.grossermain;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.wares.Order;

import java.io.IOException;
import java.time.LocalDate;

// Frederik Bergmann

public class GrosserMainViewController implements GrosserViewController
{
  @FXML private TextField searchBar;
  @FXML private TableView<Order> orderTable;
  @FXML private TableColumn<Order, Integer> tableCVR;
  @FXML private TableColumn<Order, Integer> tableOrderNo;
  @FXML private TableColumn<Order, LocalDate> tableOrderDate;
  @FXML private TableColumn<Order, Double> tableSum;

  private ListProperty<Order> orderList;

  @FXML private MenuBar mainNavBar;

  private ViewHandler viewHandler;
  private GrosserMainViewModel viewModel;

  @Override public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
    viewModel = (GrosserMainViewModel) ViewModelFactory.getInstance().grosserMainViewModel();
    orderList = new SimpleListProperty<>();
    orderList.bind(viewModel.getOrderListProperty());
    viewModel.getAllOrders();
    populateTable();
  }

  private void populateTable()
  {
    tableCVR.setCellValueFactory(new PropertyValueFactory<>("CVR"));
    tableOrderNo.setCellValueFactory(new PropertyValueFactory<>("OrderNo"));
    tableOrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
    tableSum.setCellValueFactory(new PropertyValueFactory<>("Sum"));

    orderTable.getItems().addAll(orderList);
  }

  @FXML private void updateOrders()
  {
    orderTable.getItems().clear();
    viewModel.getAllOrders();
    populateTable();
  }

  @FXML private void addNewProductScene() throws IOException {
    this.swapScene("grosseraddproduct");
  }

  @Override public void swapScene(String sceneName) throws IOException
  {
    viewHandler.openView(sceneName);
  }
}
