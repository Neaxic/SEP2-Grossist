package shared.wares;

import javafx.util.Pair;

import java.util.ArrayList;

public class Alcohol extends Product implements ProductInterface{
    private ArrayList<String> tags = new ArrayList<>();
    public Alcohol(Pair<Integer, String> stock, int soldDaily, int deliveryDays, Pair<Double, String> price,
                 String bb)
    {
        super("", stock, soldDaily, deliveryDays, price, bb);
        tags.add("Øko");
        tags.add("Sukkerfri");
        tags.add("Alkoholfri");
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

    public ArrayList<String> getTags() {
        return tags;
    }
}
