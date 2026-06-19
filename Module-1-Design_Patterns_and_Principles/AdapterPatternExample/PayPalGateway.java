package AdapterPatternExample;

public class PayPalGateway {
    private String apiKey;
    private String apiSecret;
    
    public PayPalGateway() {
        this.apiKey = "PAYPAL_API_KEY";
        this.apiSecret = "PAYPAL_SECRET";
    }
    
    public void makePayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        System.out.println("Using API Key: " + apiKey);
        System.out.println("Payment successful via PayPal!");
    }
    
    public void processRefund(double amount) {
        System.out.println("Processing PayPal refund of $" + amount);
        System.out.println("Refund successful via PayPal!");
    }
}