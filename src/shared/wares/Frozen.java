package shared.wares;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

public class Frozen extends Product implements ProductInterface {
	public Frozen(Object[] params) {
		super((String) params[1], (String) params[2], ((Date) params[5]).toLocalDate(), (Integer) params[0], (Integer)params[8], ((BigDecimal) params[4]).doubleValue(), (String) params[3], (String) params[7]);
	}

	public Frozen(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, String producedBy, String tags) {
		super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, producedBy, tags);
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
