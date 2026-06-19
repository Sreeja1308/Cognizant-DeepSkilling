package StrategyPatternExample;

public class TestStrategy {
    public static void main(String[] args) {
       
        PaymentContext context = new PaymentContext();
        
        PaymentStrategy creditCard = new CreditCardPayment(
            "1234567890123456", "John Doe", "123", "12/25");
        context.setPaymentStrategy(creditCard);
        context.executePayment(299.99);
        
        PaymentStrategy paypal = new PayPalPayment("john.doe@example.com", "password123");
        context.setPaymentStrategy(paypal);
        context.executePayment(149.50);
        
        System.out.println("\nAnother Transaction");
        context.executePayment(89.99);
    }
}