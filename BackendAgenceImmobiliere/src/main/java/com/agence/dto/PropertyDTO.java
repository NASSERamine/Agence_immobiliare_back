package com.agence.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PropertyDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double area;
    private String propertyType;
    private String status;
    private List<String> imageUrls;
    private Long userId;
} 