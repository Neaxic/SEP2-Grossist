package client.core;

import client.CustomerClient.Views.ViewModel;

public interface ViewHandler {
	void start();
	void openView(String viewToOpen);

	ViewModel getViewModelByViewName(String viewName);
}
