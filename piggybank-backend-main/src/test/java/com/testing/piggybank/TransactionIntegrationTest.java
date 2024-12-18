package com.testing.piggybank;

import com.testing.piggybank.account.AccountController;
import com.testing.piggybank.account.AccountService;
import com.testing.piggybank.account.GetAccountsResponse;
import com.testing.piggybank.model.Account;
import com.testing.piggybank.model.Currency;
import com.testing.piggybank.model.Transaction;
import com.testing.piggybank.transaction.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class TransactionIntegrationTest {
    @Autowired
    private TransactionController transactionController;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    void beforeEach() {
        transactionRepository.deleteAll();
    }

    @Test
    void createTransaction() {
        CreateTransactionRequest request = new CreateTransactionRequest();
        request.setCurrency(Currency.EURO);
        request.setReceiverAccountId(1L);
        request.setSenderAccountId(2L);
        request.setDescription("Integration test transaction");
        request.setAmount(new BigDecimal(100));

        transactionController.createTransaction(request);

        List<Transaction> result = transactionRepository.findAllByReceiverAccount_Id(1);
        Assertions.assertEquals(1, result.size());
    }

//@ExtendWith(MockitoExtension.class)
//public class TransactionIntegrationTest {
//
//    @Mock
//    TransactionRepository transactionRepository;
//
//    @InjectMocks
//    private TransactionService transactionService;
//
//    @Test
//    public void test_filter() {
//        TransactionService ts = new TransactionService(null, null, null);
//
//        List<Transaction> result = ts.filterAndLimitTransactions(Collections.emptyList(), 1L, 10); {
//            TransactionService service = new TransactionService(transactionRepository, null, null);
//
//        Assertions.assertEquals(0, result.size());
//        }
//    }
//
//    @Test
//    public void test_filterTransaction() {
//        TransactionService ts = new TransactionService(null, null, null);
//        Transaction transaction = new Transaction();
//        List<Transaction> result = ts.filterAndLimitTransactions(Collections.emptyList(), 1L, 10); {
//        Account account = new Account();
//        account.setId(1);
//        List<Transaction> transactionList = List.of(transaction);
//        Assertions.assertEquals(0, result.size());
//        }
//    }
//
//    @Test
//    public void test_filterTransactionNoLimit() {
//        TransactionService ts = new TransactionService(null, null, null);
//        Transaction transaction = new Transaction();
//        List<Transaction> result = ts.filterAndLimitTransactions(Collections.emptyList(), 1L, 0); {
//            Account account = new Account();
//            account.setId(1);
//            List<Transaction> transactionList = List.of(transaction);
//            Assertions.assertEquals(0, result.size());
//        }
//    }
}
