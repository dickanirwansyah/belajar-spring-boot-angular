package com.generali.jwtauthbackend.security;

import com.generali.jwtauthbackend.constant.AllMessageConstant;
import com.generali.jwtauthbackend.entity.User;
import com.generali.jwtauthbackend.exception.ResourceNotFoundException;
import com.generali.jwtauthbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /** find user by username **/
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByUsername = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username +
                        AllMessageConstant.UsernameNotFoundMessage));

        return UserPrincipal.create(userByUsername);
    }

    @Transactional
    public UserDetails loadUserById(int userId) {
        User userById = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId +
                        AllMessageConstant.UserIdNotfoundMessage));

        return UserPrincipal.create(userById);
    }
}
