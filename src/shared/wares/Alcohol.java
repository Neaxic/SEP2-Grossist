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
		super((String) params[1], (String) params[2], ((Date) params[5]).toLocalDate(), (Integer) params[0], 10, ((BigDecimal) params[4]).doubleValue(), (String) params[3], (String) params[7]);
		originCountry = (String) params[9];
		percentage = ((BigDecimal) params[8]).doubleValue();
		beverageType = (String) params[10];
	}

	public Alcohol(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, String producedBy, String tags, String originCountry, double percentage, String beverageType) {
		super(wareName,
				measurementType,
				bestBefore,
				wareNumber,
				deliveryDays,
				price,
				producedBy,
				tags);
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
		return super.sqlInformation() + ", " + percentage + ", '" + originCountry + "', '" + beverageType + "'";
	}

}
