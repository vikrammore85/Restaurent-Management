package com.cmb.restaurent_management_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @Positive(message = "Price must be a positive value")
    private double price;

    @NotBlank(message = "Category is mandatory")
    @Size(max = 50, message = "Category must be less than 50 characters")
    private String category;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "availability_status")
    private boolean availabilityStatus;

    @PositiveOrZero(message = "Preparation time must be a positive value or zero")
    @Column(name = "preparation_time")
    private int preparationTime; // in minutes

}
