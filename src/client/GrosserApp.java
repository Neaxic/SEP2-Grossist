package client;

import client.core.factories.ClientFactory;
import client.core.viewhandlers.ProxyViewHandler;
import client.network.Client;
import javafx.application.Application;
import javafx.stage.Stage;

//Frederik Bergmann og Andreas Østergaard (Young er nu her fordi han fik systemet til at stoppe når vinduerne lukkes)

public class GrosserApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    stage.setOnCloseRequest(e -> System.exit(0));
    Client client = ClientFactory.getInstance().getClient();
    client.start();
    ProxyViewHandler proxy = new ProxyViewHandler(stage);
  }
}
