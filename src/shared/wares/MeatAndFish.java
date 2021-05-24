package shared.wares;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

public class MeatAndFish extends Product implements ProductInterface {
	private String originCountry;

	public MeatAndFish(Object[] params) {
		super((String) params[1], (String) params[2], ((Date) params[5]).toLocalDate(), (Integer) params[0], 10, ((BigDecimal) params[4]).doubleValue(), (String) params[3], (String) params[7]);
		originCountry = (String) params[8];
	}

	public MeatAndFish(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, String producedBy, String tags, String originCountry) {
		super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, producedBy, tags);
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
		return super.sqlInformation() + ", '" + originCountry + "'";
	}
}
