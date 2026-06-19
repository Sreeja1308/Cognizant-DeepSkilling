package AdapterPatternExample;

public interface PaymentProcessor {
    void processPayment(double amount);
    void refundPayment(double amount);
}