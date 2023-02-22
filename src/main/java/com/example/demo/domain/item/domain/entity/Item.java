package com.example.demo.domain.item.domain.entity;

import com.example.demo.domain.warrior.domain.entity.skill.Skill;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, name = "type")
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCost> itemCosts = new ArrayList<>();

    @ElementCollection(targetClass = Skill.class)
    @CollectionTable(name = "item_skill", joinColumns = @JoinColumn(name = "item_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "skill")
    private Set<Skill> skillModifiers;

    public Item() {
    }

    public Item(Long id, ItemType type, String name, List<ItemCost> itemCosts, Set<Skill> skillModifiers) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.itemCosts = itemCosts;
        this.skillModifiers = skillModifiers;
    }

    public Long getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<ItemCost> getItemCosts() {
        return itemCosts;
    }

    public Set<Skill> getSkillModifiers() {
        return skillModifiers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
