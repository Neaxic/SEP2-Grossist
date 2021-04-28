package shared.wares;

import javafx.util.Pair;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class OLD_Product implements Serializable {
	private String name;
	private Pair<Integer, String> stock;
	private Pair<Double, String> price;
	private int soldDaily, deliveryDays;
	private LocalDate bb;
	private ArrayList<String> tags = new ArrayList<>();
	/**
	 * Constructor for a generic product item
	 *
	 * @param stock        Amount and measurement type ex. <500, kg>
	 * @param soldDaily    Amount of product sold on average each day
	 * @param deliveryDays Expected time before product will be ready and delivered
	 * @param price        Price of product and of what measurement type
	 * @param bb           Best Before Date of Product
	 */
	public OLD_Product(String name, Pair<Integer, String> stock, int soldDaily, int deliveryDays, Pair<Double, String> price, LocalDate bb) {
		this.name = name;
		this.stock = stock;
		this.soldDaily = soldDaily;
		this.deliveryDays = deliveryDays;
		this.price = price;
		this.bb = bb;
	}

	public String getName() {
		return name;
	}

	public Pair<Double, String> getPrice() {
		return price;
	}

	public Pair<Integer, String> getStock() {
		return stock;
	}

	public int getSoldDaily() {
		return soldDaily;
	}

	public int getDeliveryDays() {
		return deliveryDays;
	}

	public LocalDate getBb() {
		return bb;
	}

	public void addTag(String s) {
		tags.add(s);
	}

	public ArrayList<String> getTags() {
		return tags;
	}
}
