package com.example.evoucherproject.config;

import com.example.evoucherproject.auth.handlers.CustomAccessDeniedHandler;
import com.example.evoucherproject.auth.handlers.JwtSessionStorageLogoutHandler;
import com.example.evoucherproject.auth.jwt.JwtAuthenticationFilter;
import com.example.evoucherproject.auth.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.example.evoucherproject.enums.RoleType;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    @Value("${user_to_access_url}")
    private String[] UserAccessUrls;
    @Value("${authority_to_access_url}")
    private String[] authorityToAccessUrls;
    @Value("${default_success_url}")
    private String successUrls;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService detailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public LogoutHandler jwtSessionStorageLogoutHandler() {
        return new JwtSessionStorageLogoutHandler();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(detailsService());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(UserAccessUrls[0], UserAccessUrls[1]).permitAll()
                        .requestMatchers(authorityToAccessUrls[0]).hasAuthority(RoleType.USER.name())
                        .requestMatchers(authorityToAccessUrls[1]).hasAuthority(RoleType.ADMIN.name())
                        .anyRequest().authenticated()
                ).exceptionHandling(config -> config.accessDeniedHandler(accessDeniedHandler()))
                .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl(successUrls)
                ).httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                        .logoutSuccessUrl("/login")
                        .addLogoutHandler(jwtSessionStorageLogoutHandler())
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
