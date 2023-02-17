package com.example.demo.domain.item;

import com.example.demo.domain.warrior.skill.Skill;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCost> itemCosts = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "item_skill",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skillModifiers;

    public Item() {
    }

    public Item(Long id, ItemType type, List<ItemCost> itemCosts, List<Skill> skillModifiers) {
        this.id = id;
        this.type = type;
        this.itemCosts = itemCosts;
        this.skillModifiers = skillModifiers;
    }

    public Long getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public List<ItemCost> getItemCosts() {
        return itemCosts;
    }

    public List<Skill> getSkillModifiers() {
        return skillModifiers;
    }
}
