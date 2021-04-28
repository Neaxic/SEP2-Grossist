package shared.wares;

import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public class CooledAndDairy extends Product implements ProductInterface {

    public CooledAndDairy(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase) {
        super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase);
    }
}
