package Exercise5_TaskManagement;

public class Task {
    private int taskId;
    private String taskName;
    private String status;
    private Task next;
    
    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }
    
    public int getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public String getStatus() { return status; }
    public Task getNext() { return next; }
    public void setNext(Task next) { this.next = next; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return "Task{id=" + taskId + ", name='" + taskName + "', status='" + status + "'}";
    }
}