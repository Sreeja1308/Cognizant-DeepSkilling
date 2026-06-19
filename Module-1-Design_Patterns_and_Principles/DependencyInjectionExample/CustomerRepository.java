package DependencyInjectionExample;

public interface CustomerRepository {
    Customer findCustomerById(String id);
    void saveCustomer(Customer customer);
    void deleteCustomer(String id);
}