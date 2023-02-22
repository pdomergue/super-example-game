package com.example.demo.domain.warrior.application;

import com.example.demo.domain.warrior.domain.dto.WarriorDto;
import com.example.demo.domain.warrior.domain.service.WarriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{warriorId}/item/{itemId}")
    public WarriorDto equipItem(@PathVariable(value = "warriorId") final Long warriorId,
                                @PathVariable(value = "itemId") final Long itemId){
        return warriorService.equipItem(warriorId, itemId);
    }
}
