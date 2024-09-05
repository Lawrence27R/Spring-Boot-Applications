package com.aurionpro.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.dto.RoleDto;
import com.aurionpro.mappings.service.RoleService;

@RestController
@RequestMapping("/bankApp")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/roles")
    public ResponseEntity<RoleDto> addNewRole(@RequestBody RoleDto roleDto) {
        RoleDto savedRoleDto = roleService.addRole(roleDto);
        return ResponseEntity.ok(savedRoleDto);
    }

    @GetMapping("/roles")
    public ResponseEntity<PageResponseDto<RoleDto>> getAllRoles(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {

        PageResponseDto<RoleDto> response = roleService.getAllRoles(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/role/{roleId}")
//    public ResponseEntity<RoleDto> getRoleById(@PathVariable int roleId) {
//        return roleService.getRoleById(roleId)
//                .map(role -> ResponseEntity.ok(roleService.toRoleDtoMapper(role)))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
