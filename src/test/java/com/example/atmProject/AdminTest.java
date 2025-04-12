package com.example.atmProject;

import com.example.atmProject.model.Admin;
import com.example.atmProject.model.User;
import org.junit.jupiter.api.Test;

public class AdminTest {

    @Test
        public void main() {
            Admin admin = new Admin();
            admin.setUsername("Test");
            System.out.println("Surname: " + admin.getUsername());
        }

    @Test
    public void main2() {
        User u = new User();
        u.setBalance(500.0);
        System.out.println(u.getBalance());

    }
}
