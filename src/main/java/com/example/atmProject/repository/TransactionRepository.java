package com.example.atmProject.repository;

import com.example.atmProject.model.Transaction;
import com.example.atmProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser(User user);
}
