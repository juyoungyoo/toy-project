package com.toy.shoppingmall.items;

import com.toy.shoppingmall.items.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    void saveItem(Item item) {
        itemRepository.save(item);
    }

    List<Item> findItems() {
        return itemRepository.findAll();
    }

    Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
