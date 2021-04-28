package tests;

import javafx.util.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.wares.Basket;
import shared.wares.Drink;
import shared.wares.NewProduct;
import shared.wares.TestAlcohol;

import java.time.*;

public class basketTest {

    Basket b = new Basket();
    LocalDate date = LocalDate.of(2020, 1, 8);
    Drink d = new Drink(
            new Pair<>(300,"Cola"),
            10,
            3,
            new Pair<>(15.0,"Kr"),
            date
    );

    TestAlcohol n = new TestAlcohol("romOgCola", "Liter", date, 30409801,301,15,25,"USA",40.4,"Cider");
    TestAlcohol n1 = new TestAlcohol("Bailey", "Liter", date, 30409801,301,15,25,"USA",40.4,"Cider");
    TestAlcohol n2 = new TestAlcohol("Bajer", "Liter", date, 30409801,301,15,25,"USA",40.4,"Cider");

    @BeforeEach
    void setUp() {
        b = new Basket();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addProduct() {
        b.addProduct(n,200);
        b.addProduct(n1,500);
        b.addProduct(n2,1000);

        System.out.println( b.getBasket().get(n));
        System.out.println( b.getBasket().get(n1));
        System.out.println( b.getBasket().get(n2));

    }
}
