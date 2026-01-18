package com.speedrun.data.model;

import jakarta.persistence.*;

@Entity
public class Run {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalId;
    private double timeSeconds;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Platform platform;

    @ManyToOne
    private User user;

    // Gettery i Settery
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public double getTimeSeconds() { return timeSeconds; }
    public void setTimeSeconds(double timeSeconds) { this.timeSeconds = timeSeconds; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public Platform getPlatform() { return platform; }
    public void setPlatform(Platform platform) { this.platform = platform; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}