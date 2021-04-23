package shared.wares;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Frozen extends Product implements ProductInterface
{

  public Frozen(Pair<Integer, String> stock, int soldDaily, int deliveryDays, Pair<Double, String> price,
      LocalDate bb)
  {
    super("", stock, soldDaily, deliveryDays, price, bb);
    super.addTag("Laktosefri");
    super.addTag("Øko");
    super.addTag("Vegansk");
    super.addTag("Vegetar");
    super.addTag("Fedtfattig");
    super.addTag("Nøgle");
    super.addTag("Halal");
    super.addTag("MSE");
    super.addTag("Sukkerfri");
  }

  @Override
  public Pair<Integer, String> getStock() {
    return super.getStock();
  }

  @Override
  public int getSoldDaily() {
    return super.getSoldDaily();
  }

  @Override
  public int getDeliveryDays() {
    return super.getDeliveryDays();
  }

  @Override
  public Pair<Double, String> getPrice() {
    return super.getPrice();
  }

  @Override
  public LocalDate getBb() { return super.getBb(); }

  @Override
  public ArrayList<String> getTags() {
    return super.getTags();
  }
}
