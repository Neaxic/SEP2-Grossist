package client.grosserclient.views.grosseraddcustomer;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import shared.objects.CustomerContainer;

import java.io.IOException;

public class GrosserAddCustomerViewController implements GrosserViewController
{
  @FXML private TextField nameField;
  @FXML private TextField streetField;
  @FXML private TextField zipField;
  @FXML private TextField cityField;
  @FXML private TextField CVRField;
  @FXML private TextField passwordField;

  private ViewHandler viewHandler;
  private GrosserAddCustomerViewModel viewModel;

  @FXML public void create()
  {
    if (!(CVRField.getText().matches("[0-9]+$")) || CVRField.getText().length() != 8)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("CVR Fejl");
      alert.setHeaderText(null);
      alert.setContentText("Ugyldigt CVR nummer, prøv igen.");

      alert.showAndWait();
      return;
    }

    if (!(passwordField.getText().matches("[a-z]")) || !(passwordField.getText().matches("[A-Z]"))
      || !(passwordField.getText().matches("[0-9]")))
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

    int CVR = Integer.parseInt(CVRField.getText());
    String address = streetField.getText() + ", " + zipField.getText() + " " + cityField.getText();

    CustomerContainer customer = new CustomerContainer(CVR, address, passwordField.getText(), nameField.getText());


  }

  @Override public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
    viewModel = ViewModelFactory.getInstance().grosserAddCustomerViewModel();
  }

  @Override public void swapScene(String sceneName) throws IOException
  {
    viewHandler.openView(sceneName);
  }
}
