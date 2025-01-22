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
        // Arrange: Header met gebruikers-ID
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-User-Id", "1"); // Zorg ervoor dat userId 1 bestaat in de testdata

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Act: Ophalen van accounts
        ResponseEntity<GetAccountsResponse> response = restTemplate
                .exchange("/api/v1/accounts", org.springframework.http.HttpMethod.GET, requestEntity, GetAccountsResponse.class);

        // Assert: Controleer responsstatus en inhoud
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().getAccounts().isEmpty(), "Er moeten accounts beschikbaar zijn.");
    }

    @Test
    public void testGetAccounts_Unauthorized() {
        // Arrange: Geen gebruikers-ID meegegeven
        HttpEntity<Void> requestEntity = new HttpEntity<>(null);

        // Act: Ophalen van accounts zonder geldige header
        ResponseEntity<GetAccountsResponse> response = restTemplate
                .exchange("/api/v1/accounts", org.springframework.http.HttpMethod.GET, requestEntity, GetAccountsResponse.class);

        // Assert: Controleer op foutstatus
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdateAccount_Success() {
        // Arrange
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
        updateAccountRequest.setAccountId(1L);
        updateAccountRequest.setAccountName("Updated Account Name");

        HttpEntity<UpdateAccountRequest> requestEntity = new HttpEntity<>(updateAccountRequest);

        // Act
        ResponseEntity<Void> response = restTemplate
                .exchange("/api/v1/accounts", org.springframework.http.HttpMethod.PUT, requestEntity, Void.class);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    public void testUpdateAccount_BadRequest_InvalidPayload() {
//        // Arrange
//        String invalidRequestBody = "{ \"accountName\": \"Updated Account Name\" }"; // Missing accountId
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json");
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(invalidRequestBody, headers);
//
//        // Act
//        ResponseEntity<Void> response = restTemplate
//                .exchange("/api/v1/accounts", org.springframework.http.HttpMethod.PUT, requestEntity, Void.class);
//
//        // Assert
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    }
}
