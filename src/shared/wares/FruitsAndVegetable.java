package shared.wares;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class FruitsAndVegetable extends Product implements ProductInterface {
private String originCountry;

    public FruitsAndVegetable(Object[] params){
        super((String) params[1], (String) params[2], ((Date) params[6]).toLocalDate(), (Integer) params[0], 10, ((BigDecimal) params[5]).doubleValue(), (Integer) params[3]);
        originCountry = (String) params[9];
    }

    public FruitsAndVegetable(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase, String originCountry) {
        super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase);
        this.originCountry = originCountry;
    }

    public String getOriginCountry() {
        return originCountry;
    }
}
