package client.customerclient.views;

import client.core.ViewHandler;

import java.io.IOException;

// Andreas Young, Line Guld

public interface CustomerViewController {
  void init(ViewHandler viewHandler);
  void swapScene(String sceneName) throws IOException;
}