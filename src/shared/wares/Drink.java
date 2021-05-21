package shared.wares;


import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

public class Drink extends Product implements ProductInterface {
	private String beverageType;

	public Drink(Object[] params) {
		super((String) params[1], (String) params[2], ((Date) params[5]).toLocalDate(), (Integer) params[0], 10, ((BigDecimal) params[4]).doubleValue(), (String) params[3], (String) params[7]);
		beverageType = (String) params[8];
	}

	public Drink(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, String producedBy, String tags, String beverageType) {
		super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, producedBy, tags);
		this.beverageType = beverageType;
	}

	public String getBeverageType() {
		return beverageType;
	}

	@Override
	public String sqlTemplate() {
		return super.sqlTemplate() + ", type";
	}

	@Override
	public String sqlInformation() {
		return super.sqlInformation() + ", '" + beverageType + "'";
	}

}
