package client.CustomerClient.Views;

import client.core.ViewHandler;

public interface CustomerViewController
{
  void init(ViewHandler viewHandler, ViewModel viewModel);
  void swapScene(String sceneName);
}
