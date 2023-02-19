package com.example.demo.domain.warrior.application;

import com.example.demo.domain.warrior.domain.dto.WarriorDto;
import com.example.demo.domain.warrior.domain.service.WarriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/warrior")
public class WarriorController {

    @Autowired
    private WarriorService warriorService;

    @PostMapping
    public WarriorDto createWarrior() {
        return warriorService.createWarrior();
    }

    @PostMapping("/{warriorId}/equip-item/{itemId}")
    public WarriorDto equipItem(@PathParam(value = "warriorId") final Long warriorId,
                                @PathParam(value = "itemId") final Long itemId){
        return null;
    }
}
