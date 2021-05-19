package client;

import client.core.factories.ClientFactory;
import client.core.viewhandlers.ProxyViewHandler;
import client.network.Client;
import javafx.application.Application;
import javafx.stage.Stage;

//Frederik Bergmann og Andreas Østergaard

public class GrosserApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    Client client = ClientFactory.getInstance().getClient();
    client.start();
    ProxyViewHandler proxy = new ProxyViewHandler(stage);
  }
}
