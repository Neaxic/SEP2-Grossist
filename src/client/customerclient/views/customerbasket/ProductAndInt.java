package client.customerclient.views.customerbasket;

// Andreas Østergaard

public class ProductAndInt {

    private String Product;
    private int Antal;

    public ProductAndInt(String Product, int v){
        this.Product = Product;
        Antal = v;
    }

    public String getProduct() {
        return Product;
    }

    public int getAntal() {
        return Antal;
    }
}
