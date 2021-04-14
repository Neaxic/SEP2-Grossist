package client.core;

public class ProxyViewHandler implements ViewHandler {
	private ViewHandler viewHandler;



	public ProxyViewHandler(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
	}

	@Override
	public void start() {
		viewHandler.start();
	}

	@Override
	public void openView(String viewToOpen) {
		viewHandler.openView(viewToOpen);
	}

	@Override
	public ViewModelFactory getViewModelByViewName(String viewName) {
		return viewHandler.getViewModelByViewName(viewName);
	}
}
