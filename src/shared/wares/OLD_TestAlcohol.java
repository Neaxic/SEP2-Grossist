package shared.wares;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

// Andreas Young

// Test version of the Alcohol Class
public class OLD_TestAlcohol extends Product implements ProductInterface
{
  private String originCountry;
  private double percentage;     // Kommer bare til at være et tallet
  private String beverageType;   // Øl, Vin, Cider, småbarns spiritus(små sure), Spiritus (spiritus er alt over 16.4%)
  private String tags;           // Flyttes til product

  public OLD_TestAlcohol(String name, String measurementType, LocalDate bestBefore,
                         int wareNumber, int deliveryDays, double price,
                         int minimumAmountForPurchase, String originCountry, double percentage,
                         String beverageType)
  {
    super(name, measurementType, bestBefore, wareNumber, deliveryDays, price,
        minimumAmountForPurchase);
    this.originCountry = originCountry;
    this.percentage = percentage;
    this.beverageType = beverageType;
    tags = "";
  }

  public void addTags(String tags)
  { // Kan nemt ændres til at tage en String[] eller ArrayList<String>
    tags = removeDuplicates(tags.split(","));
    tags = tags.replaceAll("[\s+\\[\\]]",
        "");  // Fjerner alle whitespace karakterer (mellemrum og lign) samt firkantede parenteser
    this.tags = tags;
  }

  public void addTags(String[] tags)
  { // Kan nemt ændres til at tage en String[] eller ArrayList<String>
    String s = removeDuplicates(tags);
    this.tags = s.replaceAll("[\s+\\[\\]]", "");
  }

  private String removeDuplicates(String[] newTags)
  {
    Set<String> t = new TreeSet<>(Arrays.asList(tags.split(",")));
    t.addAll(Arrays.asList(newTags));
    return t.toString();
  }
}
