package shared.wares;

import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public class MeatAndFish extends Product implements ProductInterface{

    public MeatAndFish(Pair<Integer, String> stock, int soldDaily, int deliveryDays, Pair<Double, String> price,
                          LocalDate bb)
    {
        super("", stock, soldDaily, deliveryDays, price, bb);
        super.addTag("Øko");
        super.addTag("Halal");
        super.addTag("Nøgle");
        super.addTag("MSE");
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