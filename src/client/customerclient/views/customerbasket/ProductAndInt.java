package client.customerclient.views.customerbasket;

// Andreas Østergaard

public class ProductAndInt {

    private String Product;
    private int Antal;
    private int ProductID;

    public ProductAndInt(String Product, int ID, int v){
        this.Product = Product;
        ProductID = ID;
        Antal = v;
    }

    public int getProductID(){ return ProductID; }

    public String getProduct() {
        return Product;
    }

    public int getAntal() {
        return Antal;
    }
}
