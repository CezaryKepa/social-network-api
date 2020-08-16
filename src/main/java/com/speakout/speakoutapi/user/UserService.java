package com.speakout.speakoutapi.user;

import com.speakout.speakoutapi.role.Role;
import com.speakout.speakoutapi.role.RoleEnum;
import com.speakout.speakoutapi.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private ApplicationUserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(ApplicationUserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void addWithDefaultRole(ApplicationUser user) {
        Optional<ApplicationUser> userFind = userRepository.findByEmail(user.getEmail());

        if (userFind.isPresent())
            throw new UserDuplicateException();

        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER);
        user.getRoles().add(userRole);
        String passwordHash = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
//        TODO
//        sendToken(user);
    }
}
