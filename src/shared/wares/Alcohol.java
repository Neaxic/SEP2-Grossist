package shared.wares;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

public class Alcohol extends Product{
	private final String originCountry;
	private final double percentage;
	private final String beverageType;

	public Alcohol(Object[] params) {
		super((String) params[1], (String) params[2], ((Date) params[5]).toLocalDate(), (Integer) params[0], (Integer)params[8], ((BigDecimal) params[4]).doubleValue(), (String) params[3], (String) params[7]);
		originCountry = (String) params[11];
		percentage = ((BigDecimal) params[10]).doubleValue();
		beverageType = (String) params[12];
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
