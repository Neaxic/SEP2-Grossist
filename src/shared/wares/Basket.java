package shared.wares;

import java.io.Serializable;
import java.util.HashMap;

// Andreas Young, Frederik Bergmann

public class Basket implements Serializable {
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

	public void removeProductWithSameWareNum(int wareNum){
		for(Product i: basket.keySet()){
			if(i.getWareNumber() == wareNum){
				this.removeProduct(i);
			}
		}
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
