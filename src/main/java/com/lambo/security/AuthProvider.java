package com.lambo.security;

import com.lambo.entity.User;
import com.lambo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author : 张勇杰
 * @date : 2019/2/13 14:32
 * @Version : v1.0
 * @description  自定义认证接口
 **/
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private IUserService userService;

    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String inputPassWord = (String) authentication.getCredentials();

        User user = userService.findUserByName(userName);
        if(user == null){
            throw new AuthenticationCredentialsNotFoundException("authError");
        }
        if(this.passwordEncoder.isPasswordValid(user.getPassword(),inputPassWord,user.getId())){
            return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        }
        throw new BadCredentialsException("authError");

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
