package client.grosserclient.views;

import client.core.ViewHandler;

import java.io.IOException;

public interface GrosserViewController
{
  void init(ViewHandler viewHandler);
  void swapScene(String sceneName) throws IOException;
}
