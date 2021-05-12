package shared.network;

import java.beans.PropertyChangeListener;

// Andreas Young

public interface Subject extends PropertyChangeListener {
	void addListener(PropertyChangeListener listener);
	void removeListener(PropertyChangeListener listener);
}
