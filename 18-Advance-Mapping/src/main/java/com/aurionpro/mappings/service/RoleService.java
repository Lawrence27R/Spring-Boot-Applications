package com.aurionpro.mappings.service;

import java.util.Optional;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.RoleDto;
import com.aurionpro.mappings.entity.Role;

public interface RoleService {

    RoleDto addRole(RoleDto roleDto);
    PageResponseDto<RoleDto> getAllRoles(int pageNumber, int pageSize);
    Optional<Role> getRoleById(int roleId);
}
