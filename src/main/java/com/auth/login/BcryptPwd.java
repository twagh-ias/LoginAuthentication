package com.auth.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPwd {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodedPassword3 = passwordEncoder.encode("kpmanager");
        System.out.println(encodedPassword3);
    }
}
