package shared.wares;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class NewProduct implements Serializable {
	private String wareName;
	private String measurementType;
	private LocalDate bestBefore;
	private int wareNumber;
	private int amount;
	private int deliveryDays;
	private double price;
	private int minimumAmountForPurchase;

	/**
	 * Generic Product Constructor
	 *
	 * @param wareName                 The name of the Ware / Product
	 * @param measurementType          The measurement type pr unit
	 * @param bestBefore               The last date which the ware still should be able to sell on
	 * @param wareNumber               Internal PLU number
	 * @param amount                   The amount of the specific Ware / Product
	 * @param deliveryDays             Expected time for delivery in Days
	 * @param price                    Price pr unit measurementType in DKK
	 * @param minimumAmountForPurchase The lowest amount available for purchase at a time
	 */
	public NewProduct(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int amount, int deliveryDays, double price, int minimumAmountForPurchase) {
		this.wareName = wareName;
		this.measurementType = measurementType;
		this.bestBefore = bestBefore;
		this.wareNumber = wareNumber;
		this.amount = amount;
		this.deliveryDays = deliveryDays;
		this.price = price;
		this.minimumAmountForPurchase = minimumAmountForPurchase;
	}

	// Getters for all Field Variables
	public String getWareName() {
		return wareName;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public LocalDate getBestBefore() {
		return bestBefore;
	}

	public int getWareNumber() {
		return wareNumber;
	}

	public int getAmount() {
		return amount;
	}

	public int getDeliveryDays() {
		return deliveryDays;
	}

	public double getPrice() {
		return price;
	}

	public int getMinimumAmountForPurchase() {
		return minimumAmountForPurchase;
	}
}

// Interface matching the newly suggested Product Class
interface NewProductInterface {
	int getWareNumber();

	String getWareName();

	String getMeasurementType();

	LocalDate getBestBefore();

	int getAmount();

	int getDeliveryDays();

	double getPrice();

	int getMinimumAmountForPurchase();
}

// Test version of the Alcohol Class
class TestAlcohol extends NewProduct implements NewProductInterface {
	private String originCountry;
	private double percentage;     // Kommer bare til at være et tallet
	private String beverageType;   // Øl, Vin, Cider, småbarns spiritus(små sure), Spiritus (spiritus er alt over 16.4%)
	private String tags;           // Alle tags forventet på produktet

	public TestAlcohol(String name, String measurementType, LocalDate bestBefore, int wareNumber, int amount, int deliveryDays, double price, int minimumAmountForPurchase, String originCountry, double percentage, String beverageType) {
		super(name, measurementType, bestBefore, wareNumber, amount, deliveryDays, price, minimumAmountForPurchase);
		this.originCountry = originCountry;
		this.percentage = percentage;
		this.beverageType = beverageType;
		tags = "";
	}

	public void addTags(String tags) { // Kan nemt ændres til at tage en String[] eller ArrayList<String>
		tags = removeDuplicates(tags.split(","));
		tags = tags.replaceAll("[\s+\\[\\]]", "");  // Fjerner alle whitespace karakterer (mellemrum og lign) samt firkantede parenteser
		this.tags = tags;
	}

	public void addTags(String[] tags) { // Kan nemt ændres til at tage en String[] eller ArrayList<String>
		String s = removeDuplicates(tags);
		this.tags = s.replaceAll("[\s+\\[\\]]", "");
	}

	private String removeDuplicates(String[] newTags) {
		Set<String> t = new TreeSet<>(Arrays.asList(tags.split(",")));
		t.addAll(Arrays.asList(newTags));
		return t.toString();
	}
}
