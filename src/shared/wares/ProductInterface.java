package shared.wares;

import java.time.LocalDate;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

// Interface matching the newly suggested Product Class
public interface ProductInterface
{
  int getWareNumber();

  String getWareName();

  String getMeasurementType();

  LocalDate getBestBefore();

  int getDeliveryDays();

  double getPrice();

  int getMinimumAmountForPurchase();
}
