package client.core;

public interface ViewHandler {
	void start();
	void openView(String viewToOpen);

	ViewModelFactory getViewModelByViewName(String viewName);
}
