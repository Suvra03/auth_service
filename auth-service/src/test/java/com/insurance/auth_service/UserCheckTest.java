package com.insurance.auth_service;

import com.insurance.auth_service.entity.UserAuth;
import com.insurance.auth_service.repository.UserAuthRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserCheckTest {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Test
    public void checkUser() {
        System.out.println("USER_CHECK_START");
        Optional<UserAuth> user = userAuthRepository.findByEmail("test-customer@bank.com");
        if (user.isPresent()) {
            System.out.println("User Found: " + user.get().getEmail());
            System.out.println("Hash: " + user.get().getPasswordHash());
            System.out.println("Status: " + user.get().getAccountStatus());
        } else {
            System.out.println("User NOT Found!");
        }
        System.out.println("USER_CHECK_END");
    }
}
