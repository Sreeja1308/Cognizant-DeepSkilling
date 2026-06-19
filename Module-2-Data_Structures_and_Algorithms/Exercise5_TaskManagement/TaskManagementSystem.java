package Exercise5_TaskManagement;

public class TaskManagementSystem {
    private Task head;
    private int size;
    
    public TaskManagementSystem() {
        this.head = null;
        this.size = 0;
    }
    
    public void addTask(Task task) {
        if (head == null) {
            head = task;
        } else {
            Task current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(task);
        }
        size++;
    }
    
    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.getTaskId() == taskId) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    
    public void traverseTasks() {
        Task current = head;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }
    
    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false;
        }
        
        if (head.getTaskId() == taskId) {
            head = head.getNext();
            size--;
            return true;
        }
        
        Task current = head;
        while (current.getNext() != null) {
            if (current.getNext().getTaskId() == taskId) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    
    public int getSize() { return size; }
    
    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();
        
        tms.addTask(new Task(1, "Complete report", "Pending"));
        tms.addTask(new Task(2, "Review code", "In Progress"));
        tms.addTask(new Task(3, "Deploy application", "Completed"));
        
        System.out.println("All Tasks:");
        tms.traverseTasks();
        
        System.out.println("\nSearch for task 2:");
        System.out.println(tms.searchTask(2));
        
        tms.deleteTask(2);
        System.out.println("\nAfter deleting task 2:");
        tms.traverseTasks();
        
        System.out.println("\nTotal tasks: " + tms.getSize());
    }
}