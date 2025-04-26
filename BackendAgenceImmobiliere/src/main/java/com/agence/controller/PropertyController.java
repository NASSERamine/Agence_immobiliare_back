package com.agence.controller;

import com.agence.dto.PropertyDTO;
import com.agence.entity.PropertyStatus;
import com.agence.entity.PropertyType;
import com.agence.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO propertyDTO) {
        return ResponseEntity.ok(propertyService.createProperty(propertyDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO propertyDTO) {
        return ResponseEntity.ok(propertyService.updateProperty(id, propertyDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByType(@PathVariable PropertyType type) {
        return ResponseEntity.ok(propertyService.getPropertiesByType(type));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByStatus(@PathVariable PropertyStatus status) {
        return ResponseEntity.ok(propertyService.getPropertiesByStatus(status));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByCity(@PathVariable String city) {
        return ResponseEntity.ok(propertyService.getPropertiesByCity(city));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return ResponseEntity.ok(propertyService.getPropertiesByPriceRange(minPrice, maxPrice));
    }

    @GetMapping("/bedrooms/{bedrooms}")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByBedrooms(@PathVariable Integer bedrooms) {
        return ResponseEntity.ok(propertyService.getPropertiesByBedrooms(bedrooms));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(propertyService.getPropertiesByUserId(userId));
    }
} 