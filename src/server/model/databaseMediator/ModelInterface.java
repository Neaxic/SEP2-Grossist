package server.model.databaseMediator;

import shared.wares.NewProduct;
import shared.wares.TestAlcohol;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public interface ModelInterface
{
  HashMap<String, ArrayList> getAllProducts();
  ArrayList<TestAlcohol> getAlcoholProducts();
}
