package shared.wares;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

public class CooledAndDairy extends Product implements ProductInterface {

	public CooledAndDairy(Object[] params) {
		super((String) params[1], (String) params[2], ((Date) params[6]).toLocalDate(), (Integer) params[0], 10, ((BigDecimal) params[5]).doubleValue(), (Integer) params[3], (String) params[4]);
	}

	public CooledAndDairy(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase, String producedBy) {
		super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase, producedBy);
	}

	@Override
	public String sqlTemplate() {
		return super.sqlTemplate();
	}

	@Override
	public String sqlInformation() {
		return super.sqlInformation();
	}

}
