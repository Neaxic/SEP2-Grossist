package client;

import client.core.CustomerViewHandler;
import client.core.ProxyViewHandler;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class GrosserApp extends Application
{
  @Override
  public void start(Stage stage) throws Exception {
    ClientFactory clientFactory = new ClientFactory();
    ModelFactory modelFactory = new ModelFactory(clientFactory);
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);

    ViewHandler viewHandler = new ProxyViewHandler(viewModelFactory);
    viewHandler.start(stage);
  }

}
