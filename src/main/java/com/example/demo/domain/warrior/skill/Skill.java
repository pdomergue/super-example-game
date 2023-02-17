package com.example.demo.domain.warrior.skill;

import javax.persistence.*;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, name = "type")
    @Enumerated(EnumType.STRING)
    private SkillType type;

    public Skill() {
    }

    public Skill(final SkillType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public SkillType getType() {
        return type;
    }
}
