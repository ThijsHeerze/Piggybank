package com.testing.piggybank;

import com.testing.piggybank.account.AccountController;
import com.testing.piggybank.account.AccountService;
import com.testing.piggybank.account.GetAccountsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class AccountIntegrationTest {
    @Autowired
    private AccountController accountController;
    @Autowired
    private AccountService accountService;

    @Test
    public void testAccountController() {
        ResponseEntity<GetAccountsResponse> result = accountController.getAccounts(1);

        Assertions.assertEquals(1, result.getBody().getAccounts().size());
    }

//    @Test
//    public void testGetAccount() {
//        ResponseEntity<GetAccountsResponse> result = accountService.getAccounts(1);
//
//        Assertions.assertEquals(1, result.getBody().getAccounts().size());
//    }
}
