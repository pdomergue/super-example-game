package com.example.demo.domain.item.domain.dto;

import com.example.demo.domain.item.domain.entity.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemDto {
    private Long id;
    private String type;
    private String name;
    private List<String> skillModifiers;

    public ItemDto() {
    }

    public ItemDto(Long id, String type, String name, List<String> skillModifiers) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.skillModifiers = skillModifiers;
    }

    public static ItemDto from(final Item item) {
        List<String> skills = item.getSkillModifiers()
                .stream()
                .map(skill -> skill.name())
                .collect(Collectors.toList());

        return new ItemDto(item.getId(),
                item.getType().name(),
                item.getName(),
                skills);
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<String> getSkillModifiers() {
        return skillModifiers;
    }
}
