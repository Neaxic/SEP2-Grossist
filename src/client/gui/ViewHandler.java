package client.gui;

public interface ViewHandler {
	void start();
	void openView(String viewToOpen);

	ViewModelFactory getViewModelByViewName(String viewName);
}
