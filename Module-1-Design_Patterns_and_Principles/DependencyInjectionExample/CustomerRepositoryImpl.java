package DependencyInjectionExample;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {
    private Map<String, Customer> customerDatabase;
    
    public CustomerRepositoryImpl() {
        this.customerDatabase = new HashMap<>();
        customerDatabase.put("C001", new Customer("C001", "John Doe", "john@example.com", "123 Main St"));
        customerDatabase.put("C002", new Customer("C002", "Jane Smith", "jane@example.com", "456 Oak Ave"));
        customerDatabase.put("C003", new Customer("C003", "Bob Johnson", "bob@example.com", "789 Pine Rd"));
    }
    
    @Override
    public Customer findCustomerById(String id) {
        if (customerDatabase.containsKey(id)) {
            return customerDatabase.get(id);
        }
        return null;
    }
    
    @Override
    public void saveCustomer(Customer customer) {
        customerDatabase.put(customer.getId(), customer);
        System.out.println("Customer saved: " + customer.getId());
    }
    
    @Override
    public void deleteCustomer(String id) {
        if (customerDatabase.containsKey(id)) {
            customerDatabase.remove(id);
            System.out.println("Customer deleted: " + id);
        } else {
            System.out.println("Customer not found: " + id);
        }
    }
    
    public void displayAllCustomers() {
        System.out.println("\n--- All Customers in Database ---");
        for (Customer customer : customerDatabase.values()) {
            System.out.println(customer);
        }
    }
}