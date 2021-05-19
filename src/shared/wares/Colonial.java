package shared.wares;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

public class Colonial extends Product implements ProductInterface {
	private String originCountry;

	public Colonial(Object[] params) {
		super((String) params[1], (String) params[2], ((Date) params[6]).toLocalDate(), (Integer) params[0], 10, ((BigDecimal) params[5]).doubleValue(), (Integer) params[3], (String) params[4]);
		originCountry = (String) params[9];
	}

	public Colonial(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase, String originCountry, String  producedBy) {
		super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase, producedBy);
		this.originCountry = originCountry;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	@Override
	public String sqlTemplate() {
return super.sqlTemplate() + ", productionCountry";
	}

	@Override
	public String sqlInformation() {
        return super.sqlInformation() + "," + originCountry;
	}

}
