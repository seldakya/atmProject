package com.example.atmProject.controller;

import com.example.atmProject.model.Transaction;
import com.example.atmProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/paraYatirma")
    public String paraYatirma(@RequestParam Long userId , @RequestParam Double amount){

        userService.paraYatirma(userId,amount);
        return "Para yatırma işlemi başarılı.";
    }

    @PostMapping("/paraCekme")
    public String paraCekme(@RequestParam Long userId, @RequestParam Double amount){

        userService.paraCekme(userId,amount);
        return "Para çekme işlemi başarılı.";
    }
    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromUserId,@RequestParam Long toUserId,@RequestParam Double amount){

        userService.transfer(fromUserId,toUserId,amount);
        return "Para transfer işlemi başarıyla gerçekleşmiştir";
    }
    @GetMapping("/transactions")
    public List<Transaction> getUserTransactions(@RequestParam Long userId){

        return userService.getUserTransactions(userId);
    }
    @GetMapping("/balance")
    public Double getBalance(@RequestParam Long userId) {

        return userService.getBalance(userId);
    }
}
