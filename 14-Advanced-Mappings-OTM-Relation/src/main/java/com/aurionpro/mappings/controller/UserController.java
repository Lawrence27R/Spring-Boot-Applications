package com.aurionpro.mappings.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.UserDto;
import com.aurionpro.mappings.service.UserService;

@RestController
@RequestMapping("/bankApp")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) {
        UserDto savedUserDto = userService.addUser(userDto);
        return ResponseEntity.ok(savedUserDto);
    }
    
    @GetMapping("/users")
    public ResponseEntity<PageResponseDto<UserDto>> getAllUsers(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        PageResponseDto<UserDto> response = userService.getAllUsers(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int userId) {
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/user/roles")
    public ResponseEntity<UserDto> assignRolesToUser(
            @RequestParam int userId,
            @RequestBody Set<Integer> roleIds) {
        UserDto updatedUserDto = userService.assignRolesToUser(userId, roleIds);
        return ResponseEntity.ok(updatedUserDto);
    }
}
