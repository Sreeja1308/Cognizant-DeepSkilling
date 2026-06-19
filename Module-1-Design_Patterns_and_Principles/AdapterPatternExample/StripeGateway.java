package AdapterPatternExample;

public class StripeGateway {
    private String publishableKey;
    private String secretKey;
    
    public StripeGateway() {
        this.publishableKey = "pk_test_STRIPE";
        this.secretKey = "sk_test_STRIPE";
    }
    
    public void charge(double amount, String currency) {
        System.out.println("Charging $" + amount + " " + currency + " via Stripe");
        System.out.println("Using Secret Key: " + secretKey);
        System.out.println("Charge successful!");
    }
    
    public void reverse(String chargeId) {
        System.out.println("Refunding charge ID: " + chargeId);
        System.out.println("Refund successful via Stripe!");
    }
}