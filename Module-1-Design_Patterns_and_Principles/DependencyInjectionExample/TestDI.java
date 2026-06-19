package DependencyInjectionExample;

public class TestDI {
    public static void main(String[] args) {
        
        System.out.println("Setting up Dependencies");
        CustomerRepository repository = new CustomerRepositoryImpl();
        
        CustomerService service = new CustomerService(repository);
        
        System.out.println("\nDisplay All Customers");
        service.displayAllCustomers();
        
        System.out.println("\nFinding a Customer");
        Customer customer = service.getCustomerById("C001");
        if (customer != null) {
            System.out.println("Found: " + customer);
        }
        
        System.out.println("\n--- Finding Non-existent Customer ---");
        service.getCustomerById("C999");
        
        System.out.println("\n--- Registering New Customer ---");
        Customer newCustomer = new Customer("C004", "Alice Williams", "alice@example.com", "321 Elm St");
        service.registerNewCustomer(newCustomer);
        
        System.out.println("\n--- Display All Customers After Addition ---");
        service.displayAllCustomers();
        
        System.out.println("\n--- Removing a Customer ---");
        service.removeCustomer("C002");
        
        System.out.println("\n--- Display All Customers After Removal ---");
        service.displayAllCustomers();
        
    }
}