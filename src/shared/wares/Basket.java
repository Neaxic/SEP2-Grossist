package shared.wares;

import java.util.HashMap;

public class Basket {
	private HashMap<NewProduct, Integer> basket;

	public Basket() {
		basket = new HashMap<>();
	}

	public void addProduct(NewProduct product, int amount) {
		basket.put(product, amount);
	}

	/**
	 * Removes the Product from the HashMap if possible
	 * @param product This is the desired product to be removed
	 */
	public void removeProduct(NewProduct product) {
		basket.remove(product);
	}

	public void changeAmount(NewProduct product, int newAmount) {
		basket.replace(product, newAmount);
	}

	public int getAmount(NewProduct product) {
		return basket.get(product);
	}
}
