package server.model.databaseMediator;

import shared.wares.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public interface ModelInterface
{
  HashMap<String, ArrayList<Product>> getAllProducts();
  ArrayList<Product> getProduct(String schemaName, String productClass); //TEMP FIX
  void createOrder(int cvr, double sum, LocalDate date);
}
