package client;

import client.core.*;
import client.network.Client;
import javafx.application.Application;
import javafx.stage.Stage;


public class GrosserApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    // TODO: Mangler pil mellem modelFactory og clientFactory på Astah
    Client client = ClientFactory.getInstance().getClient();
    client.start();
    ProxyViewHandler proxy = new ProxyViewHandler(stage);
  }
}
