package com.gurkan.h2test.service;

import com.gurkan.h2test.domain.Item;
import com.gurkan.h2test.domain.User;
import com.gurkan.h2test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
     userRepository.save(user);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<String>();
        Iterator iterator = getUsers().iterator();
        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findOne(id);
    }
    public Map<String, List<Item>> numberOfItemsByType(long userId) {
        Map<String, List<Item>> map = new HashMap<String, List<Item>>();
        Set<Item> items = getUserById(userId).getItems();
        for (Item item: items) {
            List<Item> itemList = new ArrayList<Item>();
            String key = item.getType().toLowerCase();
            if (map.containsKey(key))
                itemList = map.get(key);
            itemList.add(item);
            map.put(key, itemList);
        }
        return map;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("User with username: " + username + " not found.");
        } else {
            return user;
        }
    }
}
