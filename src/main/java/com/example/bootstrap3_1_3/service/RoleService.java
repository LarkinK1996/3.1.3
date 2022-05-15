package com.example.bootstrap3_1_3.service;

import com.example.bootstrap3_1_3.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();

    Set getRoleById(Integer[] role_id);

    void saveRole(Role role);
    Role getRole(String name);
}
