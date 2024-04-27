package ru.kata.spring.boot_bootstrap.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kata.spring.boot_bootstrap.demo.services.UserServiceImpl;


@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = "ru.kata.spring.boot_bootstrap.demo")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserServiceImpl userService;

    @Autowired
    public WebSecurityConfig(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/page", true)
//                .successHandler(successUserHandler)
//                .permitAll()
                .failureUrl("/login?error")
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(getPasswordEncoder());
    }
    // Если ментор просит encode сделать без привязки к сервису, создать для него бин.
    // Обязательн в WebSecurityConfig, метод BCryptPasswordEncoder делайте static.
    // Мелкое замечание, которое сэкономит вам время
    @Bean
    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}