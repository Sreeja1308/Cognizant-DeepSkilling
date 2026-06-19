package DependencyInjectionExample;

public class CustomerService {
    private final CustomerRepository customerRepository;
    
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        System.out.println("CustomerService initialized with dependency injection");
    }
    
    public Customer getCustomerById(String id) {
        System.out.println("Service: Fetching customer with ID: " + id);
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            System.out.println("Service: Customer not found with ID: " + id);
        } else {
            System.out.println("Service: Customer found - " + customer.getName());
        }
        return customer;
    }
    
    public void registerNewCustomer(Customer customer) {
        System.out.println("Service: Registering new customer: " + customer.getName());
        customerRepository.saveCustomer(customer);
    }
    
    public void removeCustomer(String id) {
        System.out.println("Service: Removing customer with ID: " + id);
        customerRepository.deleteCustomer(id);
    }
    
    public void displayAllCustomers() {
        if (customerRepository instanceof CustomerRepositoryImpl) {
            ((CustomerRepositoryImpl) customerRepository).displayAllCustomers();
        }
    }
}