package com.lobotomia.lobotomia.config;

import com.lobotomia.lobotomia.Model.Profile;
import com.lobotomia.lobotomia.Model.RoleEnum;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.lobotomia.lobotomia.Repository.ProfileRepository;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createDefaultUser() {
        if (!profileRepository.existsByUsername("admin")) {
            Profile user = new Profile();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setActive(true);
            user.setRoles(Collections.singleton(RoleEnum.ADMIN));
            profileRepository.save(user);
        }
        if (!profileRepository.existsByUsername("sysadmin")) {
            Profile user = new Profile();
            user.setUsername("sysadmin");
            user.setPassword(passwordEncoder.encode("sysadmin"));
            user.setActive(true);
            user.setRoles(Collections.singleton(RoleEnum.SYSADMIN));
            profileRepository.save(user);
        }
        if (!profileRepository.existsByUsername("manager")) {
            Profile user = new Profile();
            user.setUsername("manager");
            user.setPassword(passwordEncoder.encode("manager"));
            user.setActive(true);
            user.setRoles(Collections.singleton(RoleEnum.MANAGERROLES));
            profileRepository.save(user);
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(login -> {
            Profile user = profileRepository.findByUsername(login);
            if (user == null) {
                throw new UsernameNotFoundException("Такой пользователь не существует!");
            }
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.isActive(),
                    true,
                    true,
                    true,
                    user.getRoles()
            );
        }).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/login", "/reg").permitAll() // Открытые страницы
                        .requestMatchers("/users/**").hasAuthority("ADMIN") // Админ доступ
                        .requestMatchers("/profiles/**").hasAuthority("SYSADMIN")
                        .requestMatchers("/roles/**").hasAuthority("MANAGERROLES")
                        .requestMatchers("/orders/**").hasAuthority("USER") // Пользовательский доступ
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .successHandler(authenticationSuccessHandler()) // Кастомный обработчик
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout")
                                .invalidateHttpSession(true)
                                .permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session ->
                        session
                                .sessionFixation().migrateSession()
                                .invalidSessionUrl("/login?invalid")
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                .maximumSessions(1)
                                .maxSessionsPreventsLogin(true)
                                .expiredUrl("/login?expired")
                );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/h2-console/**");
    }
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (_, response, authentication) -> {
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ADMIN"));
            boolean isUser = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("USER"));
            boolean isSysAdmin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("SYSADMIN"));
            boolean isManager = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("MANAGERROLES"));

            if (isAdmin)
                response.sendRedirect("/users/all");
            else if (isUser)
                response.sendRedirect("/orders/all");
            else if (isSysAdmin)
                response.sendRedirect("/profiles/all");
            else if (isManager)
                response.sendRedirect("/roles/all");
        };
    }
}
