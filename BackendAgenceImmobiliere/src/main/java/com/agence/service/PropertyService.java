package com.agence.service;

import com.agence.dto.PropertyDTO;
import com.agence.entity.PropertyStatus;
import com.agence.entity.PropertyType;

import java.util.List;

public interface PropertyService {
    PropertyDTO createProperty(PropertyDTO propertyDTO);
    PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO);
    void deleteProperty(Long id);
    PropertyDTO getPropertyById(Long id);
    List<PropertyDTO> getAllProperties();
    List<PropertyDTO> getPropertiesByType(PropertyType type);
    List<PropertyDTO> getPropertiesByStatus(PropertyStatus status);
    List<PropertyDTO> getPropertiesByCity(String city);
    List<PropertyDTO> getPropertiesByPriceRange(Double minPrice, Double maxPrice);
    List<PropertyDTO> getPropertiesByBedrooms(Integer bedrooms);
    List<PropertyDTO> getPropertiesByUserId(Long userId);
} 