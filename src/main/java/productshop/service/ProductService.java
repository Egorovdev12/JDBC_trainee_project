package productshop.service;

import productshop.entity.Product;
import productshop.repository.ProductRepository;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
