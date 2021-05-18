package server.model.databaseMediator;

import shared.wares.Basket;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

// Lavet af hele teamet

public interface ModelInterface
{
  HashMap<String, ArrayList<Product>> getAllProducts();
  ArrayList<Product> getProduct(String schemaName, String productClass); //TEMP FIX
  void createOrder(int cvr, double sum, LocalDate date);
  void createOrderSpec(Basket basket, int CVR, LocalDate date, double sum) throws
      SQLException;
}
