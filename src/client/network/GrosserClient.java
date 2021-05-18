package client.network;

// Andreas Østergaard

import shared.wares.Product;

import java.beans.PropertyChangeListener;

public interface GrosserClient
{
	public void createProduct(Product newProduct);
	public void getAllOrders();
	public void addListener(PropertyChangeListener listener);
}


//TODO: tilføj alle methoder til interfacet client