package com.generali.jwtauthbackend.service;

import com.generali.jwtauthbackend.constant.RoleConstant;
import com.generali.jwtauthbackend.entity.Role;
import com.generali.jwtauthbackend.entity.User;
import com.generali.jwtauthbackend.exception.ResourceConflictException;
import com.generali.jwtauthbackend.exception.ResourceNotFoundException;
import com.generali.jwtauthbackend.payload.JsonWebTokenResponse;
import com.generali.jwtauthbackend.payload.RequestLogin;
import com.generali.jwtauthbackend.payload.RequestRegister;
import com.generali.jwtauthbackend.repository.RoleRepository;
import com.generali.jwtauthbackend.repository.UserRepository;
import com.generali.jwtauthbackend.security.JwtTokenProvider;
import com.generali.jwtauthbackend.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public JsonWebTokenResponse login(RequestLogin reqLogin){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(reqLogin.getUsername(), reqLogin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtTokenProvider.generateToken(auth);

        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(role -> ((GrantedAuthority) role).getAuthority())
                .collect(Collectors.toList());


        return new JsonWebTokenResponse(jwt, userPrincipal.getId(),
                userPrincipal.getUsername(), userPrincipal.getEmail(), roles);
    }

    public void register(RequestRegister reqRegister){


        if (userRepository.findUserByEmail(reqRegister.getEmail()).isPresent()){
            throw new ResourceConflictException("email with "+reqRegister.getEmail()+" is already taken");
        }

        if (userRepository.findUserByUsername(reqRegister.getUsername()).isPresent()){
            throw new ResourceConflictException("username with "+reqRegister.getUsername()+ " is already taken");
        }

        Set<String> strRoles = reqRegister.getRoles();
        Set<Role> roles = new HashSet<>();

        /** check if roles is null then add role staff **/
        if (strRoles == null){
            Role role = roleRepository.findRoleByName(RoleConstant.ROLE_STAFF)
                    .orElseThrow(() -> new ResourceNotFoundException("role with "+
                            RoleConstant.ROLE_STAFF+ " is not found"));
            roles.add(role);
        }else{

            strRoles.forEach(getRole -> {
                switch (getRole){
                    case "admin":

                        Role roleAdmin = roleRepository.findRoleByName(RoleConstant.ROLE_ADMIN)
                                .orElseThrow(() -> new ResourceNotFoundException("role with "+
                                        RoleConstant.ROLE_ADMIN+" is not found"));
                        roles.add(roleAdmin);
                        break;
                    case "staff":

                        Role roleStaff = roleRepository.findRoleByName(RoleConstant.ROLE_STAFF)
                                .orElseThrow(() -> new ResourceNotFoundException("role with "+
                                        RoleConstant.ROLE_STAFF+" is not found"));
                        roles.add(roleStaff);
                        break;
                    default:
                        Role roleManagers = roleRepository.findRoleByName(RoleConstant.ROLE_MANAGER)
                                .orElseThrow(() -> new ResourceNotFoundException("role with "+
                                        RoleConstant.ROLE_MANAGER+" is not found"));
                        roles.add(roleManagers);

                }
            });

        }

        User user = User.builder()
                .name(reqRegister.getName())
                .username(reqRegister.getUsername())
                .email(reqRegister.getEmail())
                .password(passwordEncoder.encode(reqRegister.getPassword()))
                .roles(roles)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        log.info("###SAVE### : "+user);
        userRepository.save(user);
    }
}
