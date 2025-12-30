package productshop.service;

import productshop.entity.Product;
import productshop.repository.CustomerRepository;
import productshop.repository.ProductRepository;

public class ProductService implements ServiceInterface {

    private final ProductRepository productRepository;

    private ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    private static class Holder{
        static final ProductService INSTANCE = new ProductService(ProductRepository.getInstance());
    }

    public static ProductService getInstance() {
        return Holder.INSTANCE;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    public void updatePriceByProductId(int id, double price) {
        productRepository.updatePrice(id, price);
    }

    public void deleteProductByName(String name) {
        productRepository.deleteByName(name);
    }

    public void printAllProducts() {
        productRepository.findAllAndPrint();
    }
}
