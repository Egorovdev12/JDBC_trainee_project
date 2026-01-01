package productshop.system.cache;

import productshop.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCache {

    private static List<Product> PRODUCTS = new ArrayList<>();

    public static List<Product> getPRODUCTS() {
        return PRODUCTS;
    }

    public static void setPRODUCTS(List<Product> PRODUCTS) {
        ProductCache.PRODUCTS = PRODUCTS;
    }

    public static void showInfo() {
        for (Product product : PRODUCTS) {
            System.out.println(product.toString());
        }
    }

    public static Product findProductById(int id) {
        for (Product product : PRODUCTS) {
            if(product.getId()==id){
                return product;
            }
        }
        return null;
    }
}
