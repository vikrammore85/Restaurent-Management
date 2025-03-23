package com.cmb.restaurent_management_api.repository;

import com.cmb.restaurent_management_api.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository  extends JpaRepository<MenuItem, Long> {
}
