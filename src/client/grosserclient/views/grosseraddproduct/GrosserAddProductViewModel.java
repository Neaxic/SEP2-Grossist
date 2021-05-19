package client.grosserclient.views.grosseraddproduct;

import client.core.ModelFactory;
import client.grosserclient.model.GrosserModel;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.GrosserViewModel;
import javafx.util.Pair;
import shared.wares.Product;

public class GrosserAddProductViewModel implements GrosserViewModel
{
    private GrosserModelInterface grosserModel;

    public GrosserAddProductViewModel(){
        grosserModel = ModelFactory.getInstance().getGrosserModel();
    }

    public void sendOrder(Pair<Product, Integer> newProduct){
        grosserModel.createNewProduct(newProduct);
    }
}
