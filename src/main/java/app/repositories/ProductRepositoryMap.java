package app.repositories;

import app.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepositoryMap implements ProductRepository {

    private Map<Long, Product> database = new HashMap<>();

    private long currentId = 0;

    @Override
    public Product save(Product product) {
        product.setId(++currentId);
        database.put(currentId, product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Product findById(Long id) {
        return database.get(id);
    }

    @Override
    public boolean deleteById(Long id) {
        Product oldProduct = findById(id);
        if (oldProduct == null) {
            return false;
        }
        oldProduct.setActive(false);
        return true;
    }

    @Override
    public Product updateById(Product product) {
        Long id = product.getId();
        double newPrice = product.getPrice();
        String newName = product.getName();
        boolean newActive = product.isActive();

        Product oldProduct = findById(id);

        if (oldProduct != null) {
            oldProduct.setName(newName);
            oldProduct.setPrice(newPrice);
            oldProduct.setActive(newActive);

            return oldProduct;
        }
        return null;
    }
//
//    public static void main(String[] args) {
//        ProductRepository repository = new ProductRepositoryMap();
//
//        System.out.println(repository.save(new Product(true, "Coffee", 3.00)));
//
//        System.out.println(repository.save(new Product(false, "Baget", 4.00)));
//        System.out.println(repository.findAll());
//        System.out.println(repository.findById(2L));
//
//        System.out.println("=========Delte=========");
//        repository.deleteById(1L);
//        System.out.println(repository.findById(1L));
//        System.out.println("=========Update=========");
//        Product newProduct = new Product(true, "Baguette", 7.00);
//        newProduct.setId(2L);
//
//        System.out.println(repository.updateById(newProduct));
//
//
//    }
}
