package com.cafe.com.cafe.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

// this spring SecurityConfig implementin video-2
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomerUsersDetailsService customerUserDetailsService;

    @Autowired
    JwtFilter jwtFilter;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // authenticates username and password, implements authentication logic
    // uses Service to find username and validates password using Encoder
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(customerUserDetailsService);
        auth.setPasswordEncoder(bCryptPasswordEncoder());
        return auth;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return NoOpPasswordEncoder.getInstance();
    // }

    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues().setAllowedMethods())
//
//                .and()
//                .csrf().disable()
//                // by pass all requests with the following paths
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/user/login/**").permitAll()
//                        .requestMatchers("/user/signup/**").permitAll()
//                        .requestMatchers("/user/forgotPassword/**").permitAll()
//                        .anyRequest().authenticated() // otherwise, require authentication
//                )
//                .exceptionHandling()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authenticationProvider(authenticationProvider());
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(request -> {
                    CorsConfiguration corsConfig = new CorsConfiguration();
                    corsConfig.applyPermitDefaultValues();
                    corsConfig.addAllowedMethod(HttpMethod.PUT);
                    corsConfig.addAllowedMethod(HttpMethod.PATCH);
                    corsConfig.addAllowedMethod(HttpMethod.DELETE);
                    return corsConfig;
                })
                .and()
                .csrf().disable()
                // bypass all requests with the following paths
                .authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/user/login/**").permitAll()
                                .requestMatchers("/user/signup/**").permitAll()
                                .requestMatchers("/user/forgotPassword/**").permitAll()
                                .anyRequest().authenticated() // otherwise, require authentication
                )
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
