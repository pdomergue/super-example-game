package com.example.demo.domain.warrior;

import com.example.demo.domain.warrior.level.Bronze;
import com.example.demo.domain.warrior.level.Level;
import com.example.demo.domain.warrior.skill.Skill;
import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "skill_id")
    public Skill skill;

    @ManyToOne
    @JoinColumn(name = "level_id")
    public Level level;

    @Column(name = "grade", nullable = false)
    public Long grade;

    public SkillLevel() {
    }

    public SkillLevel(Skill skill, Level level, Long grade) {
        this.skill = skill;
        this.level = level;
        this.grade = grade;
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

    public SkillLevel create(final Skill skill){
        return new SkillLevel(skill, new Bronze(), 1L);
    }

}
