package shared.wares;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Drink extends Product implements ProductInterface {
	private String beverageType;

	public Drink(Object[] params) {
		super((String) params[1], (String) params[2], ((Date) params[6]).toLocalDate(), (Integer) params[0], 10, ((BigDecimal) params[5]).doubleValue(), (Integer) params[3]);
		beverageType = (String) params[9];
	}

	public Drink(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase, String beverageType) {
		super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase);
		this.beverageType = beverageType;
	}

	public String getBeverageType() {
		return beverageType;
	}
}
