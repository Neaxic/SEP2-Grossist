package client.core;

import javafx.stage.Stage;

public class ProxyViewHandler implements ViewHandler {
	private ViewHandler viewHandler;

	private GrosserViewHandler grosserViewHandler;
	private CustomerViewHandler customerViewHandler;

	public ProxyViewHandler(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
	}

	@Override
	public void start(Stage primaryStage) {
		viewHandler.start(primaryStage);
	}

	@Override
	public void openView(String viewToOpen) {
		//if(grosserApp.client???){
	//	grosserViewHandler.start();
		//} else {
		// custommerViewHandler.start();
		// }
		viewHandler.openView(viewToOpen);
	}

	@Override
	public ViewModelFactory getViewModelByViewName(String viewName) {
		return viewHandler.getViewModelByViewName(viewName);
	}
}
