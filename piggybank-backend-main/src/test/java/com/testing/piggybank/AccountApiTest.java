package com.testing.piggybank;

import com.testing.piggybank.account.GetAccountsResponse;
import com.testing.piggybank.account.UpdateAccountRequest;
import com.testing.piggybank.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAccounts_Success() {
        // Ophalen van accounts met een  ID.
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-User-Id", "1"); // Zorg ervoor dat userId 1 bestaat in de testdata

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<GetAccountsResponse> response = restTemplate
                .exchange("/api/v1/accounts", org.springframework.http.HttpMethod.GET, requestEntity, GetAccountsResponse.class);

        // Controleer responsstatus en inhoud
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().getAccounts().isEmpty(), "Er moeten accounts beschikbaar zijn.");
    }

    @Test
    public void testGetAccounts_Unauthorized() {
        // Ophalen van accounts zonder ID.
        HttpEntity<Void> requestEntity = new HttpEntity<>(null);

        ResponseEntity<GetAccountsResponse> response = restTemplate
                .exchange("/api/v1/accounts", org.springframework.http.HttpMethod.GET, requestEntity, GetAccountsResponse.class);

        // Controleer op foutstatus
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdateAccount_Success() {
        // Updaten van een account
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
        updateAccountRequest.setAccountId(1L);
        updateAccountRequest.setAccountName("Updated Account Name");

        HttpEntity<UpdateAccountRequest> requestEntity = new HttpEntity<>(updateAccountRequest);
        ResponseEntity<Void> response = restTemplate
                .exchange("/api/v1/accounts", org.springframework.http.HttpMethod.PUT, requestEntity, Void.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}