package com.aurionpro.mappings.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private int userId;
    private String username;
    private String password;
    private Set<RoleDto> roles;
}
