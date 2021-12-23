package com.auth.login.controller;

import java.util.Set;
import java.util.stream.Collectors;

import com.auth.login.exception.DisabledUserException;
import com.auth.login.exception.InvalidUserCredentialsException;
import com.auth.login.service.UserAuthService;
import com.auth.login.util.JwtUtil;
import com.auth.login.vo.JwtRequest;
import com.auth.login.vo.JwtResponse;
import com.auth.login.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtRestController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private AuthenticationManager authenticationManager;

//  sigin api for login user
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> generateJwtToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getUserpwd()));
        } catch (DisabledException e) {
            throw new DisabledUserException("User Inactive");
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException("Invalid Credentials");
        }
        UserDetails userDetails = userAuthService.loadUserByUsername(jwtRequest.getUsername());
        String username = userDetails.getUsername();
        String userpwd = userDetails.getPassword();
        Set<String> roles = userDetails.getAuthorities().stream().map(r -> r.getAuthority())
                .collect(Collectors.toSet());
        UserVo user = new UserVo();
        user.setUsername(username);
        user.setUserpwd(userpwd);
        user.setRoles(roles);
        String token = jwtUtil.generateToken(user);
        return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
    }

//    signup api for user registration
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserVo userVo) {
        UserVo u = userAuthService.getUserByUsername(userVo.getUsername());

        if (u == null) {
            userAuthService.saveUser(userVo);
            return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
        }
    }

}