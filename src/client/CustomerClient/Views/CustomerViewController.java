package client.CustomerClient.Views;

import client.core.ViewHandler;

public interface CustomerViewController
{
  void init(ViewHandler viewHandler, CustomerViewModel customerViewModel);
  void swapScene(String sceneName);
}
