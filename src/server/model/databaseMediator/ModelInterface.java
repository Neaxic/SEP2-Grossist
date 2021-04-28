package server.model.databaseMediator;

import shared.wares.Alcohol;
import shared.wares.OLD_TestAlcohol;

import java.util.ArrayList;
import java.util.HashMap;

public interface ModelInterface
{
  HashMap<String, ArrayList> getAllProducts();
  ArrayList<Alcohol> getAlcoholProducts();
}
