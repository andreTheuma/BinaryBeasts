package com.binarybeasts.sport.services;

import com.binarybeasts.sport.models.Client;
import com.binarybeasts.sport.models.ClientEntity;
import com.binarybeasts.sport.respositories.ClientRepository;
import io.swagger.models.Model;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

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
    public void testSaveUserUnder14() {
        Client clientToBeSaved = new Client(1L, "123456L", "Joe Borg", 13, "joeborg@gmail.com", "79123456");

        Client expectedSavedClient = new Client(1L, "123456L", "Joe Borg", 13, "joeborg@gmail.com", "79123456");
        expectedSavedClient.setIsActive(false);

        ClientEntity outputClientEntity = new ClientEntity(1L, "123456L", "Joe Borg", 13, "joeborg@gmail.com", "79123456", false);
        when(clientMockRepository.save(any(ClientEntity.class))).thenReturn(outputClientEntity);

        Optional<Client> savedClient = clientService.saveClient(clientToBeSaved);

        assertFalse(savedClient.isEmpty());
        Assertions.assertThat(savedClient.get()).isEqualToComparingFieldByField(expectedSavedClient);

        verify(clientMockRepository, times(1)).save(any(ClientEntity.class));
    }

    @Deprecated
    @Test
    public void testSaveUserOver14() {
        Client clientToBeSaved = new Client(1L, "123456L", "Joe Borg", 14, "joeborg@gmail.com", "79123456");

        Client expectedSavedClient = new Client(1L, "123456L", "Joe Borg", 14, "joeborg@gmail.com", "79123456");
        expectedSavedClient.setIsActive(true);

        ClientEntity outputClientEntity = new ClientEntity(1L, "123456L", "Joe Borg", 14, "joeborg@gmail.com", "79123456", true);
        when(clientMockRepository.save(any(ClientEntity.class))).thenReturn(outputClientEntity);

        Optional<Client> savedClient = clientService.saveClient(clientToBeSaved);

        assertFalse(savedClient.isEmpty());
        Assertions.assertThat(savedClient.get()).isEqualToComparingFieldByField(expectedSavedClient);

        verify(clientMockRepository, times(1)).save(any(ClientEntity.class));
    }

    @Deprecated
    @Test
    void testGetUserByIdValidId() {
        //Arrange
        ClientEntity newClientEntity = new ClientEntity(1L,"123456L", "Joe Borg", 14, "joeborg@gmail.com", "79123456", true);
        when(clientMockRepository.findById(1L)).thenReturn(Optional.of(newClientEntity));

        Client expectedUser = modelMapper.map(newClientEntity, Client.class);

        Optional<Client> actualClient = clientService.getClientById(1L);

        //Assert
        assertFalse(actualClient.isEmpty());
        Assertions.assertThat(actualClient.get()).isEqualToComparingFieldByField(expectedUser);
        verify(clientMockRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserByIdInvalidId() {
        // Arrange
        when(clientMockRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Client> returnedUser = clientService.getClientById(1L);

        // Assert
        assertTrue(returnedUser.isEmpty());

        verify(clientMockRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteUserByIdValidId() {
        // Act
        clientService.deleteClientById(1L);

        // Assert
        verify(clientMockRepository, times(1)).deleteById(1L);
    }

    @Deprecated
    @Test
    void testGetAllUsersValidUsers() {
        //Arrange
        List<ClientEntity> clientsList = List.of(
                new ClientEntity("123456L", "Joe Borg", 14, "joeborg@gmail.com", "79123456", true),
                new ClientEntity("123457L", "Mary Borg", 24, "maryborg@gmail.com", "79123457", true)
        );

        when(clientMockRepository.findAll()).thenReturn(clientsList);

        Client expectedClient1 = new Client("123456L", "Joe Borg", 14, "joeborg@gmail.com", "79123456");
        Client expectedClient2 = new Client("123457L", "Mary Borg", 24, "maryborg@gmail.com", "79123457");

        List<Client> actualClientsList = clientService.getAllClients();

        // Assert
        assertThat(actualClientsList)
                .extracting(Client::getId, Client::getFullName, Client::getAge, Client::getEmail, Client::getMobileNumber)
                .containsExactly(
                        tuple(expectedClient1.getId(), expectedClient1.getFullName(), expectedClient1.getAge(), expectedClient1.getEmail(), expectedClient1.getMobileNumber()),
                        tuple(expectedClient2.getId(), expectedClient2.getFullName(), expectedClient2.getAge(), expectedClient2.getEmail(), expectedClient2.getMobileNumber())
                );
        verify(clientMockRepository, times(1)).findAll();
    }

}
