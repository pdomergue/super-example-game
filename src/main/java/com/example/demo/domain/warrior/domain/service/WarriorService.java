package com.example.demo.domain.warrior.domain.service;

import com.example.demo.domain.exception.GameBadRequestException;
import com.example.demo.domain.exception.GameNotFoundException;
import com.example.demo.domain.item.domain.entity.Item;
import com.example.demo.domain.item.domain.service.ItemService;
import com.example.demo.domain.user.domain.entity.User;
import com.example.demo.domain.user.domain.service.UserService;
import com.example.demo.domain.warrior.domain.dao.WarriorRepository;
import com.example.demo.domain.warrior.domain.dto.WarriorDto;
import com.example.demo.domain.warrior.domain.entity.Warrior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        final User user = userService.getCurrentUser();

        if(!warrior.getUser().equals(user)){
            throw new GameBadRequestException("Warrior is not from current user");
        }

        

    }
}
