package com.example.demo.domain.warrior.domain.dto;

import com.example.demo.domain.item.domain.dto.ItemDto;
import com.example.demo.domain.warrior.domain.entity.Warrior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarriorDto {
    private Long id;
    private List<SkillLevelDto> skills = new ArrayList<>();
    private List<ItemDto> items = new ArrayList<>();

    public WarriorDto() {
    }

    public WarriorDto(final Long id, final List<SkillLevelDto> skills, final List<ItemDto> items) {
        this.id = id;
        this.skills = skills;
        this.items = items;
    }

    public static WarriorDto from(final Warrior warrior) {
        List<SkillLevelDto> skills = warrior.getSkills()
                .stream()
                .map(skillLevel -> SkillLevelDto.from(skillLevel))
                .collect(Collectors.toList());

        List<ItemDto> items = warrior.getItems()
                .stream()
                .map(item -> ItemDto.from(item))
                .collect(Collectors.toList());

        return new WarriorDto(warrior.getId(), skills, items);
    }

    public Long getId() {
        return id;
    }

    public List<SkillLevelDto> getSkills() {
        return skills;
    }

    public List<ItemDto> getItems() {
        return items;
    }
}
