package com.cmb.restaurent_management_api.service;

import com.cmb.restaurent_management_api.exception.ResourceNotFoundException;
import com.cmb.restaurent_management_api.model.MenuItem;
import com.cmb.restaurent_management_api.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MenuServiceImpl implements MenuItemService {

    private static final Logger logger = Logger.getLogger(MenuServiceImpl.class.getName());



    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItem> getAllMenuItems() {
        logger.info("Fetching all menu items");
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem getMenuItemById(Long id) throws ResourceNotFoundException {
        logger.info("Fetching menu item with id: " + id);
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found with id: " + id));
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        logger.info("Creating new menu item: " + menuItem.getName());
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem menuItemDetails) throws ResourceNotFoundException {
        logger.info("Updating menu item with id: " + id);
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found with id: " + id));

        menuItem.setName(menuItemDetails.getName());
        menuItem.setDescription(menuItemDetails.getDescription());
        menuItem.setPrice(menuItemDetails.getPrice());
        menuItem.setCategory(menuItemDetails.getCategory());
        menuItem.setImageUrl(menuItemDetails.getImageUrl());
        menuItem.setAvailabilityStatus(menuItemDetails.isAvailabilityStatus());
        menuItem.setPreparationTime(menuItemDetails.getPreparationTime());

        return menuItemRepository.save(menuItem);
    }

    @Override
    public void deleteMenuItem(Long id) throws ResourceNotFoundException {
        logger.info("Deleting menu item with id: " + id);
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found with id: " + id));
        menuItemRepository.delete(menuItem);
    }
}
