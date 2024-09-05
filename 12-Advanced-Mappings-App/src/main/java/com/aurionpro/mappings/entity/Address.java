package com.aurionpro.mappings.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "address")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    @Id
    @Column(name = "addressId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Column
    @NotBlank(message = "Building Name is mandatory")
    @Size(min = 2, max = 100, message = "Building Name must be between 2 and 100 characters")
    private String buildingName;

    @Column
    @NotBlank(message = "Area Name is mandatory")
    @Size(min = 2, max = 100, message = "Area Name must be between 2 and 100 characters")
    private String areaName;

    @Column
    @NotBlank(message = "City is mandatory")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "City must contain only letters and spaces")
    private String city;

    @Column
    @NotNull(message = "Pincode is mandatory")
    private int pincode;
}
