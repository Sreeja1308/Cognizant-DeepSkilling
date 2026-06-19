package AdapterPatternExample;

public class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;
    
    public StripeAdapter() {
        this.stripeGateway = new StripeGateway();
    }
    
    @Override
    public void processPayment(double amount) {
        stripeGateway.charge(amount, "USD");
    }
    
    @Override
    public void refundPayment(double amount) {
        stripeGateway.reverse("ch_" + System.currentTimeMillis());
    }
}