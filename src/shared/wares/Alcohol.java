package shared.wares;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

public class Alcohol extends Product implements ProductInterface {
	private String originCountry;
	private double percentage;     // Kommer bare til at være et tallet
	private String beverageType;   // Øl, Vin, Cider, småbarns spiritus(små sure), Spiritus (spiritus er alt over 16.4%)

	// TODO: Opdater med params når database opdateres, eller anden aftale er lavet
	public Alcohol(Object[] params) {
		super((String) params[1], (String) params[2], ((Date) params[6]).toLocalDate(), (Integer) params[0], 10, ((BigDecimal) params[5]).doubleValue(), (Integer) params[3], (String) params[4]);
		originCountry = (String) params[10];
		percentage = ((BigDecimal) params[9]).doubleValue();
		beverageType = (String) params[11];
	}

	public Alcohol(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase, String producedBy, String originCountry, double percentage, String beverageType) {
		super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase, producedBy);
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

	@Override
	public String sqlTemplate() {
		return super.sqlTemplate() + ", alcoholPercentage, productionCountry, type";
	}

	@Override
	public String sqlInformation() {
		return super.sqlInformation() + ", " + percentage + ", " + originCountry + ", " + beverageType;
	}

}
