package com.tw.academy.banking;

import com.tw.banking.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

class PrinterTest {

    private Console console;
    private Printer printer;
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        console = Mockito.mock(Console.class);
        transaction = Mockito.mock(Transaction.class);
        printer = new Printer(console);
    }

    @Test
    public void should_print_statement_when_print() {
        //given
        List<Transaction> transactions = Collections.singletonList(transaction);
        //when
        printer.print(transactions);
        //then
        Mockito.verify(console, Mockito.times(2)).printLine(any());
    }

    @Test
    public void should_print_statement_with_right_format_when_print() {
        //given
        int runningBalance = 1;
        //when
        Mockito.when(transaction.date()).thenReturn("2021-06-21");
        Mockito.when(transaction.amount()).thenReturn(1);
        String result = printer.statementLine(transaction, runningBalance);
        //then
        Assertions.assertEquals(String.format("%s | %s | %s",transaction.date(), transaction.amount(),runningBalance), result);
    }
}
