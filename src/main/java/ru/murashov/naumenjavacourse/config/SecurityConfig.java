package ru.murashov.naumenjavacourse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import ru.murashov.naumenjavacourse.models.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private AccessDeniedHandler accessDeniedHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .antMatchers("/login", "/registration").permitAll()
        .antMatchers("/product/getAll", "/product/get").permitAll()
        .antMatchers("/category/**", "/producer/**", "/user/**", "/product/**").hasRole(Role.ADMIN.name())
        .anyRequest().authenticated()
        .and().formLogin().permitAll()
        .and().logout().permitAll()
        .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    return httpSecurity.build();
  }
}
