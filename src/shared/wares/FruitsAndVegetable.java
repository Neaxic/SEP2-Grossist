package shared.wares;

import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public class FruitsAndVegetable extends Product implements ProductInterface {
private String originCountry;

    public FruitsAndVegetable(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase, String originCountry) {
        super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase);
        this.originCountry = originCountry;
    }

    public String getOriginCountry() {
        return originCountry;
    }
}
