package shared.wares;

import java.time.LocalDate;

public class Alcohol extends Product implements ProductInterface {
	private String originCountry;
	private double percentage;     // Kommer bare til at være et tallet
	private String beverageType;   // Øl, Vin, Cider, småbarns spiritus(små sure), Spiritus (spiritus er alt over 16.4%)

	// TODO: Opdater med params når database opdateres, eller anden aftale er lavet
	public Alcohol(Object[] params) {
		super((String) params[1], (String) params[2], (LocalDate) params[6], (int) params[0], 10, (double) params[5], (int) params[3]);
		originCountry = (String) params[10];
		percentage = (double) params[9];
		beverageType = (String) params[11];
	}

	public Alcohol(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase, String originCountry, double percentage, String beverageType) {
		super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase);
		this.originCountry = originCountry;
		this.percentage = percentage;
		this.beverageType = beverageType;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public double getPercentage() {
		return percentage;
	}

	public String getBeverageType() {
		return beverageType;
	}

}
