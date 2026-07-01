package com.example.ormlearn.entity;

import javax.persistence.*;
import java.util.List;  // ← Add this import

@Entity
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private int id;
    
    @Column(name = "us_name")
    private String name;
    
    @Column(name = "us_username")
    private String username;
    
    @OneToMany(mappedBy = "user")
    private List<Attempt> attempts;
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public List<Attempt> getAttempts() { return attempts; }
    public void setAttempts(List<Attempt> attempts) { this.attempts = attempts; }
}