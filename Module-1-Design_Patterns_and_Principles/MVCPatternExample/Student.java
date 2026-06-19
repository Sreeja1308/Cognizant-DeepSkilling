package MVCPatternExample;

public class Student {
    private String id;
    private String name;
    private String grade;
    private String email;
    
    public Student(String id, String name, String grade, String email) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.email = email;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getGrade() { return grade; }
    public String getEmail() { return email; }
    
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', grade='" + grade + "', email='" + email + "'}";
    }
}
