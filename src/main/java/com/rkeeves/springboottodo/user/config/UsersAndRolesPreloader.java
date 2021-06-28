package com.rkeeves.springboottodo.user.config;

import com.rkeeves.springboottodo.user.entity.Role;
import com.rkeeves.springboottodo.user.entity.User;
import com.rkeeves.springboottodo.user.repository.RoleRepository;
import com.rkeeves.springboottodo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersAndRolesPreloader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    private final  RoleRepository roleRepository;

    private final  UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var adminRole = createOrGetRoleByName("ROLE_ADMIN",roleRepository);
        var userRole = createOrGetRoleByName("ROLE_USER",roleRepository);
        var admin = userRepository.findByUserName("admin").orElseGet(()->{
            var user = new User();
            user.setUserName("admin");
            user.setActive(true);
            user.setPassword(passwordEncoder.encode("admin"));
            user.getRoles().addAll(List.of(adminRole, userRole));
            return userRepository.save(user);
        });
    }

    private Role createOrGetRoleByName(String name, RoleRepository roleRepository){
        return roleRepository.findByName(name).orElseGet(()->{
            var role = new Role();
            role.setName(name);
            return roleRepository.save(role);
        });
    }
}
