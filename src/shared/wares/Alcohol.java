package shared.wares;

import java.time.LocalDate;

public class Alcohol extends Product implements ProductInterface {
    private String originCountry;
    private double percentage;     // Kommer bare til at være et tallet
    private String beverageType;   // Øl, Vin, Cider, småbarns spiritus(små sure), Spiritus (spiritus er alt over 16.4%)


    public Alcohol(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, int minimumAmountForPurchase, String originCountry, double percentage, String beverageType) {
        super(wareName, measurementType, bestBefore, wareNumber, deliveryDays, price, minimumAmountForPurchase);
        this.originCountry = originCountry;
        this.percentage = percentage;
        this.beverageType = beverageType;
        tags = "";
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getBeverageType() {
        return beverageType;
    }

}
