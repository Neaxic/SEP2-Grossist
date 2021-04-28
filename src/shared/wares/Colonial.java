package shared.wares;

import java.time.LocalDate;

public class Colonial extends Product implements ProductInterface {
    private String originCountry;

    public Colonial(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase, String originCountry) {
        super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase);
        this.originCountry = originCountry;
    }

    public String getOriginCountry() {
        return originCountry;
    }
}
