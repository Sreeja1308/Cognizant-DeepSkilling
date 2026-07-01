package com.example.ormlearn.entity;

import javax.persistence.*;
import java.util.List;  // ← Add this import

@Entity
@Table(name = "options")
public class Options {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "op_id")
    private int id;
    
    @Column(name = "op_text")
    private String text;
    
    @Column(name = "op_is_correct")
    private boolean correct;
    
    @ManyToOne
    @JoinColumn(name = "op_qu_id")
    private Question question;
    
    @OneToMany(mappedBy = "options")
    private List<AttemptOption> attemptOptions;  // This needs List import
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    
    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }
    
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
    
    public List<AttemptOption> getAttemptOptions() { return attemptOptions; }
    public void setAttemptOptions(List<AttemptOption> attemptOptions) { 
        this.attemptOptions = attemptOptions; 
    }
}