package TEMP;

import javafx.util.Pair;
import shared.wares.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Alcohol extends Product {
    String type;
    String alkoholProcent;
    String oprindelsesLand;
    String producent;
    Date produktionsDato; //OVERVEJ HER OM DETTE ER JAVA.UTIL ELLER SQL
    Date udlobningsDato; //OVERVEJ HER OM DETTE ER JAVA.UTIL ELLER SQL
    double Salgspris;
    int antal;
    int minKobsMaengde;
    private ArrayList<String> tags = new ArrayList<>();

    public Alcohol(String name, String type, String alkoholProcent, String oprindelsesLand, String producent, Date produktionsDato, Date udlobningsDato, Double Salgspris, int antal, int minKobsMaengde) {
        super(name, new Pair<>(10, "stk"), 10, 10, new Pair<>(10d,"kg"), "I morgen");
        this.type = type;
        this.alkoholProcent = alkoholProcent;
        this.oprindelsesLand = oprindelsesLand;
        this.producent = producent;
        this.produktionsDato = produktionsDato;
        this.udlobningsDato = udlobningsDato;
        this.Salgspris = Salgspris;
        this.antal = antal;
        this.minKobsMaengde = minKobsMaengde;
        tags.add("Øko");
        tags.add("Sukkerfri");
        tags.add("Alkoholfri");
    }

    public void addTag(String tagToAdd) {
        tags.add(tagToAdd);
    }
}
