package shared.wares;

import java.io.Serializable;

public abstract class NewProduct implements Serializable {
	private String name;
	private String measurementType;
	private String bb;
	private int amount;
	private int deliveryDays;
	private double price;

	public NewProduct(String name, int amount, double price, String measurementType, String bb, int deliveryDays) {
		this.name = name;
		this.measurementType = measurementType;
		this.bb = bb;
		this.amount = amount;
		this.deliveryDays = deliveryDays;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public String getBb() {
		return bb;
	}

	public int getAmount() {
		return amount;
	}

	public int getDeliveryDays() {
		return deliveryDays;
	}

	public double getPrice() {
		return price;
	}
}
