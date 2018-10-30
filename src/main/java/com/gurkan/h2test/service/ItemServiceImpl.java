package com.gurkan.h2test.service;

import com.gurkan.h2test.domain.Item;
import com.gurkan.h2test.domain.ItemAddForm;
import com.gurkan.h2test.domain.User;
import com.gurkan.h2test.repository.ItemRepository;
import com.gurkan.h2test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemRepository itemRepository;
    @Autowired
    private final UserService userService;

    public ItemServiceImpl(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    public void addItem(ItemAddForm form) {
        for (int i = 0; i < form.getAmount(); i++) {
            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10); //generate random string
            Item item = new Item(inventoryCode, form.getItemType());
            itemRepository.save(item);
            System.out.println(itemRepository.findOne(item.getId()));
        }
    }

    @Override
    public Iterable<Item> getItems() {
        return itemRepository.findAll();
    }
    @Override
    public void deleteItemById(long id) {
        itemRepository.delete(id);
    }

    public Item getItemById(long id) {
        return itemRepository.findOne(id);
    }
    public Item assignItem(String username, long itemId) {
        User user = userService.getUserByUsername(username);
        Item item = getItemById(itemId);
        Set<Item> itemList = user.getItems();
        itemList.add(item);
        user.setItems(itemList);
        item.setUser(user);
        return itemRepository.save(item);
    }

}