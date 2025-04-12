package com.example.atmProject;

import com.example.atmProject.model.Transaction;
import com.example.atmProject.model.User;
import com.example.atmProject.repository.TransactionRepository;
import com.example.atmProject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

/*
    @Test
    void whenSaveTransaction_thenFindByUser(){

        User user = new User();
        user.setName("Jasper");
        user.setEmail("jasper@example.com");
        user.setPassword("Jasper123");

        userRepository.save(user);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionType("WITHDRAW");
        transaction.setAmount(500.0);
        transactionRepository.save(transaction);


        List<Transaction> foundTransction = transactionRepository.findByUser(user);
        assertThat(foundTransction).isNotEmpty();
        assertThat(foundTransction.get().getAmount()).isEqualTo(500.0);
    }*/
}
