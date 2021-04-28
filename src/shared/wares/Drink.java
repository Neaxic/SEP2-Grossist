package shared.wares;

import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public class Drink extends Product implements ProductInterface {
    private String beverageType;

    public Drink(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase, String beverageType) {
        super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase);
        this.beverageType = beverageType;
    }

    public String getBeverageType() {
        return beverageType;
    }
}
