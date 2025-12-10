package com.example.expensetracker.expense_tracker.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.expensetracker.dto.CreateAccountRequest;
import com.example.expensetracker.expense_tracker.entity.Account;
import com.example.expensetracker.expense_tracker.entity.User;
import com.example.expensetracker.expense_tracker.repository.AccountRepository;
import com.example.expensetracker.expense_tracker.repository.UserRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository,
            UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account createAccount(CreateAccountRequest req) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = Account.builder()
                .name(req.getName())
                .type(req.getType())
                .currency(req.getCurrency())
                .balance(0.0) // default balance
                .userId(user.getId())
                .build();

        return accountRepository.save(account);

    }

}
