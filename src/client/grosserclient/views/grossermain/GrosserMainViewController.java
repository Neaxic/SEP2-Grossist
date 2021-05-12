package client.grosserclient.views.grossermain;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.fxml.FXML;
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

  private ViewHandler viewHandler;
  private GrosserMainViewModel viewModel;

  @Override public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
    viewModel = (GrosserMainViewModel) ViewModelFactory.getInstance().grosserMainViewModel();

  }

  private void populateTable()
  {
    tableCVR.setCellValueFactory(new PropertyValueFactory<>("CVR"));
    tableOrderNo.setCellValueFactory(new PropertyValueFactory<>("OrderNo"));
    tableOrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
    tableSum.setCellValueFactory(new PropertyValueFactory<>("Sum"));

    //TODO: Get ArrayList;
  }

  @Override public void swapScene(String sceneName) throws IOException
  {
    viewHandler.openView(sceneName);
  }
}
