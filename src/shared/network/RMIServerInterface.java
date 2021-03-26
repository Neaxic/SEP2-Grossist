package shared.network;

import java.rmi.Remote;

public interface RMIServerInterface extends Remote {
    int getThingFromServer();
    void setThingOnServer();
}
