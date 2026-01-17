package com.speedrun.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalId;
    private String name;

    @OneTooMany(mappedBy="game", cascade = CascadeType.ALL)
    private List<Category> categories;
}