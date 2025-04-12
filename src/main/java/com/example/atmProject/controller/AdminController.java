package com.example.atmProject.controller;

import com.example.atmProject.model.Transaction;
import com.example.atmProject.model.User;
import com.example.atmProject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private AdminService adminService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return adminService.getAllUsers();
    }

    @GetMapping("/user-transactions/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable Long userId){
        return adminService.getUserTransactions(userId);
    }

    @GetMapping("/user-balance/{userId}")
    public Double getUserBalange(@PathVariable Long userId){
        return adminService.getUserBalange(userId);
    }

    @GetMapping("/all-transaction")
    public List<Transaction> getAllTransaction(){
        return adminService.getAllTransaction(null);  // şu an tüm işlemleri getiriyor
    }
}
