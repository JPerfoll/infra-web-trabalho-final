package br.edu.unidavi.infrawebtrabalhofinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws
    Exception {    
        auth
        .inMemoryAuthentication()
        .withUser("jean")
        .password("perfoll")
        .roles("MANAGER")
        .and()
        .withUser("rodrigo")
        .password("candido")
        .roles("USER", "MANAGER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/produtos/**")
        .hasAnyRole("MANAGER")
        .anyRequest()
        .fullyAuthenticated()
        .and()
        .httpBasic()
        .and()
        .csrf()
        .disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}