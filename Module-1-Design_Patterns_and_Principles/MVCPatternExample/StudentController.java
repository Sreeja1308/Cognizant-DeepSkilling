package MVCPatternExample;

public class StudentController {
    private Student model;
    private StudentView view;
    
    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    
    public String getStudentId() { return model.getId(); }
    public String getStudentName() { return model.getName(); }
    public String getStudentGrade() { return model.getGrade(); }
    public String getStudentEmail() { return model.getEmail(); }
    
    public void setStudentId(String id) {
        if (id == null || id.trim().isEmpty()) {
            view.displayError("Student ID cannot be empty");
            return;
        }
        model.setId(id);
        view.displayMessage("Student ID updated to: " + id);
    }
    
    public void setStudentName(String name) {
        if (name == null || name.trim().isEmpty()) {
            view.displayError("Student name cannot be empty");
            return;
        }
        model.setName(name);
        view.displayMessage("Student name updated to: " + name);
    }
    
    public void setStudentGrade(String grade) {
        if (grade == null || grade.trim().isEmpty()) {
            view.displayError("Grade cannot be empty");
            return;
        }
        model.setGrade(grade);
        view.displayMessage("Student grade updated to: " + grade);
    }
    
    public void setStudentEmail(String email) {
        if (email == null || !email.contains("@")) {
            view.displayError("Invalid email format");
            return;
        }
        model.setEmail(email);
        view.displayMessage("Student email updated to: " + email);
    }
    
    public void updateView() {
        view.displayStudentDetails(model);
    }
}