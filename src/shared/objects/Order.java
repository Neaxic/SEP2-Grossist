package shared.objects;

import java.time.LocalDateTime;

public class Order {
	private final long cvr;
	private final int orderNumber;
	private final LocalDateTime orderTime;
	private final double priceSum;

	public Order(long cvr, int orderNumber, LocalDateTime orderTime, double priceSum) {
		this.cvr = cvr;
		this.orderNumber = orderNumber;
		this.orderTime = orderTime;
		this.priceSum = priceSum;
	}

	public long getCVR() {
		return cvr;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public double getPriceSum() {
		return priceSum;
	}
}
