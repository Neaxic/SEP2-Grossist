package shared.wares;

import java.time.LocalDate;

// Interface matching the newly suggested Product Class
public interface NewProductInterface
{
  int getWareNumber();

  String getWareName();

  String getMeasurementType();

  LocalDate getBestBefore();

  int getDeliveryDays();

  double getPrice();

  int getMinimumAmountForPurchase();
}
