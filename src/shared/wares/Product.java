package shared.wares;

import javafx.util.Pair;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Product implements Serializable {
	private Pair<Integer, String> stock;
	private Pair<Double, String> price;
	private int soldDaily, deliveryDays;
	private LocalDate bb;

	/**
	 * Constructor for a generic product item
	 *
	 * @param stock        Amount and measurement type ex. <500, kg>
	 * @param soldDaily    Amount of product sold on average each day
	 * @param deliveryDays Expected time before product will be ready and delivered
	 * @param price        Price of product and of what measurement type
	 * @param bb           Best Before Date of Product
	 */
	public Product(Pair<Integer, String> stock, int soldDaily, int deliveryDays, Pair<Double, String> price, LocalDate bb) {
		this.stock = stock;
		this.soldDaily = soldDaily;
		this.deliveryDays = deliveryDays;
		this.price = price;
		this.bb = bb;
	}
}
