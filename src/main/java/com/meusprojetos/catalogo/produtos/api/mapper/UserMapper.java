package com.meusprojetos.catalogo.produtos.api.mapper;

import com.meusprojetos.catalogo.produtos.api.dto.AdminUpdateUserDTO;
import com.meusprojetos.catalogo.produtos.api.dto.UserRequestDTO;
import com.meusprojetos.catalogo.produtos.api.dto.UserResponseDTO;
import com.meusprojetos.catalogo.produtos.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUserEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "id", ignore = true)
    User toUserEntity(AdminUpdateUserDTO adminUpdateUserDTO);

    UserResponseDTO toResponseDTO(User user);

    List<UserResponseDTO> toListResponseDTO(List<User> users);


}
