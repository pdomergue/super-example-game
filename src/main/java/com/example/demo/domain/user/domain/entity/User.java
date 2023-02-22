package com.example.demo.domain.user.domain.entity;

import com.example.demo.domain.warrior.domain.entity.Warrior;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "game_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Warrior> warriors;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBalance> userBalances;

    public User() {
    }

    public User(Long id, String userName, List<Warrior> warriors, List<UserBalance> userBalances) {
        this.id = id;
        this.userName = userName;
        this.warriors = warriors;
        this.userBalances = userBalances;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public List<Warrior> getWarriors() {
        return warriors;
    }

    public List<UserBalance> getUserBalances() {
        return userBalances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
