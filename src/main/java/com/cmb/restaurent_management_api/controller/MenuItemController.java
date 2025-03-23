package com.cmb.restaurent_management_api.controller;

import com.cmb.restaurent_management_api.exception.ResourceNotFoundException;
import com.cmb.restaurent_management_api.model.MenuItem;
import com.cmb.restaurent_management_api.service.MenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private static final Logger logger = LoggerFactory.getLogger(MenuItemController.class);

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        logger.info("Fetching all menu items");
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        logger.info("Successfully fetched {} menu items", menuItems.size());
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Fetching menu item with ID: {}", id);
        MenuItem menuItem = menuItemService.getMenuItemById(id);
        logger.info("Successfully fetched menu item: {}", menuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        logger.info("Creating a new menu item: {}", menuItem);
        MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);
        logger.info("Successfully created menu item with ID: {}", createdMenuItem.getId());
        return new ResponseEntity<>(createdMenuItem, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) throws ResourceNotFoundException {
        logger.info("Updating menu item with ID: {}", id);
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(id, menuItem);
        logger.info("Successfully updated menu item: {}", updatedMenuItem);
        return new ResponseEntity<>(updatedMenuItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Deleting menu item with ID: {}", id);
        menuItemService.deleteMenuItem(id);
        logger.info("Successfully deleted menu item with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
