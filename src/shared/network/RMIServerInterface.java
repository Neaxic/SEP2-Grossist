package shared.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {

    /**
     * @return Værdien gemt på Server
     */
    int getThingFromServer() throws RemoteException;

    /**
     * @param val Gemmes på Server
     */
    void setThingOnServer(int val) throws RemoteException;


    /**
     * @return True hvis der er forbindelse
     */
    boolean ping() throws RemoteException;
}
