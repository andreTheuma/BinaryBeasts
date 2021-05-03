package com.binarybeasts.sport.services;

import com.binarybeasts.sport.models.Client;
import com.binarybeasts.sport.models.ClientEntity;
import com.binarybeasts.sport.respositories.ClientRepository;
import io.swagger.models.Model;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClientEntityServiceTest {

    @MockBean
    ClientRepository clientMockRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    ModelMapper modelMapper;

    // Under 14 --> Inactive
    @Test
    @Deprecated
    public void testSaveUserUnder18() {
        Client clientToBeSaved = new Client(1L, "123456L", "Joe Borg", 13, "joeborg@gmail.com", "79123456");

        Client expectedSavedClient = new Client(1L, "123456L", "Joe Borg", 13, "joeborg@gmail.com", "79123456");
        expectedSavedClient.setIsActive(false);

        ClientEntity outputClientEntity = new ClientEntity(1L, "123456L", "Joe Borg", 13, "joeborg@gmail.com", "79123456", false);
        when(clientMockRepository.save(any(ClientEntity.class))).thenReturn(outputClientEntity);

        Client savedClient = clientService.saveClient(clientToBeSaved);

        assertThat(savedClient).isEqualToComparingFieldByField(expectedSavedClient);

        verify(clientMockRepository, times(1)).save(any(ClientEntity.class));
    }

    @Deprecated
    @Test
    public void testSaveUserOver18() {
        Client clientToBeSaved = new Client(1L, "123456L", "Joe Borg", 14, "joeborg@gmail.com", "79123456");

        Client expectedSavedClient = new Client(1L, "123456L", "Joe Borg", 14, "joeborg@gmail.com", "79123456");
        expectedSavedClient.setIsActive(true);

        ClientEntity outputClientEntity = new ClientEntity(1L, "123456L", "Joe Borg", 14, "joeborg@gmail.com", "79123456", true);
        when(clientMockRepository.save(any(ClientEntity.class))).thenReturn(outputClientEntity);

        Client savedClient = clientService.saveClient(clientToBeSaved);

        assertThat(savedClient).isEqualToComparingFieldByField(expectedSavedClient);

        verify(clientMockRepository, times(1)).save(any(ClientEntity.class));
    }


}
