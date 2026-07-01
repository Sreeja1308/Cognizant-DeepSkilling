package com.example.ormlearn.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;  // ← Add this import

@Entity
@Table(name = "attempt")
public class Attempt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private int id;
    
    @Column(name = "at_attempted_date")
    private LocalDateTime attemptedDate;
    
    @ManyToOne
    @JoinColumn(name = "at_us_id")
    private User user;
    
    @OneToMany(mappedBy = "attempt")
    private List<AttemptQuestion> attemptQuestions;
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public LocalDateTime getAttemptedDate() { return attemptedDate; }
    public void setAttemptedDate(LocalDateTime attemptedDate) { this.attemptedDate = attemptedDate; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public List<AttemptQuestion> getAttemptQuestions() { return attemptQuestions; }
    public void setAttemptQuestions(List<AttemptQuestion> attemptQuestions) { 
        this.attemptQuestions = attemptQuestions; 
    }
}