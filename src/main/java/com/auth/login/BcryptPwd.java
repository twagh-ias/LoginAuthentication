package com.auth.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPwd {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("admin");
        System.out.println(encodedPassword);

        String encodedPassword1 = passwordEncoder.encode("employee");
        System.out.println(encodedPassword1);
    }
}
