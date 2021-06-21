package com.tw.academy.banking;

import com.tw.banking.Account;
import com.tw.banking.Printer;
import com.tw.banking.Transaction;
import com.tw.banking.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class AccountTest {

    private TransactionRepository transactionRepository;
    private Printer printer;
    private Account account;

    @BeforeEach
    public void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        printer = Mockito.mock(Printer.class);
        account = new Account(transactionRepository, printer);
    }

    @Test
    public void should_account_add_100_when_deposit() {
        //given
        int amount = 100;
        //when
        account.deposit(amount);
        //then
        Mockito.verify(transactionRepository, Mockito.times(1)).addDeposit(amount);
    }

    @Test
    public void should_account_desc_100_when_withdraw() {
        //given
        int amount = 100;
        //when
        account.withdraw(amount);
        //then
        Mockito.verify(transactionRepository, Mockito.times(1)).addWithdraw(amount);
    }


    @Test
    public void should_print_statement_when_call_method() {
        //given
        List<Transaction> transactionList = new ArrayList<>();
        Mockito.when(transactionRepository.allTransactions()).thenReturn(transactionList);
        //when
        account.printStatement();
        //then
        Mockito.verify(printer, Mockito.times(1)).print(transactionList);
    }
}