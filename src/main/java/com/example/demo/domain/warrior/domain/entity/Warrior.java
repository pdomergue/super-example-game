package com.example.demo.domain.warrior.domain.entity;

import com.example.demo.domain.item.domain.entity.Item;
import com.example.demo.domain.user.domain.entity.User;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "warrior")
public class Warrior {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "warrior", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SkillLevel> skills = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "warrior_item",
            joinColumns = @JoinColumn(name = "warrior_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Warrior() {
    }

    public Warrior(final Long id,
                   final List<SkillLevel> skills,
                   final List<Item> items,
                   final User user) {
        this.skills = skills;
        this.items = items;
        this.user = user;
    }

    public Warrior(final User user){
        this.user = user;
        this.skills = SkillLevel.createSkillLevels(this);
    }

    public Long getId() {
        return id;
    }

    public List<SkillLevel> getSkills() {
        return skills;
    }

    public List<Item> getItems() {
        return items;
    }

    public User getUser() {
        return user;
    }
}
