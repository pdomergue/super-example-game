package com.example.demo.domain.warrior.domain.service;

import com.example.demo.domain.exception.GameBadRequestException;
import com.example.demo.domain.exception.GameNotFoundException;
import com.example.demo.domain.item.domain.entity.Item;
import com.example.demo.domain.item.domain.service.ItemService;
import com.example.demo.domain.user.domain.entity.User;
import com.example.demo.domain.user.domain.service.UserService;
import com.example.demo.domain.warrior.domain.dao.WarriorRepository;
import com.example.demo.domain.warrior.domain.dto.WarriorDto;
import com.example.demo.domain.warrior.domain.entity.SkillLevel;
import com.example.demo.domain.warrior.domain.entity.Warrior;
import com.example.demo.domain.warrior.domain.entity.skill.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WarriorService {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private WarriorRepository warriorRepository;

    public WarriorDto createWarrior(){
        Warrior warrior =  new Warrior(userService.getCurrentUser());
        warriorRepository.save(warrior);
        return WarriorDto.from(warrior);
    }

    public WarriorDto equipItem(final Long warriorId, final Long itemId) {
        final Warrior warrior = warriorRepository.findById(warriorId)
                .orElseThrow(()-> new GameNotFoundException("Warrior does not exists"));

        final Item item = itemService.findItemById(itemId)
                .orElseThrow(()-> new GameNotFoundException("Item does not exists"));

        if(warrior.getItems().contains(item)){
            throw new GameBadRequestException("Warrior already have this item");
        }

        final User user = userService.getCurrentUser();

        if(!warrior.getUser().equals(user)){
            throw new GameBadRequestException("Warrior is not from current user");
        }

        updateSkillLevelsWithItem(warrior.getSkills(), item);
        warrior.getItems().add(item);
        warriorRepository.save(warrior);
        return WarriorDto.from(warrior);
    }

    private void updateSkillLevelsWithItem(final List<SkillLevel> skillLevelList,
                                          final Item item) {
        for(Skill s: item.getSkillModifiers()){
            skillLevelList.stream()
                    .filter(skillLevel -> skillLevel.getSkill().equals(s))
                    .findFirst()
                    .ifPresent(skillLevel -> skillLevel.levelUp());
        }

        checkValidDifferenceBetweenSkills(skillLevelList);
    }

    private void checkValidDifferenceBetweenSkills(final List<SkillLevel> skillLevelList){
        final Long skillGrade1 = skillLevelList.get(0).getGrade()
                + (skillLevelList.get(0).getLevel().ordinal() * 10);
        final Long skillGrade2 = skillLevelList.get(1).getGrade()
                + (skillLevelList.get(1).getLevel().ordinal() * 10);
        final Long skillGrade3 = skillLevelList.get(2).getGrade()
                + (skillLevelList.get(2).getLevel().ordinal() * 10);

        if(Math.abs(skillGrade1 - skillGrade2) > 5
                || Math.abs(skillGrade1 - skillGrade3) > 5
                || Math.abs(skillGrade2 - skillGrade3) > 5) {
            throw new GameBadRequestException("Cant equip item because difference between skills would be invalid");
        }
    }
}
