package com.cmb.restaurent_management_api.controller;

import com.cmb.restaurent_management_api.exception.ResourceNotFoundException;
import com.cmb.restaurent_management_api.model.MenuItem;
import com.cmb.restaurent_management_api.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return new ResponseEntity<>(menuItemService.getAllMenuItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(menuItemService.getMenuItemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        return new ResponseEntity<>(menuItemService.createMenuItem(menuItem), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) throws ResourceNotFoundException {
        return new ResponseEntity<>(menuItemService.updateMenuItem(id, menuItem), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) throws ResourceNotFoundException {
        menuItemService.deleteMenuItem(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
