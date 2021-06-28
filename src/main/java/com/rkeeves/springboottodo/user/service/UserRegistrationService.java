package com.rkeeves.springboottodo.user.service;

import com.rkeeves.springboottodo.user.dto.UserRegisterDTO;
import com.rkeeves.springboottodo.user.entity.User;
import com.rkeeves.springboottodo.user.exception.UserAlreadyExistException;
import com.rkeeves.springboottodo.user.repository.RoleRepository;
import com.rkeeves.springboottodo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRegistrationService {

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public void registerNewUser(UserRegisterDTO dto) throws UserAlreadyExistException {
        if (userRepository.findByUserName(dto.getUserName()).isPresent()) {
            throw new UserAlreadyExistException(dto.getUserName());
        }
        var user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setActive(true);
        roleRepository.findByName("ROLE_USER").ifPresent(userRole->{
            user.getRoles().add(userRole);
        });
        userRepository.save(user);
    }
}
