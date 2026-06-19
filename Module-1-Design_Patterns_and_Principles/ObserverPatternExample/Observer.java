package ObserverPatternExample;
public interface Observer {
    void update(String stockName, double price);
    String getName();
}