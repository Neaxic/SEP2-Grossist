package client;

import client.core.*;
import javafx.application.Application;
import javafx.stage.Stage;


public class GrosserApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    ClientFactory clientFactory = new ClientFactory();
    // TODO: Mangler pil mellem modelFactory og clientFactory på Astah
    ModelFactory modelFactory = new ModelFactory(clientFactory.getClient());
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ProxyViewHandler viewHandler = new ProxyViewHandler(stage, viewModelFactory);

    viewHandler.login();
  }
}
