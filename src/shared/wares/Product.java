package shared.wares;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

// Andreas Young, Kasper Falk, Andreas Østergaard, Frederik Bergmann

public abstract class Product implements Serializable {
	private String wareName;
	private String measurementType;
	private LocalDate bestBefore;
	private int wareNumber;
	private int deliveryDays;
	private double price;
	private String producedBy;
	String tags;

	/**
	 * Generic Product Constructor
	 *
	 * @param wareName        The name of the Ware / Product
	 * @param measurementType The measurement type pr unit
	 * @param bestBefore      The last date which the ware still should be able to sell on
	 * @param wareNumber      Internal PLU number
	 * @param deliveryDays    Expected time for delivery in Days
	 * @param price           Price pr unit measurementType in DKK
	 * @param producedBy      The company which produced the product
	 * @param tags            Tags concerning the product. Should be a String with each tag seperated by a comma
	 */
	public Product(String wareName, String measurementType, LocalDate bestBefore, int wareNumber, int deliveryDays, double price, String producedBy, String tags) {
		this.wareName = wareName;
		this.measurementType = measurementType;
		this.bestBefore = bestBefore;
		this.wareNumber = wareNumber;
		this.deliveryDays = deliveryDays;
		this.price = price;
		this.producedBy = producedBy;
		this.tags = "";
		addTags(tags);
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

	public int getDeliveryDays() {
		return deliveryDays;
	}

	public double getPrice() {
		return price;
	}

	public String getProducedBy() {
		return producedBy;
	}

	public String getTags() {
		return tags;
	}

	public void addTags(String tags) { // Kan nemt ændres til at tage en String[] eller ArrayList<String>
		if (!tags.isBlank()) {
			tags = removeDuplicates(tags.split(","));
			tags = tags.replaceAll("[\s+\\[\\]]",
					"");  // Fjerner alle whitespace karakterer (mellemrum og lign) samt firkantede parenteser
			this.tags = tags;
		}
	}

	public void addTags(String[] tags) { // Kan nemt ændres til at tage en String[] eller ArrayList<String>
		if (!(tags.length == 0)) {
			String s = removeDuplicates(tags);
			this.tags = s.replaceAll("[\s+\\[\\]]", "");
		}
	}

	private String removeDuplicates(String[] newTags) {
		Set<String> t = new TreeSet<>(Arrays.asList(tags.split(",")));
		t.addAll(Arrays.asList(newTags));
		return t.toString();
	}

	@Override
	public String toString() {
		return "(" + wareNumber + ") " + wareName;
	}

	/**
	 * Creates an INSERT query into the Class' mapped Table and returns an open ended query for addition information if needed
	 *
	 * @return INSERT INTO query open ended for SQL Column Names
	 */
	public String sqlTemplate() {
		return "productName, measurement, producedBy, salesprice, bbDate, tags";
	}

	/**
	 * Creates the VALUES part of an INSERT query and returns an open ended query for addition information if needed
	 *
	 * @return Information regarding product, open ended for SQL Column Information
	 */
	public String sqlInformation() { // Produced By
		return " '" + wareName + "', '" + measurementType + "', '" + producedBy + "', " + price + ", '" + bestBefore + "', '" + tags + "'";
	}
}

