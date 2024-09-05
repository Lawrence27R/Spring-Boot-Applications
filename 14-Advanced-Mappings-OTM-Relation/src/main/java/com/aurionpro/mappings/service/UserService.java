package com.aurionpro.mappings.service;

import java.util.Set;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.UserDto;

public interface UserService {
    UserDto addUser(UserDto userDto);
    PageResponseDto<UserDto> getAllUsers(int pageNumber, int pageSize);
    UserDto getUserById(int userId);
    UserDto assignRolesToUser(int userId, Set<Integer> roleIds);
}
