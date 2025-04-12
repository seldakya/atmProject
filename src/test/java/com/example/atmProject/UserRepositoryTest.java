package com.example.atmProject;

import com.example.atmProject.model.User;
import com.example.atmProject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    public UserRepository userRepository;


    @Test
    public void whenSaveUserThenFindById(){

        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password123");

        User savedUser = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("John Doe");

    }
    @Test
    void whenFindByEmailthenReturnUser(){

        User user = new User();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setPassword("Alice123");

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        assertThat(foundUser.get().getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    void whenUserNotExists_thenReturnEmpty() {
        // When
        Optional<User> foundUser = userRepository.findByEmail("notexists@example.com");

        // Then
        assertThat(foundUser).isEmpty();
    }
}
