package com.cmb.restaurent_management_api.service;

import com.cmb.restaurent_management_api.exception.ResourceNotFoundException;
import com.cmb.restaurent_management_api.model.MenuItem;

import java.util.List;

public interface MenuItemService {

    List<MenuItem> getAllMenuItems();
    MenuItem getMenuItemById(Long id) throws ResourceNotFoundException;
    MenuItem createMenuItem(MenuItem menuItem);
    MenuItem updateMenuItem(Long id, MenuItem menuItem) throws ResourceNotFoundException;
    void deleteMenuItem(Long id) throws ResourceNotFoundException;
}
