package com.example.atmProject.service;

import com.example.atmProject.model.Transaction;
import com.example.atmProject.model.User;
import com.example.atmProject.repository.TransactionRepository;
import com.example.atmProject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public void paraYatirma(Long userId , Double amount){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);

        //log tutmak için Transaction kaydı oluşturuyoruz.

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setTransactionType("paraYatirma");
        transactionRepository.save(transaction);
    }

    @Transactional
    public void paraCekme(Long userId , Double amount){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(user.getBalance() < amount)
            throw new RuntimeException("Yetersiz Bakiye!");


        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setTransactionType("paraCekme");
        transactionRepository.save(transaction);
    }

    @Transactional
    public void transfer(Long fromUserId, Long toUserId, Double amount) {

        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new RuntimeException("Gönderen user bulunamadı!"));

        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new RuntimeException("Alıcı kullanıcı bulunamadı!"));

        if (fromUser.getBalance() < amount)
            throw new RuntimeException("Yetersiz bakiye.");

        // Bakiyeleri güncelle
        fromUser.setBalance(fromUser.getBalance() - amount);
        toUser.setBalance(toUser.getBalance() + amount);

        userRepository.save(fromUser);
        userRepository.save(toUser);

        // Transaction logları
        Transaction fromTransaction = new Transaction();
        fromTransaction.setUser(fromUser);
        fromTransaction.setAmount(amount);
        fromTransaction.setTransactionType("TRANSFER_TO " + toUser.getEmail());
        transactionRepository.save(fromTransaction);

        Transaction toTransaction = new Transaction();
        toTransaction.setUser(toUser);
        toTransaction.setAmount(amount);
        toTransaction.setTransactionType("TRANSFER_FROM " + fromUser.getEmail());
        transactionRepository.save(toTransaction);
    }

    //Müşterinin tüm işlemlerini döner. Kullanıcının ekranında gösterilecek olan liste buradan gelir.
    public List<Transaction> getUserTransactions(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUser(user);
    }

    // Kullanıcı bakiyesini görmek istediğinde kullanılır. Sadece balance alanını döner.
    public Double getBalance(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getBalance();
    }

}
