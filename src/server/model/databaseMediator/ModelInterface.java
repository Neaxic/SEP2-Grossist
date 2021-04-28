package server.model.databaseMediator;

import shared.wares.NewProduct;
import shared.wares.TestAlcohol;

import java.util.ArrayList;

public interface ModelInterface
{
  ArrayList<ArrayList> getAllProducts();
  ArrayList<TestAlcohol> getAlcoholProducts();
}
