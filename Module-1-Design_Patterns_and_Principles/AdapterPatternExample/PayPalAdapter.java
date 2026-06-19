package AdapterPatternExample;

public class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;
    
    public PayPalAdapter() {
        this.payPalGateway = new PayPalGateway();
    }
    
    @Override
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
    
    @Override
    public void refundPayment(double amount) {
        payPalGateway.processRefund(amount);
    }
}