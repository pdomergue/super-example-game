package com.example.demo.domain.user;

import com.example.demo.domain.money.Money;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "game_user_balance")
public class UserBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, name = "money")
    @Enumerated(EnumType.STRING)
    private Money money;

    @Column(name = "balance")
    private BigDecimal balance;

    public UserBalance() {
    }

    public UserBalance(Long id, User user, Money money, BigDecimal balance) {
        this.id = id;
        this.user = user;
        this.money = money;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Money getMoney() {
        return money;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
