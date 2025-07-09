package com.meusprojetos.catalogo.produtos.api.service;

import com.meusprojetos.catalogo.produtos.api.dto.AdminUpdateUserDTO;
import com.meusprojetos.catalogo.produtos.api.dto.UserRequestDTO;
import com.meusprojetos.catalogo.produtos.api.dto.UserResponseDTO;
import com.meusprojetos.catalogo.produtos.api.entity.User;
import com.meusprojetos.catalogo.produtos.api.enums.UserRole;
import com.meusprojetos.catalogo.produtos.api.exception.EmailAlreadyExistsException;
import com.meusprojetos.catalogo.produtos.api.exception.UserNotFoundException;
import com.meusprojetos.catalogo.produtos.api.mapper.UserMapper;
import com.meusprojetos.catalogo.produtos.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        var existingUser = userRepository.findByEmail(userRequestDTO.email());

        if(existingUser != null) {
            throw new EmailAlreadyExistsException("Usuário já existe com o email: " + userRequestDTO.email());
        }

        var passwordHash = passwordEncoder.encode(userRequestDTO.password());

        User user = userMapper.toUserEntity(userRequestDTO);
        user.setPassword(passwordHash);
        user.setRole(UserRole.USER);

        User saved = userRepository.save(user);

        return userMapper.toResponseDTO(saved);
    }

    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return userMapper.toListResponseDTO(users);
    }

    public UserResponseDTO getUserById(Long id) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não existe com id: " + id));

        return userMapper.toResponseDTO(existingUser);
    }

    public UserResponseDTO updateUser(Long id, AdminUpdateUserDTO adminUpdateUserDTO) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não existe com id: " + id));

        User user = userMapper.toUserEntity(adminUpdateUserDTO);
        User saved = userRepository.save(user);

        return userMapper.toResponseDTO(saved);
    }

    public void deleteUser(Long id) {
        userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("Usuário não existe com id: " + id));
        userRepository.deleteById(id);
    }
}
