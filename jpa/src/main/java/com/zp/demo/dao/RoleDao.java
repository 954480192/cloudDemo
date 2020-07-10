package com.zp.demo.dao;

import com.zp.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,String> {
}
