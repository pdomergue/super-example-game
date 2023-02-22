package com.example.demo.domain.warrior.domain.entity;

import com.example.demo.domain.warrior.domain.entity.level.Level;
import com.example.demo.domain.warrior.domain.entity.skill.Skill;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skill_level")
public class SkillLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "warrior_id")
    private Warrior warrior;

    @Column(nullable = false, name = "skill")
    @Enumerated(EnumType.STRING)
    public Skill skill;

    @Column(nullable = false, name = "level")
    @Enumerated(EnumType.STRING)
    public Level level;

    @Column(name = "grade", nullable = false)
    public Long grade;

    public SkillLevel() {
    }

    public SkillLevel(final Skill skill, final Level level,
                      final Long grade, final Warrior warrior) {
        this.skill = skill;
        this.level = level;
        this.grade = grade;
        this.warrior = warrior;
    }

    public Long getId() {
        return id;
    }

    public Warrior getWarrior() {
        return warrior;
    }

    public Skill getSkill() {
        return skill;
    }

    public Level getLevel() {
        return level;
    }

    public Long getGrade() {
        return grade;
    }

    public static List<SkillLevel> createSkillLevels(final Warrior warrior){
        SkillLevel speed = new SkillLevel(Skill.SPEED, Level.BRONZE, 1L, warrior);
        SkillLevel strength = new SkillLevel(Skill.STRENGTH, Level.BRONZE, 1L, warrior);
        SkillLevel resistance = new SkillLevel(Skill.RESISTANCE, Level.BRONZE, 1L, warrior);

        List<SkillLevel> skillLevels = new ArrayList<>();
        skillLevels.add(speed);
        skillLevels.add(strength);
        skillLevels.add(resistance);

        return skillLevels;
    }

    public void levelUp(){
        if(grade < 10){
            grade = grade + 1;
        }else if(grade.equals(10L) && this.level.hasNext()){
            level = level.next();
            grade = 1L;
        }
    }
}
