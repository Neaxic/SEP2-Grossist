package client.core.factories;

import client.customerclient.model.CustomerModelInterface;
import client.customerclient.model.CustomerModel;
import client.grosserclient.model.GrosserModel;
import client.grosserclient.model.GrosserModelInterface;

// Andreas Østergaard, Frederik Bergmann

public class ModelFactory {
	private static ModelFactory instance;
	private CustomerModelInterface customerModelInterface;
	private GrosserModelInterface grosserModelInterface;

	private ModelFactory() {
	}

	public void reset() {
		instance = null;
	}

	public static ModelFactory getInstance() {
		if (instance == null) {
			instance = new ModelFactory();
		}
		return instance;
	}

	public CustomerModelInterface getCustomerModel() {
		if (customerModelInterface == null) {
			customerModelInterface = new CustomerModel(
					ClientFactory.getInstance().getClient());
		}
		return customerModelInterface;
	}

  public GrosserModelInterface getGrosserModel()
  {
    if (grosserModelInterface == null)
    {
      grosserModelInterface = new GrosserModel(
          ClientFactory.getInstance().getGrosserClient());
    }
    return grosserModelInterface;
  }
}
