package shared.wares;

import javafx.util.Pair;

import java.util.ArrayList;

public class Alcohol extends Product implements ProductInterface {


    public Alcohol(Pair<Integer, String> stock, int soldDaily, int deliveryDays, Pair<Double, String> price,
                   String bb) {
        super("", stock, soldDaily, deliveryDays, price, bb);
        super.addTag("Øko");
        super.addTag("Sukkerfri");
        super.addTag("Alkoholfri");
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
    public String getBb() { return super.getBb(); }

    @Override
    public ArrayList<String> getTags() {
        return super.getTags();
    }
}
