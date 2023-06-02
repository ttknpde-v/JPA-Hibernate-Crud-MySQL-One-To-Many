package com.ttknp.springbootcrudonetomany.secure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecure {
    /* three roles , 1 user , 2 admin , 3 manager */
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails MR_A =
                User.withUsername("MR.A")
                .password("{noop}12345")
                .roles("USER")
                .build();
        /* User can access reads && read */
        UserDetails MR_B =
                User.withUsername("MR.B")
                        .password("{noop}12345")
                        .roles("ADMIN")
                        .build();
        /* User and Admin can access reads && read && update */
        UserDetails MR_C =
                User.withUsername("MR.C")
                        .password("{noop}12345")
                        .roles("USER","ADMIN","MANAGER")
                        .build();
        return new InMemoryUserDetailsManager(MR_A,MR_B,MR_C);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/api/read-only-user-name/**").hasAnyRole("USER","ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/reads").hasAnyRole("ADMIN","MANAGER")
                .requestMatchers(HttpMethod.GET,"/api/reads-sort-income").hasAnyRole("ADMIN","MANAGER")
                .requestMatchers(HttpMethod.POST,"/api/create").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/update/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/delete/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/api/delete-product/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/api/delete-products-by-price/**").hasRole("MANAGER")
                        .anyRequest().authenticated();

        httpSecurity.formLogin().disable();
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();

        return httpSecurity.build();

    }
}
