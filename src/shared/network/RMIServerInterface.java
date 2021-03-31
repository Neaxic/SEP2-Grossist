package shared.network;

import java.rmi.Remote;

public interface RMIServerInterface extends Remote {

    /**
     * @return Værdien gemt på Server
     */
    int getThingFromServer();

    /**
     * @param val Gemmes på Server
     */
    void setThingOnServer(int val);


    /**
     * @return True hvis der er forbindelse
     */
    boolean ping();
}
