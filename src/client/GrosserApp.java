package client;

import client.core.factories.ClientFactory;
import client.core.viewhandlers.ProxyViewHandler;
import client.network.Client;
import javafx.application.Application;
import javafx.stage.Stage;

//Frederik Bergmann og Andreas Østergaard, Andreas Young.

public class GrosserApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    stage.setOnCloseRequest(e -> System.exit(0));
    Client client = ClientFactory.getInstance().getClient();
    client.start();
    new ProxyViewHandler(stage);
  }
}
