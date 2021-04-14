package core;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  static ViewHandler instance;

  private ViewHandler()
  {
    stage = new Stage();
  }

  public static ViewHandler getInstance()
  {
    if (instance == null)
    {
      instance = new ViewHandler();
    }

    return instance;
  }

  Scene scene;
  Stage stage;

  public void start() throws IOException
  {
    // TODO
  }

 /* private void openView() throws IOException
  {
    if (scene == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(
          getClass().getResource("..fxmlpath"));
      Parent root = loader.load();

      ViewController controller = loader.getController();
      controller.init(ViewModelFactory.getInstance().getVM);

      stage.setTitle("");
      scene = new Scene(root);
    }
    stage.setScene(scene);
    stage.show();
  }*/
}
