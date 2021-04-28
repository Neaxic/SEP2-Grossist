package shared.wares;

import javafx.util.Pair;

import java.time.LocalDate;

public interface OLD_ProductInterface
{
  Pair<Integer, String> getStock();
  int getDeliveryDays();
  int getSoldDaily();
  Pair<Double, String> getPrice();
}
