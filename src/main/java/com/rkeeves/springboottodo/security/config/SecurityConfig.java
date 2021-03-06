package com.rkeeves.springboottodo.security.config;

import com.rkeeves.springboottodo.security.service.JpaBasedUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JpaBasedUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/todo/delete/**").hasRole("ADMIN")
                .antMatchers("/").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin(loginConfig->{
                    loginConfig.loginPage("/login");
                    loginConfig.failureUrl("/login-error");
                    loginConfig.loginProcessingUrl("/process-login");
                })
                .logout(logoutConfig -> {
                    logoutConfig.logoutUrl("/perform-logout");
                    logoutConfig.logoutSuccessUrl("/login");
                    logoutConfig.invalidateHttpSession(true);
                    logoutConfig.deleteCookies("JSESSIONID");
                });
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
