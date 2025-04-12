package com.example.atmProject.service;

import com.example.atmProject.model.Transaction;
import com.example.atmProject.model.User;
import com.example.atmProject.repository.TransactionRepository;
import com.example.atmProject.repository.UserRepository;


import java.util.List;

public class AdminService {

    private UserRepository userRepository;
    private TransactionRepository transactionRepository;

    //Sistemdeki tüm kullanıcıları görüntüleyebilmeli
    public List<User> getAllUsers(){
       return userRepository.findAll();
    }

    //Bir Kullanıcının İşlem Geçmişini Görme
    public List<Transaction> getUserTransactions(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return transactionRepository.findByUser(user);
    }

    //Bir Kullanıcının Bakiye Bilgisi
    public Double getUserBalange(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return user.getBalance();
    }
    //Tüm işem geçmişi
    public List<Transaction> getAllTransaction(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return transactionRepository.findAll();
    }
}
