package app.service;

import app.domain.Product;

import java.util.List;
import java.util.Objects;

public class CustomerCard {
    Long idCustomer;
    List<Product> products;

    public CustomerCard(Long idCustomer, List<Product> products) {
        this.idCustomer = idCustomer;
        this.products = products;
    }
}
