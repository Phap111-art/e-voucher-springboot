package com.example.evoucherproject.service.Impl;

import com.example.evoucherproject.model.entity.Role;
import com.example.evoucherproject.exception.CustomException;
import com.example.evoucherproject.repository.RoleRepository;
import com.example.evoucherproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public Set<Role> getRolesByRoleIds(Set<Integer> roleIds) {
        return roleIds.stream().map(id -> roleRepository.findById(id).orElseGet(() -> {
            throw new CustomException("id " + id + " not found in table role", HttpStatus.NOT_FOUND);
        })).collect(Collectors.toSet());
    }
}
