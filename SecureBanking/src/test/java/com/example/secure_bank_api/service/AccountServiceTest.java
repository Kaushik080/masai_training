package com.example.secure_bank_api.service;

import com.example.secure_bank_api.model.Account;
import com.example.secure_bank_api.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.util.Optional;

class AccountServiceTest {
    @Test
    void balanceOk() {
        AccountRepository repo = Mockito.mock(AccountRepository.class);
        Account a = new Account();
        a.setBalance(new BigDecimal("100.00"));
        Mockito.when(repo.findByUserUsername("alice")).thenReturn(Optional.of(a));
        AccountService s = new AccountService(repo);
        Assertions.assertEquals(new BigDecimal("100.00"), s.balance("alice"));
    }
    @Test
    void withdrawOk() {
        AccountRepository repo = Mockito.mock(AccountRepository.class);
        Account a = new Account();
        a.setBalance(new BigDecimal("100.00"));
        Mockito.when(repo.findByUserUsername("alice")).thenReturn(Optional.of(a));
        AccountService s = new AccountService(repo);
        BigDecimal after = s.withdraw("alice", new BigDecimal("30.00"));
        Assertions.assertEquals(new BigDecimal("70.00"), after);
    }
}
