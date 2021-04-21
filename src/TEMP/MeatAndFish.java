package TEMP;

import java.util.ArrayList;
import java.util.Date;

public class MeatAndFish {
    String name;
    String oprindelsesLand;
    String producent;
    Date produktionsDato; //OVERVEJ HER OM DETTE ER JAVA.UTIL ELLER SQL
    Date udlobningsDato; //OVERVEJ HER OM DETTE ER JAVA.UTIL ELLER SQL
    double Salgspris;
    int antal;
    int minKobsMaengde;
    private ArrayList<String> tags = new ArrayList<>();

    public MeatAndFish(String name, String oprindelsesLand, String producent, Date produktionsDato, Date udlobningsDato, double Salgspris, int antal, int minKobsMaengde) {
        this.name = name;
        this.oprindelsesLand = oprindelsesLand;
        this.producent = producent;
        this.produktionsDato = produktionsDato;
        this.udlobningsDato = udlobningsDato;
        this.Salgspris = Salgspris;
        this.antal = antal;
        this.minKobsMaengde = minKobsMaengde;
        tags.add("Øko");
        tags.add("Halal");
        tags.add("Nøgle");
        tags.add("MSE");
    }

    public void addTag(String tagToAdd) {
        tags.add(tagToAdd);
    }
}
