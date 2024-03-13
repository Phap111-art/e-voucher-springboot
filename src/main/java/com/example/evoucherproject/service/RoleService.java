package com.example.evoucherproject.service;

import com.example.evoucherproject.model.entity.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getRolesByRoleIds(Set<Integer> roleIds);
}
