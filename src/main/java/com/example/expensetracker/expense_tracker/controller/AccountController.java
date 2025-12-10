package com.example.expensetracker.expense_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.dto.CreateAccountRequest;
import com.example.expensetracker.expense_tracker.entity.Account;
// import com.example.expensetracker.expense_tracker.service.AccountService;
import com.example.expensetracker.expense_tracker.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @Valid CreateAccountRequest req) {
        Account created = accountService.createAccount(req);
        return ResponseEntity.ok(created);
    }
}
