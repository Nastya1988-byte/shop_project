package app.service;

import app.domain.Customer;
import app.domain.Product;
import app.exceptions.CustomerNotFaundExeption;
import app.exceptions.CustomerSaveException;
import app.exceptions.CustomerUpdateException;
import app.exceptions.ProductUpdateException;
import app.repositories.CustomerRepository;
import app.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final ProductRepository productRepository;

    public CustomerServiceImpl(CustomerRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }


    @Override
    public Customer save(Customer customer) {

        if (customer == null) {
            throw new CustomerNotFaundExeption("Customer cannot be null");
        }
        String name = customer.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new CustomerSaveException("Customer cannot by emptiness");
        }
        customer.setActive(true);
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return repository.findAll().stream()
                .filter(Customer::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public Customer getByIdActive(Long id) {
        Customer customer = repository.findCustomerById(id);
        if (customer == null || !customer.isActive()) {
            throw new CustomerNotFaundExeption("Customer with id = " + id + " not found");
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new ProductUpdateException("Customer cannot be null");
        }
        Long id = customer.getId();
        if (id <= 0) {
            throw new CustomerUpdateException("Customer id should be positive");
        }
        String name = customer.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new CustomerUpdateException("Customer name cannot bi is empty or null");
        }
        repository.update(customer);
    }

    @Override
    public void deleteById(Long id) {
        getByIdActive(id).setActive(false);
    }

    @Override
    public void deleteByName(String name) {
        Customer customer = repository.findAll().stream()
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (name == null || !customer.isActive()) {
            throw new CustomerNotFaundExeption("Customer cannot be null");
        }
        customer.setActive(false);

    }

    @Override
    public void restoreById(Long id) {
        repository.findCustomerById(id).setActive(true);
    }

    @Override
    public long getActiveCustomersTotalCount() {
        return getAllActiveCustomers().size();
    }

    @Override
    public double getCartProductsTotalCostByCustomerIdActive() {
        return 0;//TODO
    }

    @Override
    public double getCartProductsAveragePriceByCustomerIdActive() {
        return 0;//TODO
    }

    Customer customer;
    @Override
    public Product saveCardActive(Long id) {
        return null;
    }

    @Override
    public void deleteCardProduct(Long id) {

    }

    @Override
    public void clearCardActive(Long id) {

    }

}
