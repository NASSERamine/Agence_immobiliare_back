package com.agence.mapper;

import com.agence.dto.PropertyDTO;
import com.agence.entity.Property;
import com.agence.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-26T15:22:10+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class PropertyMapperImpl implements PropertyMapper {

    @Override
    public PropertyDTO toDTO(Property property) {
        if ( property == null ) {
            return null;
        }

        PropertyDTO propertyDTO = new PropertyDTO();

        propertyDTO.setUserId( propertyUserId( property ) );
        propertyDTO.setPropertyType( propertyTypeToString( property.getPropertyType() ) );
        propertyDTO.setStatus( propertyStatusToString( property.getPropertyStatus() ) );
        propertyDTO.setState( property.getCountry() );
        propertyDTO.setId( property.getId() );
        propertyDTO.setTitle( property.getTitle() );
        propertyDTO.setDescription( property.getDescription() );
        propertyDTO.setPrice( property.getPrice() );
        propertyDTO.setAddress( property.getAddress() );
        propertyDTO.setCity( property.getCity() );
        propertyDTO.setZipCode( property.getZipCode() );
        propertyDTO.setCountry( property.getCountry() );
        propertyDTO.setBedrooms( property.getBedrooms() );
        propertyDTO.setBathrooms( property.getBathrooms() );
        propertyDTO.setArea( property.getArea() );

        propertyDTO.setImageUrls( mapImages(property.getImages()) );

        return propertyDTO;
    }

    @Override
    public Property toEntity(PropertyDTO propertyDTO) {
        if ( propertyDTO == null ) {
            return null;
        }

        Property property = new Property();

        property.setUser( propertyDTOToUser( propertyDTO ) );
        property.setPropertyType( stringToPropertyType( propertyDTO.getPropertyType() ) );
        property.setPropertyStatus( stringToPropertyStatus( propertyDTO.getStatus() ) );
        property.setCountry( propertyDTO.getState() );
        property.setId( propertyDTO.getId() );
        property.setTitle( propertyDTO.getTitle() );
        property.setDescription( propertyDTO.getDescription() );
        property.setPrice( propertyDTO.getPrice() );
        property.setAddress( propertyDTO.getAddress() );
        property.setCity( propertyDTO.getCity() );
        property.setState( propertyDTO.getState() );
        property.setZipCode( propertyDTO.getZipCode() );
        property.setBedrooms( propertyDTO.getBedrooms() );
        property.setBathrooms( propertyDTO.getBathrooms() );
        property.setArea( propertyDTO.getArea() );

        return property;
    }

    private Long propertyUserId(Property property) {
        if ( property == null ) {
            return null;
        }
        User user = property.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User propertyDTOToUser(PropertyDTO propertyDTO) {
        if ( propertyDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( propertyDTO.getUserId() );

        return user;
    }
}
