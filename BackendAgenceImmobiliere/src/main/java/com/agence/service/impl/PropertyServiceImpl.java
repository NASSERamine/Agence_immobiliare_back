package com.agence.service.impl;

import com.agence.dto.PropertyDTO;
import com.agence.entity.Property;
import com.agence.entity.PropertyImage;
import com.agence.entity.PropertyStatus;
import com.agence.entity.PropertyType;
import com.agence.entity.User;
import com.agence.exception.ResourceNotFoundException;
import com.agence.mapper.PropertyMapper;
import com.agence.repository.PropertyRepository;
import com.agence.repository.UserRepository;
import com.agence.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDTO createProperty(PropertyDTO propertyDTO) {
        Property property = PropertyMapper.INSTANCE.toEntity(propertyDTO);
        
        User user = userRepository.findById(propertyDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + propertyDTO.getUserId()));
        property.setUser(user);

        if (propertyDTO.getImageUrls() != null) {
            propertyDTO.getImageUrls().forEach(imageUrl -> {
                PropertyImage image = new PropertyImage();
                image.setImageUrl(imageUrl);
                image.setProperty(property);
                property.getImages().add(image);
            });
        }

        Property savedProperty = propertyRepository.save(property);
        return PropertyMapper.INSTANCE.toDTO(savedProperty);
    }

    @Override
    public PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO) {
        Property existingProperty = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));

        existingProperty.setTitle(propertyDTO.getTitle());
        existingProperty.setDescription(propertyDTO.getDescription());
        existingProperty.setPrice(propertyDTO.getPrice());
        existingProperty.setAddress(propertyDTO.getAddress());
        existingProperty.setCity(propertyDTO.getCity());
        existingProperty.setState(propertyDTO.getState());
        existingProperty.setZipCode(propertyDTO.getZipCode());
        existingProperty.setCountry(propertyDTO.getCountry());
        existingProperty.setBedrooms(propertyDTO.getBedrooms());
        existingProperty.setBathrooms(propertyDTO.getBathrooms());
        existingProperty.setArea(propertyDTO.getArea());
        existingProperty.setPropertyType(PropertyType.valueOf(propertyDTO.getPropertyType()));
        existingProperty.setPropertyStatus(PropertyStatus.valueOf(propertyDTO.getStatus()));

        if (propertyDTO.getUserId() != null && !propertyDTO.getUserId().equals(existingProperty.getUser().getId())) {
            User user = userRepository.findById(propertyDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + propertyDTO.getUserId()));
            existingProperty.setUser(user);
        }

        if (propertyDTO.getImageUrls() != null) {
            existingProperty.getImages().clear();
            propertyDTO.getImageUrls().forEach(imageUrl -> {
                PropertyImage image = new PropertyImage();
                image.setImageUrl(imageUrl);
                image.setProperty(existingProperty);
                existingProperty.getImages().add(image);
            });
        }

        Property updatedProperty = propertyRepository.save(existingProperty);
        return PropertyMapper.INSTANCE.toDTO(updatedProperty);
    }

    @Override
    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Property not found with id: " + id);
        }
        propertyRepository.deleteById(id);
    }

    @Override
    public PropertyDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
        return PropertyMapper.INSTANCE.toDTO(property);
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll().stream()
                .map(PropertyMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> getPropertiesByType(PropertyType type) {
        return propertyRepository.findByPropertyType(type).stream()
                .map(PropertyMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> getPropertiesByStatus(PropertyStatus status) {
        return propertyRepository.findByPropertyStatus(status).stream()
                .map(PropertyMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> getPropertiesByCity(String city) {
        return propertyRepository.findByCity(city).stream()
                .map(PropertyMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> getPropertiesByPriceRange(Double minPrice, Double maxPrice) {
        return propertyRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .map(PropertyMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> getPropertiesByBedrooms(Integer bedrooms) {
        return propertyRepository.findByBedrooms(bedrooms).stream()
                .map(PropertyMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> getPropertiesByUserId(Long userId) {
        return propertyRepository.findByUser_Id(userId).stream()
                .map(PropertyMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
} 