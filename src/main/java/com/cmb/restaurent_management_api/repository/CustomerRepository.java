package com.cmb.restaurent_management_api.repository;

import com.cmb.restaurent_management_api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
