package MVCPatternExample;

public class StudentView {
    
    public void displayStudentDetails(Student student) {
      
        System.out.println("        STUDENT DETAILS              ");
        System.out.println(" Student ID:    " + padRight(student.getId(), 25) );
        System.out.println(" Name:          " + padRight(student.getName(), 25) );
        System.out.println(" Grade:         " + padRight(student.getGrade(), 25) );
        System.out.println(" Email:         " + padRight(student.getEmail(), 25) );
    }
    
    public void displayMessage(String message) {
        System.out.println("📌 " + message);
    }
    
    public void displayError(String error) {
        System.out.println("❌ Error: " + error);
    }
    
    private String padRight(String s, int n) {
        if (s == null) s = "";
        return String.format("%-" + n + "s", s);
    }
}