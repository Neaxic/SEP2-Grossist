package shared.wares;

import javafx.util.Pair;

public class FruitsAndVegetable extends Product implements ProductInterface {

    public FruitsAndVegetable(Pair<Integer, String> stock, int soldDaily, int deliveryDays, Pair<Double, String> price,
                  String bb)
    {
        super("", stock, soldDaily, deliveryDays, price, bb);
    }

    @Override public Pair<Integer, String> getStock()
    {
        return null;
    }

    @Override public int getDeliveryDays()
    {
        return 0;
    }

    @Override public int getSoldDaily()
    {
        return 0;
    }

    @Override public Pair<Double, String> getPrice()
    {
        return null;
    }
}
