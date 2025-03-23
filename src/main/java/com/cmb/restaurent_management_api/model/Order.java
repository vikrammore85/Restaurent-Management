package com.cmb.restaurent_management_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private String customerName;
    private String customerNotes;
    private String status; // Accepted, Preparing, Ready for Pickup, Cancelled
    private String cancellationReason;
    private LocalDateTime orderDate;
    private double price;
    private LocalDateTime orderTime;
    private int preparationTime;
    @Column(nullable = false)
    private Integer quantity;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> reviews;

//    @ManyToOne
//    @JoinColumn(name = "delivery_partner_id")
//    private DeliveryPartner deliveryPartner;
}
