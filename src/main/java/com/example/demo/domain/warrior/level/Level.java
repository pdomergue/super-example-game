package com.example.demo.domain.warrior.level;

import javax.persistence.*;

@Entity
@Table(name = "level")
public abstract class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, name = "name")
    @Enumerated(EnumType.STRING)
    private LevelName name;

    public Level() {
    }

    public Level(final LevelName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public LevelName getName() {
        return name;
    }
}
