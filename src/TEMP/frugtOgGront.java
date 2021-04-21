package TEMP;

import java.util.ArrayList;
import java.util.Date;

public class frugtOgGront {
    String name;
    String oprindelsesLand;
    int klasseNo;
    Date produktionsDato; //OVERVEJ HER OM DETTE ER JAVA.UTIL ELLER SQL
    String sort;
    double Salgspris;
    int antal;
    int minKobsMaengde;
    private ArrayList<String> tags = new ArrayList<>();


    public frugtOgGront(String name,String oprindelsesLand,int klasseNo, Date produktionsDato,String sort,double Salgspris,int antal,int minKobsMaengde) {
        this.name = name;
        this.oprindelsesLand = oprindelsesLand;
        this.klasseNo = klasseNo;
        this.produktionsDato = produktionsDato;
        this.sort = sort;
        this.Salgspris = Salgspris;
        this.antal = antal;
        this.minKobsMaengde = minKobsMaengde;
        tags.add("Øko");
        tags.add("Nøgle");
        tags.add("GMO");
    }

    public void addTag(String tagToAdd) {
        tags.add(tagToAdd);
    }
}
