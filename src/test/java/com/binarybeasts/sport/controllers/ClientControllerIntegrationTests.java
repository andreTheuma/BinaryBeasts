package com.binarybeasts.sport.controllers;

import com.binarybeasts.sport.models.Client;
import com.binarybeasts.sport.models.ClientRequest;
import com.binarybeasts.sport.models.ClientResponse;
import com.binarybeasts.sport.services.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.constraints.Pattern;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ClientControllerIntegrationTests {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ClientService clientMockService;

    @Test
    public void testCreateValidClient() throws JsonProcessingException, JSONException {
        ClientRequest clientRequest = new ClientRequest("123456L", "Joe Borg", 19, "joeborg@gmail.com", "79123456");

        // UUID?
        ClientResponse expectedClientResponse = new ClientResponse(1L, "123456L", "Joe Borg", 19, "joeborg@gmail.com", "79123456");

        String expectedResponseBody = om.writeValueAsString(expectedClientResponse);

        String endpoint = "/clients";

        Client serviceClient = new Client(1L, "123456L", "Joe Borg", 19, "joeborg@gmail.com", "79123456");
        serviceClient.setIsActive(true);

        when(clientMockService.saveClient(any(Client.class))).thenReturn(Optional.of(serviceClient));

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, clientRequest, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // we do a string comparison and not an object comparison
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);
    }

    @Test
    public void testCreateUnderAgeClient() {
        ClientRequest clientRequest = new ClientRequest("123456L", "Joe Borg", 0, "joeborg@gmail.com", "79123456");

        String endpoint = "/clients";

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, clientRequest, String.class);


        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateOverAgeClient() {
        ClientRequest clientRequest = new ClientRequest("123456L", "Joe Borg", 123, "joeborg@gmail.com", "79123456");

        String endpoint = "/clients";

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, clientRequest, String.class);


        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testClientInvalidEmail() {
        ClientRequest clientRequest = new ClientRequest("123456L", "Joe Borg", 19, "joeborggmail.com", "79123456");

        String endpoint = "/clients";

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, clientRequest, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testClientInvalidMobileNumber() {
        ClientRequest clientRequest = new ClientRequest("123456L", "Joe Borg", 19, "joeborg@gmail.com", "345");

        String endpoint = "/clients";

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, clientRequest, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testClientEmptyFullName() {
        ClientRequest clientRequest = new ClientRequest("123456L", "", 19, "joeborg@gmail.com", "79123456");

        String endpoint = "/clients";

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, clientRequest, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testClientLongFullName() {
        ClientRequest clientRequest = new ClientRequest("123456L", "My full name is Joe Borg and my middle name is Francis." +
                "You probably do not know me, because there are many people named Joe Borg around Malta.", 19, "joeborg@gmail.com", "79123456");

        String endpoint = "/clients";

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, clientRequest, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testClientInvalidId() {
        ClientRequest clientRequest = new ClientRequest("123456", "Joe Borg", 19, "joeborg@gmail.com", "79123456");

        String endpoint = "/clients";

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, clientRequest, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
