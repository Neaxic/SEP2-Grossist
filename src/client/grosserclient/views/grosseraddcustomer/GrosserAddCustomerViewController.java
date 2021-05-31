package client.grosserclient.views.grosseraddcustomer;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shared.objects.CustomerContainer;
import shared.util.md5;

import java.io.IOException;
import java.sql.SQLException;

// Frederik Bergmann.

public class GrosserAddCustomerViewController implements GrosserViewController
{
  @FXML private TextField nameField;
  @FXML private TextField streetField;
  @FXML private TextField zipField;
  @FXML private TextField cityField;
  @FXML private TextField CVRField;
  @FXML private TextField passwordField;
  @FXML private Button abortButton;

  private ViewHandler viewHandler;
  private GrosserAddCustomerViewModel viewModel;

  @FXML public void create()
  {
    if (!(CVRField.getText().matches("[0-9]+$"))
        || CVRField.getText().length() != 8)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("CVR Fejl");
      alert.setHeaderText(null);
      alert.setContentText("Ugyldigt CVR nummer, prøv igen.");

      alert.showAndWait();
      return;
    }

    String pw = passwordField.getText();

    if (!pw.matches("\\w+") || !pw.matches("(?s).*[A-Z].*") || !pw
        .matches("(?s).*[a-z].*") || !pw.matches("(?s).*[0-9].*"))
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Kodeord Fejl");
      alert.setHeaderText("Ugyldigt kodeord, prøv igen.");
      alert.setContentText("""
          Kodeord skal indeholde følgende:
          * Minimum 1 stort bogstav.
          * Minimum 1 lille bogstav.
          * Minimum 1 tal.""");

      alert.showAndWait();
      return;
    }

    if (passwordField.getText().length() < 8)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Kodeord Fejl");
      alert.setHeaderText("Ugyldigt kodeord, prøv igen.");
      alert.setContentText("Kodeord skal være på minimum 8 tegn.");

      alert.showAndWait();
      return;
    }

    if (fieldsCheck())
    {
      int CVR = Integer.parseInt(CVRField.getText());
      String address =
          streetField.getText() + ", " + zipField.getText() + " " + cityField
              .getText();

      pw = md5.encode(passwordField.getText());

      CustomerContainer customer = new CustomerContainer(CVR, address, pw, nameField.getText());

      if (viewModel.addCustomer(customer))
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bekræftelse");
        alert.setHeaderText(null);
        alert.setContentText("Ny kunde oprettet succesfuldt");

        alert.showAndWait();
        abortButton.fire();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(null);
        alert.setContentText("Noget gik galt, prøv igen.");

        alert.showAndWait();
      }
    }
  }

  private boolean fieldsCheck()
  {
    if (nameField.getText().isBlank() || zipField.getText().isBlank()
        || cityField.getText().isBlank() || streetField.getText().isBlank())
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Indtastnings fejl");
      alert.setHeaderText(null);
      alert.setContentText("Et eller flere felter mangler at blive udfyldt.");

      alert.showAndWait();
      return false;
    }
    else
    {
      return true;
    }
  }

  @FXML
  public void abort(ActionEvent event)
  {
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();

    stage.close();
  }

  @Override public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
    viewModel = ViewModelFactory.getInstance().grosserAddCustomerViewModel();
  }

  @Override public void swapScene(String sceneName) throws IOException, SQLException {
    viewHandler.openView(sceneName);
  }
}
