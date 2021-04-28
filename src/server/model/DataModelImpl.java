package server.model;

import server.model.databaseMediator.BaseDAO;
import server.model.databaseMediator.DAOModel;
import server.model.databaseMediator.ModelInterface;
import shared.wares.TestAlcohol;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataModelImpl
{
  private BaseDAO base = new BaseDAO();
  private ModelInterface model = new DAOModel();


  public DataModelImpl() throws SQLException
  {
  }

  public ArrayList<TestAlcohol> getAlcohol(){
    return model.getAlcoholProducts();
  }
}
