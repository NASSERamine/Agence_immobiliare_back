package com.agence.repository;

import com.agence.entity.Property;
import com.agence.entity.PropertyStatus;
import com.agence.entity.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {
    List<Property> findByPropertyType(PropertyType type);
    List<Property> findByPropertyStatus(PropertyStatus status);
    List<Property> findByCity(String city);
    List<Property> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Property> findByBedrooms(Integer bedrooms);
    List<Property> findByUser_Id(Long userId);
} 