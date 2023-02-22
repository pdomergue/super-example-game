package com.example.demo.warrior.domain.service;

import com.example.demo.domain.exception.GameBadRequestException;
import com.example.demo.domain.exception.GameNotFoundException;
import com.example.demo.domain.item.domain.entity.Item;
import com.example.demo.domain.item.domain.entity.ItemType;
import com.example.demo.domain.item.domain.service.ItemService;
import com.example.demo.domain.user.domain.entity.User;
import com.example.demo.domain.user.domain.service.UserService;
import com.example.demo.domain.warrior.domain.dao.WarriorRepository;
import com.example.demo.domain.warrior.domain.dto.SkillLevelDto;
import com.example.demo.domain.warrior.domain.dto.WarriorDto;
import com.example.demo.domain.warrior.domain.entity.SkillLevel;
import com.example.demo.domain.warrior.domain.entity.Warrior;
import com.example.demo.domain.warrior.domain.entity.level.Level;
import com.example.demo.domain.warrior.domain.entity.skill.Skill;
import com.example.demo.domain.warrior.domain.service.WarriorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WarriorServiceTest {

    @InjectMocks
    private WarriorService warriorService;

    @Mock
    private UserService userService;

    @Mock
    private ItemService itemService;

    @Mock
    private WarriorRepository warriorRepository;

    @Test
    public void equipItem(){
        Warrior warrior = buildWarrior();

        when(warriorRepository.findById(any()))
                .thenReturn(Optional.of(warrior));

        final Item item = buildItem();
        when(itemService.findItemById(any()))
                .thenReturn(Optional.of(item));

        when(userService.getCurrentUser()).thenReturn(warrior.getUser());

        when(warriorRepository.save(any()))
                .thenReturn(warrior);

        final WarriorDto warriorDto = warriorService.equipItem(1L, 1L);

        final SkillLevelDto speed = warriorDto.getSkills().stream()
                .filter(sl -> sl.getSkill().equals(Skill.SPEED.name()))
                .findFirst().get();

        final SkillLevelDto strength = warriorDto.getSkills().stream()
                .filter(sl -> sl.getSkill().equals(Skill.STRENGTH.name()))
                .findFirst().get();

        final SkillLevelDto resistance = warriorDto.getSkills().stream()
                .filter(sl -> sl.getSkill().equals(Skill.RESISTANCE.name()))
                .findFirst().get();

        assertThat(speed.getGrade()).isEqualTo(2L);
        assertThat(speed.getLevel()).isEqualTo(Level.BRONZE.name());

        assertThat(resistance.getGrade()).isEqualTo(2L);
        assertThat(resistance.getLevel()).isEqualTo(Level.BRONZE.name());

        assertThat(strength.getGrade()).isEqualTo(1L);
        assertThat(strength.getLevel()).isEqualTo(Level.BRONZE.name());
    }

    @Test
    public void equipItemWithChangeOfLevel(){
        Warrior warrior = buildWarrior();
        warrior.getSkills().clear();
        warrior.getSkills().addAll(createUpdatedSkillLevels(warrior));

        when(warriorRepository.findById(any()))
                .thenReturn(Optional.of(warrior));

        final Item item = buildItem();
        when(itemService.findItemById(any()))
                .thenReturn(Optional.of(item));

        when(userService.getCurrentUser()).thenReturn(warrior.getUser());

        when(warriorRepository.save(any()))
                .thenReturn(warrior);

        final WarriorDto warriorDto = warriorService.equipItem(1L, 1L);

        final SkillLevelDto speed = warriorDto.getSkills().stream()
                .filter(sl -> sl.getSkill().equals(Skill.SPEED.name()))
                .findFirst().get();

        final SkillLevelDto strength = warriorDto.getSkills().stream()
                .filter(sl -> sl.getSkill().equals(Skill.STRENGTH.name()))
                .findFirst().get();

        final SkillLevelDto resistance = warriorDto.getSkills().stream()
                .filter(sl -> sl.getSkill().equals(Skill.RESISTANCE.name()))
                .findFirst().get();

        assertThat(speed.getGrade()).isEqualTo(8L);
        assertThat(speed.getLevel()).isEqualTo(Level.BRONZE.name());

        assertThat(resistance.getGrade()).isEqualTo(1L);
        assertThat(resistance.getLevel()).isEqualTo(Level.SILVER.name());

        assertThat(strength.getGrade()).isEqualTo(8L);
        assertThat(strength.getLevel()).isEqualTo(Level.BRONZE.name());
    }


    @Test
    public void equipItemOnLimitGradeDifference_shouldThrowBadRequest(){
        Warrior warrior = buildWarrior();
        warrior.getSkills().clear();
        warrior.getSkills().addAll(createLimitSkillLevels(warrior));

        when(warriorRepository.findById(any()))
                .thenReturn(Optional.of(warrior));

        final Item item = buildItem();
        when(itemService.findItemById(any()))
                .thenReturn(Optional.of(item));

        when(userService.getCurrentUser()).thenReturn(warrior.getUser());

        assertThrows(GameBadRequestException.class,
                () -> warriorService.equipItem(1L, 1L));
    }

    @Test
    public void equipItemForInexistentWarrior_shouldThrowNotFound(){
        when(warriorRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class,
                () -> warriorService.equipItem(1L, 1L));
    }

    @Test
    public void equipInexistentItem_shouldThrowNotFound(){
        Warrior warrior = buildWarrior();

        when(warriorRepository.findById(any()))
                .thenReturn(Optional.of(warrior));

        when(itemService.findItemById(any()))
                .thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class,
                () -> warriorService.equipItem(1L, 1L));
    }

    @Test
    public void equipAlreadyEquipedItem_shouldThrowBadRequest(){
        Warrior warrior = buildWarrior();
        warrior.getItems().add(buildItem());

        when(warriorRepository.findById(any()))
                .thenReturn(Optional.of(warrior));

        final Item item = buildItem();
        when(itemService.findItemById(any()))
                .thenReturn(Optional.of(item));

        assertThrows(GameBadRequestException.class,
                () -> warriorService.equipItem(1L, 1L));
    }


    @Test
    public void equipWarriorOfAnotherUser_shouldThrowBadRequest(){
        Warrior warrior = buildWarrior();

        when(warriorRepository.findById(any()))
                .thenReturn(Optional.of(warrior));

        final Item item = buildItem();
        when(itemService.findItemById(any()))
                .thenReturn(Optional.of(item));

        User user2 = new User(2L, "username", new ArrayList<>(), new ArrayList<>());
        when(userService.getCurrentUser()).thenReturn(user2);


        assertThrows(GameBadRequestException.class,
                () -> warriorService.equipItem(1L, 1L));
    }

    private Warrior buildWarrior(){
        User user = new User(1L, "username", new ArrayList<>(), new ArrayList<>());

        Warrior warrior = new Warrior(1L, new ArrayList<>(), new ArrayList<>(), user);
        warrior.getSkills().addAll(SkillLevel.createSkillLevels(warrior));

        user.getWarriors().add(warrior);

        return warrior;
    }

    private Item buildItem(){
        Set<Skill> skillModifiers = new HashSet<>();
        skillModifiers.add(Skill.SPEED);
        skillModifiers.add(Skill.RESISTANCE);
        return new Item(1L, ItemType.ARMOUR, "armadura 1", new ArrayList<>(), skillModifiers);
    }

    private List<SkillLevel> createUpdatedSkillLevels(final Warrior warrior){
        SkillLevel speed = new SkillLevel(Skill.SPEED, Level.BRONZE, 7L, warrior);
        SkillLevel strength = new SkillLevel(Skill.STRENGTH, Level.BRONZE, 8L, warrior);
        SkillLevel resistance = new SkillLevel(Skill.RESISTANCE, Level.BRONZE, 10L, warrior);

        List<SkillLevel> skillLevels = new ArrayList<>();
        skillLevels.add(speed);
        skillLevels.add(strength);
        skillLevels.add(resistance);

        return skillLevels;
    }

    private List<SkillLevel> createLimitSkillLevels(final Warrior warrior){
        SkillLevel speed = new SkillLevel(Skill.SPEED, Level.BRONZE, 7L, warrior);
        SkillLevel strength = new SkillLevel(Skill.STRENGTH, Level.BRONZE, 5L, warrior);
        SkillLevel resistance = new SkillLevel(Skill.RESISTANCE, Level.BRONZE, 10L, warrior);

        List<SkillLevel> skillLevels = new ArrayList<>();
        skillLevels.add(speed);
        skillLevels.add(strength);
        skillLevels.add(resistance);

        return skillLevels;
    }
}
