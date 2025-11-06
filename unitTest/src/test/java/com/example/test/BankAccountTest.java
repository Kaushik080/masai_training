package com.example.test;

import com.example.BankAccount;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
        System.out.println("Fresh BankAccount created with balance: " + account.getBalance());
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed, final balance: " + account.getBalance());
        account = null;
    }

    @Nested
    class ConstructorTests {

        @Test
        void testDefaultConstructor() {
            BankAccount newAccount = new BankAccount();
            assertEquals(0.0, newAccount.getBalance(), 0.001);
        }

        @Test
        void testConstructorWithInitialBalance() {
            BankAccount newAccount = new BankAccount(100.0);
            assertEquals(100.0, newAccount.getBalance(), 0.001);
        }

        @Test
        void testConstructorWithNegativeBalance() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new BankAccount(-50.0)
            );
            assertEquals("Initial balance cannot be negative", exception.getMessage());
        }

        @Test
        void testConstructorWithZeroBalance() {
            BankAccount newAccount = new BankAccount(0.0);
            assertEquals(0.0, newAccount.getBalance(), 0.001);
        }
    }

    @Nested
    class DepositTests {

        @Test
        void testDepositPositiveAmount() {
            double initialBalance = account.getBalance();
            double depositAmount = 50.0;
            account.deposit(depositAmount);
            assertEquals(initialBalance + depositAmount, account.getBalance(), 0.001);
        }

        @Test
        void testMultipleDeposits() {
            account.deposit(25.0);
            account.deposit(75.0);
            account.deposit(100.0);
            assertEquals(200.0, account.getBalance(), 0.001);
        }

        @Test
        void testDepositSmallAmounts() {
            account.deposit(0.01);
            assertEquals(0.01, account.getBalance(), 0.001);
        }


        @Test
        void testValidWithdrawal() {
            BankAccount newAccount = new BankAccount(100.0);
            account.withdraw(40.0);
            assertEquals(60.0, account.getBalance(), 0.001);
        }

        @Test
        void testWithdrawExactBalance() {
            BankAccount newAccount = new BankAccount(100.0);
            account.withdraw(100.0);
            assertEquals(0.0, account.getBalance(), 0.001);
        }

        @Test
        void testWithdrawZeroAmountThrows() {
            BankAccount newAccount = new BankAccount(100.0);
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> account.withdraw(0.0)
            );
            assertEquals("Withdrawal amount must be positive", ex.getMessage());
        }

        @Test
        void testWithdrawNegativeAmountThrows() {
            BankAccount newAccount = new BankAccount(100.0);
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> account.withdraw(-50.0)
            );
            assertEquals("Withdrawal amount must be positive", ex.getMessage());
        }

        @Test
        void testWithdrawMoreThanBalanceThrows() {
            BankAccount newAccount = new BankAccount(100.0);
            IllegalStateException ex = assertThrows(
                    IllegalStateException.class,
                    () -> account.withdraw(150.0)
            );
            assertEquals("Insufficient funds", ex.getMessage());
        }
    }
}

