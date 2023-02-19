package com.example.demo.domain.item.domain.service;

import com.example.demo.domain.item.domain.entity.Item;
import com.example.demo.domain.item.domain.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Optional<Item> findItemById(final Long itemId) {
        return itemRepository.findById(itemId);
    }
}
