package com.speakout.speakoutapi.security;

import com.speakout.speakoutapi.user.ApplicationUser;
import com.speakout.speakoutapi.user.ApplicationUserRepository;
import com.speakout.speakoutapi.user_role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ApplicationUserRepository applicationUserRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUser = applicationUserRepository.findByEmail(username);

        ApplicationUser user = applicationUser.orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(user.getUsername(), 
                        user.getPassword(),
                        user.isEnabled(),
                        user.isAccountNonExpired(), 
                        user.isCredentialsNonExpired(), 
                        user.isAccountNonLocked(),
                        user.getAuthorities());
    }

    public static Set<GrantedAuthority> convertAuthorities(Set<Role> userRoles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName().toString())));
        return authorities;
    }
}