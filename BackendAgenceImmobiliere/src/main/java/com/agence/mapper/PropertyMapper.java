package com.agence.mapper;

import com.agence.dto.PropertyDTO;
import com.agence.entity.Property;
import com.agence.entity.PropertyStatus;
import com.agence.entity.PropertyType;
import com.agence.entity.PropertyImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "propertyType", source = "propertyType", qualifiedByName = "propertyTypeToString")
    @Mapping(target = "status", source = "propertyStatus", qualifiedByName = "propertyStatusToString")
    @Mapping(target = "state", source = "country")
    @Mapping(target = "imageUrls", expression = "java(mapImages(property.getImages()))")
    PropertyDTO toDTO(Property property);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "propertyType", source = "propertyType", qualifiedByName = "stringToPropertyType")
    @Mapping(target = "propertyStatus", source = "status", qualifiedByName = "stringToPropertyStatus")
    @Mapping(target = "country", source = "state")
    Property toEntity(PropertyDTO propertyDTO);

    @Named("propertyTypeToString")
    default String propertyTypeToString(PropertyType type) {
        return type != null ? type.name() : null;
    }

    @Named("propertyStatusToString")
    default String propertyStatusToString(PropertyStatus status) {
        return status != null ? status.name() : null;
    }

    @Named("stringToPropertyType")
    default PropertyType stringToPropertyType(String type) {
        return type != null ? PropertyType.valueOf(type) : null;
    }

    @Named("stringToPropertyStatus")
    default PropertyStatus stringToPropertyStatus(String status) {
        return status != null ? PropertyStatus.valueOf(status) : null;
    }

    default List<String> mapImages(List<PropertyImage> images) {
        if (images == null) {
            return null;
        }
        return images.stream()
                .map(PropertyImage::getImageUrl)
                .collect(Collectors.toList());
    }
} 