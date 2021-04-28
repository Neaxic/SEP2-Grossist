package server.model.databaseMediator;

import shared.wares.Alcohol;
import shared.wares.OLD_TestAlcohol;
import shared.wares.Product;

import java.util.ArrayList;
import java.util.HashMap;

public interface ModelInterface
{
  HashMap<String, ArrayList<Product>> getAllProducts();
  ArrayList<Product> getAlcoholProducts(); //TEMP FIX
}
