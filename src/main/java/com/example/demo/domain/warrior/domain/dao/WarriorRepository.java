package com.example.demo.domain.warrior.domain.dao;

import com.example.demo.domain.warrior.domain.entity.Warrior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarriorRepository extends JpaRepository<Warrior, Long> {
}
