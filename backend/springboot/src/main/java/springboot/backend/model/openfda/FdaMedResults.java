package springboot.backend.model.openfda;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FdaMedResults {
    @JsonProperty("products")
    private FdaProducts[] products;

    public FdaProducts[] getProducts() {
        return products;
    }

    public void setProducts(FdaProducts[] products) {
        this.products = products;
    }
}
