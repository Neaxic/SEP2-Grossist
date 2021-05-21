package client.grosserclient.views.grosseraddcustomer;

import client.core.ViewHandler;
import client.core.factories.ViewModelFactory;
import client.grosserclient.views.GrosserViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import shared.objects.CustomerContainer;

import java.io.IOException;
import java.util.regex.Pattern;

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

    int CVR = Integer.parseInt(CVRField.getText());
    String address = streetField.getText() + ", " + zipField.getText() + " " + cityField.getText();

    CustomerContainer customer = new CustomerContainer(CVR, address, passwordField.getText(), nameField.getText());


  }

  @FXML
  private void abort(ActionEvent event)
  {
    Node node = (Node) event.getSource();
    Stage thisStage = (Stage) node.getScene().getWindow();

    thisStage.close();
  }

  private boolean passwordCheck(String input)
  {
    char ch;
    boolean upperFlag = false;
    boolean lowerFlag = false;
    boolean numberFlag = false;
    boolean spaceFlag = false;

    for (int i = 0; i < input.length(); i++)
    {
      ch = input.charAt(i);
      if (Character.isUpperCase(ch)) upperFlag = true;
      else if (Character.isLowerCase(ch)) lowerFlag = true;
      else if (Character.isDigit(ch)) numberFlag = true;
      else if (Character.isWhitespace(ch)) spaceFlag = true;
    }
    return upperFlag && lowerFlag && numberFlag && !spaceFlag;
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
