package com.company.laptop_rest.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password(encoder.encode("password")).roles("USER").build());
        manager.createUser(User.withUsername("admin").password(encoder.encode("password")).roles("USER","ADMIN").build());
        return manager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/saludo").permitAll()
                        .requestMatchers("/api/laptops").hasRole("ADMIN")
                        .anyRequest().authenticated()

                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    //Estamos sobreescribiendo el firewall por defecto de Spring
    public HttpFirewall looseHttpFirewall(){
        StrictHttpFirewall firewall = new StrictHttpFirewall();

        //Configurar firewall aunque lo normal es dejarlo por defecto
        firewall.setAllowBackSlash(true); //no lanzará excepciones si hay una llamada con estos caracteres
        firewall.setAllowSemicolon(true);


        return firewall;
    }
}
