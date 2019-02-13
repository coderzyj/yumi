package com.lambo.service.user;

import com.lambo.entity.Role;
import com.lambo.entity.User;
import com.lambo.repository.RoleRepository;
import com.lambo.repository.UserRepository;
import com.lambo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author : 张勇杰
 * @date : 2019/2/13 14:38
 * @Version : v1.0
 * @description
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User findUserByName(String userName) {
        User user = userRepository.findByName(userName);
        if(user == null){
            return null;
        }
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if(roles == null || roles.isEmpty()) {
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }
}
