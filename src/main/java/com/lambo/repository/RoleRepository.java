package com.lambo.repository;

import com.lambo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author : 张勇杰
 * @date : 2019/2/13 14:51
 * @Version : v1.0
 * @description  角色数据dao
 **/
public interface RoleRepository extends CrudRepository<Role,Long> {
    List<Role> findRolesByUserId(Long userId);
}
