package client.customerclient.views.customerbrowser;

import client.customerclient.views.CustomerViewModel;

public class CustomerBrowserViewModel implements CustomerViewModel
{


	public void populate(String category) {
		switch (category.toLowerCase()) {
			case "all":
				// TODO: Implement Population
				return;
			case "colonial":
				// TODO: Implement Population
				return;
			case "diary and eggs":
				// TODO: Implement Population
				return;
			case "frozen food":
				// TODO: Implement Population
				return;
			case "fruits and vegetables":
				// TODO: Implement Population
				return;
			case "meat and seafood":
				// TODO: Implement Population
				return;
			case "beverages (non-alcohol)":
				// TODO: Implement Population
				return;
			case "beverages (alcohol)":
				// TODO: Implement Population
				return;
			default:
				throw new IllegalArgumentException("Invalid Category for Population of Items");
		}
	}

	public void loadAllProducts() {
		// Load products from Database
		populate("all");
	}

	public void addToBasket(String item, int amount){
		if (item == null || item.equals("") )
		{
			System.out.println("Item error: " +  item);
		}
		else if (amount <= 0)
		{
			System.out.println("Invalid amount");
		}

		//TODO HJÆÆÆLP, jeg kan ikke trække et helt product ud, men jeg kan få et
		// varenummer eller titel fra viewet
	}
}
