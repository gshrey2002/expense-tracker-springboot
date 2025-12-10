package com.example.expensetracker.expense_tracker.repository;

import java.util.List;
// import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetracker.expense_tracker.entity.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findByUserId(UUID userId);

}
