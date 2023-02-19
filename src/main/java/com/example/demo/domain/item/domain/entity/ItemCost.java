package com.example.demo.domain.item.domain.entity;

import com.example.demo.domain.money.Money;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_cost")
public class ItemCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(nullable = false, name = "money")
    @Enumerated(EnumType.STRING)
    private Money money;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public ItemCost() {
    }

    public ItemCost(Long id, BigDecimal value, Money money, Item item) {
        this.id = id;
        this.value = value;
        this.money = money;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Money getMoney() {
        return money;
    }

    public Item getItem() {
        return item;
    }
}
