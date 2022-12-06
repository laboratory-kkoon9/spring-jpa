package com.laboratorykkoon9.springjpa.service;

import com.laboratorykkoon9.springjpa.domain.item.*;
import com.laboratorykkoon9.springjpa.repository.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ItemService {
    @Autowired
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
