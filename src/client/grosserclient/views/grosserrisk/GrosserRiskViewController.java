package client.grosserclient.views.grosserrisk;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.RISK_ASSESSMENT.RiskReport;

import java.io.IOException;
import java.sql.SQLException;

public class GrosserRiskViewController implements GrosserViewController
{
  @FXML private TableView<RiskReport> riskTable;
  @FXML private TableColumn<RiskReport, Integer> idColumn;
  @FXML private TableColumn<RiskReport, String> actionColumn;
  @FXML private TableColumn<RiskReport, String> typeColumn;

  private ListProperty<RiskReport> riskList;

  private ViewHandler viewHandler;
  private GrosserRiskViewModel viewModel;

  @Override public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
    viewModel = ViewModelFactory.getInstance().grosserRiskViewModel();
    riskList = new SimpleListProperty<>();
    riskList.bind(viewModel.getRiskReportsProperty());
  }

  private void populateTable()
  {
    idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
    actionColumn.setCellValueFactory(new PropertyValueFactory<>("recommendation"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

    riskTable.getItems().addAll(riskList);
  }

  @FXML
  private void runRiskAssessment()
  {
    riskTable.getItems().clear();
    viewModel.getRiskData();
    populateTable();
  }

  @FXML
  private void mainScene() throws SQLException, IOException
  {
    swapScene("GrosserMain");
  }

  @FXML
  private void addNewProductScene() throws IOException, SQLException {
    swapScene("GrosserAddProduct");
  }
  @FXML
  private void waresScene() throws IOException, SQLException {
    swapScene("GrosserWares");
  }

  @Override public void swapScene(String sceneName)
      throws IOException, SQLException
  {
    viewHandler.openView(sceneName);
  }
}
