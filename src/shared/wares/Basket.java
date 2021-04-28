package shared.wares;

import java.util.HashMap;

public class Basket {
	private HashMap<Product, Integer> basket;

	public Basket() {
		basket = new HashMap<>();
	}

	public void addProduct(Product product, int amount) {
		basket.put(product, amount);
	}

	/**
	 * Removes the Product from the HashMap if possible
	 * @param product This is the desired product to be removed
	 */
	public void removeProduct(Product product) {
		basket.remove(product);
	}

	public void changeAmount(Product product, int newAmount) {
		basket.replace(product, newAmount);
	}

	public int getAmount(Product product) {
		return basket.get(product);
	}

	public HashMap<Product, Integer> getBasket() {
		return basket;
	}
}
