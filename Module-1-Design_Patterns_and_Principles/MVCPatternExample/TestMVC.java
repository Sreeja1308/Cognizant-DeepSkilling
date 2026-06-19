package MVCPatternExample;

public class TestMVC {
    public static void main(String[] args) {
        
        Student student = new Student("S001", "John Doe", "A", "john.doe@example.com");
        
        StudentView view = new StudentView();
        
        StudentController controller = new StudentController(student, view);
        
        System.out.println("Initial Student Details");
        controller.updateView();
        
        System.out.println("\nUpdating Student Details");
        controller.setStudentName("Jane Smith");
        controller.setStudentGrade("A+");
        controller.setStudentEmail("jane.smith@example.com");
        
        System.out.println("\nUpdated Student Details");
        controller.updateView();
        
        System.out.println("\nTesting Validation");
        controller.setStudentEmail("invalid-email");
        controller.setStudentName("");
        
        System.out.println("\nFinal Student Details");
        controller.updateView();
    }
}