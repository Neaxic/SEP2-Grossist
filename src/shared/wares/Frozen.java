package shared.wares;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

public class Frozen extends Product implements ProductInterface
{
  public Frozen(Object[] params){
    super((String) params[1], (String) params[2], ((Date) params[6]).toLocalDate(), (Integer) params[0], 10, ((BigDecimal) params[5]).doubleValue(), (Integer) params[3]);
  }
  
  public Frozen(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase) {
    super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase);
  }
}
