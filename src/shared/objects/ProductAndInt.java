package shared.objects;

// Andreas Østergaard

public class ProductAndInt {

    private final String product;
    private final int amount;
    private final int productID;

    public ProductAndInt(String product, int ID, int v){
        this.product = product;
        productID = ID;
        amount = v;
    }

    public int getProductID(){ return productID; }

    public String getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }
}
