package com.gurkan.h2test.controller;

import com.gurkan.h2test.domain.ItemAddForm;
import com.gurkan.h2test.domain.ItemAssignForm;
import com.gurkan.h2test.service.ItemService;
import com.gurkan.h2test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ItemController {

    @Autowired
    private final ItemService itemService;
    @Autowired
    private final UserService userService;

    public ItemController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @RequestMapping("/items/add")
    public ModelAndView itemAddPage() {
        return new ModelAndView("addItem", "itemForm", new ItemAddForm());
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public String handleItemAdd(@Valid @ModelAttribute("itemForm") ItemAddForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "addItem";
        itemService.addItem(form);
        return "redirect:/items";
    }

    @RequestMapping("/items")
    public ModelAndView getItemsPage() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("items", itemService.getItems());
        model.put("usernames", userService.getUsernames());
        model.put("assignForm", new ItemAssignForm());
        return new ModelAndView("items", model);
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
    public String handleItemAssign(@ModelAttribute("user") ItemAssignForm form, @PathVariable("id") long id) {
        itemService.assignItem(form.getUsername(), id);
        return "redirect:/items";
    }

}