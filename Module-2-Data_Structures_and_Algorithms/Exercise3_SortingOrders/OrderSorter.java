package Exercise3_SortingOrders;

public class OrderSorter {
    
    public void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }
    
    public void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }
    
    private int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        
        return i + 1;
    }
    
    public static void main(String[] args) {
        Order[] orders = {
            new Order(1, "John Doe", 150.50),
            new Order(2, "Jane Smith", 75.25),
            new Order(3, "Bob Johnson", 200.00),
            new Order(4, "Alice Brown", 50.75),
            new Order(5, "Charlie Wilson", 300.00)
        };
        
        OrderSorter sorter = new OrderSorter();
        
        Order[] bubbleSorted = orders.clone();
        sorter.bubbleSort(bubbleSorted);
        System.out.println("Bubble Sort Results:");
        for (Order order : bubbleSorted) {
            System.out.println(order);
        }
        
        Order[] quickSorted = orders.clone();
        sorter.quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("\nQuick Sort Results:");
        for (Order order : quickSorted) {
            System.out.println(order);
        }
    }
}