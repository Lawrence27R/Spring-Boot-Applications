package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.RoleDto;
import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Override
    public RoleDto addRole(RoleDto roleDto) {
        Role role = toRoleMapper(roleDto);
        
        RoleDto newRoleDto = toRoleDtoMapper(roleRepository.save(role));
        
        logger.info("Role added successfully.");
        return newRoleDto;
    }

    @Override
    public PageResponseDto<RoleDto> getAllRoles(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Role> rolePage = roleRepository.findAll(pageable);
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role : rolePage.getContent()) {
            roleDtoList.add(toRoleDtoMapper(role));
        }

        PageResponseDto<RoleDto> rolePageDto = new PageResponseDto<>();
        rolePageDto.setTotalPages(rolePage.getTotalPages());
        rolePageDto.setTotalElements(rolePage.getTotalElements());
        rolePageDto.setSize(rolePage.getSize());
        rolePageDto.setContents(roleDtoList);
        rolePageDto.setLastPage(rolePage.isLast());

        return rolePageDto;
    }

    @Override
    public Optional<Role> getRoleById(int roleId) {
        return roleRepository.findById(roleId);
    }

    private RoleDto toRoleDtoMapper(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRolename(role.getRolename());
        return roleDto;
    }

    private Role toRoleMapper(RoleDto roleDto) {
        Role role = new Role();
        role.setRolename(roleDto.getRolename());
        return role;
    }
}
