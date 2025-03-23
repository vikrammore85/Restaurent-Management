package com.cmb.restaurent_management_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class DeliveryPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactNumber;

//    @OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Order> orders;
}
