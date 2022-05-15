package com.example.bootstrap3_1_3.dao;

import com.example.bootstrap3_1_3.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
     List<Role> getAllRoles();

    Set<Role> getRoleById(Integer[] role_id);
    void saveRole(Role role);
    Role getRole(String name);
}
