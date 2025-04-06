package com.pulseofthepeople.service.serviceIMPL;

import com.pulseofthepeople.dto.UserForm;
import com.pulseofthepeople.entity.Role;
import com.pulseofthepeople.entity.User;
import com.pulseofthepeople.repository.RoleRepository;
import com.pulseofthepeople.repository.UserRepository;
import com.pulseofthepeople.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl(UserRepository theUserRepository, RoleRepository theRoleRepository , PasswordEncoder passwordEncoder){
        this.userRepository = theUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = theRoleRepository;
    }


    @Override
    public String saveUser(UserForm userDTO) {
        Role defaultRole = roleRepository.findByRole("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER"))); // Get existing role or create one

        User user = new User(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()), //remember this friend haha
                "",
                "",
                "",
                true,
                Arrays.asList(defaultRole));
        userRepository.save(user);
        return "User Saved Successfully!";
    }
}
