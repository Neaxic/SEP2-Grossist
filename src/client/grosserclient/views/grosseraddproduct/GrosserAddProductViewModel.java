package client.grosserclient.views.grosseraddproduct;

import client.core.factories.ModelFactory;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.GrosserViewModel;
import javafx.util.Pair;
import shared.wares.Product;

import java.sql.SQLException;

// Frederik Bergmann, Andreas Østergaard, Andreas Young.

public class GrosserAddProductViewModel implements GrosserViewModel {
	private GrosserModelInterface grosserModel;

	public GrosserAddProductViewModel() {
		grosserModel = ModelFactory.getInstance().getGrosserModel();
	}

	public void createProduct(Pair<Product, Integer> newProduct) throws IllegalArgumentException, SQLException {
		grosserModel.createNewProduct(newProduct);
	}
}
