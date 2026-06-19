package AdapterPatternExample;

public class TestAdapter {
    public static void main(String[] args) {
        
        System.out.println("Using PayPal");
        PaymentProcessor paypalProcessor = new PayPalAdapter();
        paypalProcessor.processPayment(99.99);
        paypalProcessor.refundPayment(49.99);
        
        System.out.println("\nUsing Stripe");
        PaymentProcessor stripeProcessor = new StripeAdapter();
        stripeProcessor.processPayment(149.50);
        stripeProcessor.refundPayment(25.00);
    }
}