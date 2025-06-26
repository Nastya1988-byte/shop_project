package app.repositories;

import app.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryMap implements CustomerRepository {

    private Map<Long, Customer> database = new HashMap<>();

    private long counterId = 0;

    @Override
    public Customer save(Customer customer) {
        customer.setId(++counterId);
        database.put(counterId, customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Customer findCustomerById(Long id) {
        return database.get(id);
    }

    @Override
    public Customer update(Customer customer) {
        Long id = customer.getId();
        String newName = customer.getName();

        Customer oldCustomer = findCustomerById(id);

        if (oldCustomer != null){
            oldCustomer.setName(newName);
            return oldCustomer;
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Customer oldCustomer = findCustomerById(id);
        if (oldCustomer == null) {
            return false;
        }
        oldCustomer.setActive(false);
        return true;
    }
}
