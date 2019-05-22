package com.bookimage.service;

import com.bookimage.model.User;
import com.bookimage.persistence.UserRepository;
import com.bookimage.validator.EmailValidator;
import com.bookimage.validator.ValidationResult;
import com.bookimage.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("userService")
class DefaultUserService implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final Validator<User> userValidator;

    @Autowired
    DefaultUserService(UserRepository userRepository, Validator<User> userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional
            .map(user -> new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user)))
            .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
    }

    private List<GrantedAuthority> getAuthority(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Transactional
    @Override
    public void createUser(User user) {
        final ValidationResult validationResult = userValidator.validate(user);
        if (validationResult.hasError()) {
            throw new IllegalStateException(validationResult.getErrorMessage());
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("Email already in use");
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.findById(user.getId())
            .map(u -> {
                final ValidationResult validationResult = userValidator.validate(user);
                if (validationResult.hasError()) {
                    throw new IllegalStateException(validationResult.getErrorMessage());
                }
                u.setName(user.getName());
                u.setPassword(user.getPassword());
                userRepository.save(u);
                return u;
            })
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
