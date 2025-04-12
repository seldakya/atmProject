package com.example.atmProject;

import com.example.atmProject.model.User;
import com.example.atmProject.repository.UserRepository;
import com.example.atmProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest // Spring context'i başlatır
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Tek instance ile testleri yürüt
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void deposit_shouldUpdateBalance() {

        User user = new User();
        user.setName("Elon");
        user.setEmail("Elon1@hotmail.com");
        user.setPassword("abc");
        user.setBalance(100.0);
        user = userRepository.save(user);

        userService.paraYatirma(user.getId(), 150.0);

        // THEN - bakiye güncellenmiş olmalı
        User updated = userRepository.findById(user.getId()).get();
        assertEquals(250.0, updated.getBalance());
    }
}
