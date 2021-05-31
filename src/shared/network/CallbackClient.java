package shared.network;

import javafx.util.Pair;
import server.model.RISK_ASSESSMENT.RiskReport;
import shared.objects.Order;
import shared.wares.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Andreas Young

public interface CallbackClient extends Remote {
	/**
	 * Sends an Update to the Client
	 * @param info Information about what kind of Update is being made, actions should be handled by the CallbackClient
	 * @param list List containing Updated Information
	 * @throws RemoteException
	 */
	void update(String info, List list) throws RemoteException;

	void updateAllOrders(List<Order> orders) throws RemoteException;

	void updateRiskData(ArrayList<RiskReport> reports) throws RemoteException;
}
