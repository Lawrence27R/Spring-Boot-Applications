package com.aurionpro.mappings.service;

import com.aurionpro.mappings.dto.RoleDto;
import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.dto.PageResponseDto;

import java.util.Optional;

public interface RoleService {

    RoleDto addRole(RoleDto roleDto);
    PageResponseDto<RoleDto> getAllRoles(int pageNumber, int pageSize);
    Optional<Role> getRoleById(int roleId);
}
