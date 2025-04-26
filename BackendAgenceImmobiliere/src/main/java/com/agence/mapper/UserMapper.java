package com.agence.mapper;

import com.agence.dto.UserDTO;
import com.agence.entity.User;
import com.agence.entity.Role;
import com.agence.entity.ERole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    UserDTO toDTO(User user);

    @Mapping(target = "roles", ignore = true)
    User toEntity(UserDTO userDTO);

    default List<String> mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());
    }

    default Set<Role> mapRoles(List<String> roles) {
        return roles.stream()
                .map(role -> {
                    Role r = new Role();
                    r.setName(ERole.valueOf(role));
                    return r;
                })
                .collect(Collectors.toSet());
    }
} 