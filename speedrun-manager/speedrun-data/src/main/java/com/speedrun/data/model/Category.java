package com.speedrun.data.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalId;
    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    // Gettery i Settery
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER) // Dodaj fetch
    @OrderBy("timeSeconds ASC") // Żeby biegi były od najszybszego
    private List<Run> runs;

    public List<Run> getRuns() { return runs; }
    public void setRuns(List<Run> runs) { this.runs = runs; }
}