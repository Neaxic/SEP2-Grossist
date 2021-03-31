package shared.network;

import java.rmi.Remote;

public interface RMIServerInterface extends Remote {

    /**
     * @return Den ene værdi der er gemt på serveren
     */
    int getThingFromServer();

    /**
     * @param val Den værdi du vil sætte på serveren
     */
    void setThingOnServer(int val);


    /**
     * @return Hvis der kan connectes, så vil værdien være True
     */
    boolean ping();
}
