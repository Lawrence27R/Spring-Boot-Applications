package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.UserDto;
import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.repository.RoleRepository;
import com.aurionpro.mappings.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = toUserMapper(userDto);
        return toUserDtoMapper(userRepository.save(user));
    }

    @Override
    public PageResponseDto<UserDto> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepository.findAll(pageable);

        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userPage.getContent()) {
            userDtoList.add(toUserDtoMapper(user));
        }

        PageResponseDto<UserDto> userPageDto = new PageResponseDto<>();
        userPageDto.setTotalPages(userPage.getTotalPages());
        userPageDto.setTotalElements(userPage.getTotalElements());
        userPageDto.setSize(userPage.getSize());
        userPageDto.setContents(userDtoList);
        userPageDto.setLastPage(userPage.isLast());

        return userPageDto;
    }

    @Override
    public UserDto getUserById(int userId) {
    	User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return toUserDtoMapper(user);
    }

    @Override
    public UserDto assignRolesToUser(int userId, Set<Integer> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Set<Role> rolesToAdd = new HashSet<>();

        roleIds.forEach(roleId -> {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new EntityNotFoundException("Role not found"));
            rolesToAdd.add(role);
        });

        user.getRoles().addAll(rolesToAdd);

        User updatedUser = userRepository.save(user);

        return toUserDtoMapper(updatedUser);
    }


    private UserDto toUserDtoMapper(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    private User toUserMapper(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        return user;
    }
}
