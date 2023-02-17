package com.example.demo.domain.user;

import com.example.demo.domain.warrior.Warrior;
import javax.persistence.*;
import java.util.List;

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
}
