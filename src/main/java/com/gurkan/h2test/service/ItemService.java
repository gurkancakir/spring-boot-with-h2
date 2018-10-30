package com.gurkan.h2test.service;

import com.gurkan.h2test.domain.Item;
import com.gurkan.h2test.domain.ItemAddForm;

public interface ItemService {
    void addItem(ItemAddForm form);
    Iterable<Item> getItems();
    void deleteItemById(long id);
    Item getItemById(long id);
    Item assignItem(String username, long itemId);
}