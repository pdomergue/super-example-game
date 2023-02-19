package com.example.demo.domain.warrior.domain.dto;

import com.example.demo.domain.warrior.domain.entity.SkillLevel;

public class SkillLevelDto {
    public Long id;
    public String skill;
    public String level;
    public Long grade;

    public SkillLevelDto() {
    }

    public SkillLevelDto(Long id, String skill, String level, Long grade) {
        this.id = id;
        this.skill = skill;
        this.level = level;
        this.grade = grade;
    }

    public static SkillLevelDto from(final SkillLevel skillLevel) {
        return new SkillLevelDto(skillLevel.getId(),
                skillLevel.getSkill().name(),
                skillLevel.getLevel().name(),
                skillLevel.getGrade());
    }

    public Long getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public String getLevel() {
        return level;
    }

    public Long getGrade() {
        return grade;
    }
}
