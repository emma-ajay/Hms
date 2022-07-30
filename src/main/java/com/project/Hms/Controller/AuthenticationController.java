package com.project.Hms.Controller;


import com.project.Hms.DTO.Requests.RegisterRequest;
import com.project.Hms.DTO.Requests.SignInRequest;
import com.project.Hms.DTO.Response.ApiResponse;
import com.project.Hms.DTO.Response.JwtAuthenticationResponse;
import com.project.Hms.Entity.Role;
import com.project.Hms.Entity.RoleName;
import com.project.Hms.Entity.User;
import com.project.Hms.Exceptions.AppException;
import com.project.Hms.Repository.RoleRepository;
import com.project.Hms.Repository.UserRepository;
import com.project.Hms.Security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsernameOrEmail(),
                        signInRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String email = signInRequest.getUsernameOrEmail();
        String jwt =tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,email));
    }

    // Register Student


    @Transactional
    @PostMapping("/register/student")
    public ResponseEntity<? > registerUser(@RequestBody RegisterRequest registerRequest){

        if(userRepository.existsByUserName(registerRequest.getUserName())) {
            return new ResponseEntity(new ApiResponse(false, "Username already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }



        User user = modelMapper.map(registerRequest,User.class);
         String password =passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(password);

        Role userRole = roleRepository.findByName(RoleName.ROLE_STUDENT)
                .orElseThrow(() -> new AppException("User Role Student not set."));

        user.setRoles(Collections.singleton(userRole));



        User result = userRepository.save(user);



        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully",result.getId(),"User (student)"));
    }


    // register porter
    @PostMapping("/register/porter")
    public ResponseEntity<? > registerVendor(@RequestBody RegisterRequest registerRequest){

        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = modelMapper.map(registerRequest,User.class);
        String password =passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(password);

        Role userRole = roleRepository.findByName(RoleName.ROLE_PORTER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully",result.getId(),"User (porter)"));
    }
}
