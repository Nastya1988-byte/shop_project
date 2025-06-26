package app.service;

import app.domain.Customer;
import app.domain.Product;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);

    List<Customer> getAllActiveCustomers();

    Customer getByIdActive(Long id);

    void update(Customer customer);

    void deleteById(Long id);

    void deleteByName(String name);

    void restoreById(Long id);

    long getActiveCustomersTotalCount();

    double getCartProductsTotalCostByCustomerIdActive();

    double getCartProductsAveragePriceByCustomerIdActive();

    Product saveCardActive(Long id);

    void deleteCardProduct(Long id);

    void clearCardActive(Long id);




}
