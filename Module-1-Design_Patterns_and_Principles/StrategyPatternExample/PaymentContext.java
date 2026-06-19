package StrategyPatternExample;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
        System.out.println("Payment method changed to: " + strategy.getMethodName());
    }
    
    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Error: No payment method selected!");
            return;
        }
        System.out.println("\n--- Executing Payment ---");
        paymentStrategy.pay(amount);
    }
}