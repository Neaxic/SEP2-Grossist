package server.model;

import server.model.databaseMediator.BaseDAO;
import server.model.databaseMediator.DAOModel;
import server.model.databaseMediator.ModelInterface;
import shared.wares.Alcohol;
import shared.wares.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataModelImpl {
	private BaseDAO base = new BaseDAO();
	private ModelInterface model = new DAOModel();


	public DataModelImpl() throws SQLException {
	}

	public HashMap<String, ArrayList<Product>> getAllProducts() {
	  return model.getAllProducts();
	}

	public ArrayList<Product> getAlcohol() {
	  return model.getAlcoholProducts();
	}
}
