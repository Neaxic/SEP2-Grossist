import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.wares.Alcohol;
import shared.objects.Basket;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// Frederik Bergmann og Kasper Falk

public class basketTest
{

  Basket b;
  Alcohol n, n1, n2;
  LocalDate date = LocalDate.of(2020, 1, 8);

  @BeforeEach void setUp()
  {
    b = new Basket();
    n = new Alcohol("romOgCola", "Liter", date, 30409801, 301, 15, 25,"Rexam",
        "USA", 40.4, "Cider");
    n1 = new Alcohol("Bailey", "Liter", date, 30409801, 301, 15, 25,"Rexam",
        "USA", 40.4, "Cider");
    n2 = new Alcohol("Bajer", "Liter", date, 30409801, 301, 15, 25,"Rexam",
        "USA", 40.4, "Cider");

  }

  @AfterEach void tearDown()
  {

  }

  @Test void addProduct()
  {
    b.addProduct(n, 200);
    b.addProduct(n1, 500);
    b.addProduct(n2, 1000);

    assertEquals(200, b.getAmount(n));
    assertEquals(500, b.getAmount(n1));
    assertEquals(1000, b.getAmount(n2));
    assertNotEquals(500, b.getAmount(n));
  }

  @Test void basketSize()
  {
    assertTrue(b.getBasket().isEmpty());

    b.addProduct(n, 200);
    b.addProduct(n1, 500);
    b.addProduct(n2, 1000);

    assertEquals(3, b.getBasket().size());

    b.removeProduct(n1);

    assertNotEquals(3, b.getBasket().size());
  }

  @Test void productName()
  {
    b.addProduct(n2, 1000);

    assertTrue(b.getBasket().containsKey(n2));
  }
}
