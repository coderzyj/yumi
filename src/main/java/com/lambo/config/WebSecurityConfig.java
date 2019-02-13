package com.lambo.config;

import com.lambo.security.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author : 张勇杰
 * @date : 2019/2/12 16:26
 * @Version : v1.0
 * @description
 **/
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * HTTP权限控制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //管理员登录入口
                .antMatchers("/admin/login").permitAll()
                //静态资源
                .antMatchers("/static/**").permitAll()
                //用户登录入口
                .antMatchers("/user/login").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN","USER")

                .antMatchers("/api/user/**").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin()
                //配置角色登陆处理入口
                //.loginPage("/admin/login")
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/page")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and();
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 自定义认证策略
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin").password("admin")
        //        .roles("ADMIN").and();
        //擦除密码
        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }
    @Bean
    public AuthProvider authProvider(){
        return new AuthProvider();
    }
}
