package com.example.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {
    private final PasswordEncoder encoder;
    DefaultSecurityConfig(){
        encoder = new BCryptPasswordEncoder();
    }
    //Esto para que register pueda ser publico y el resto de rutas privadas
    //Bean es para decirle a springboot va a "tener en cuenta " el siguiente método
    @Bean
    public SecurityFilterChain routesFilter(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(
            (requests) -> requests.requestMatchers(HttpMethod.POST,"/register").permitAll()
            .requestMatchers(HttpMethod.POST,"/csrf").permitAll()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return this.encoder;
    }
    /*@Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
        .username("nombrecorreo@correo.com")
        .password(this.encoder.encode("secret"))
        .roles("ROLE_ADMIN")
        .build();
        return new InMemoryUserDetailsManager(user);
    }*/

}
