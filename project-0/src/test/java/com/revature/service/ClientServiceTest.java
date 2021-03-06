package com.revature.service;

import com.revature.dao.ClientDao;
import com.revature.exception.ClientAlreadyExistsException;
import com.revature.exception.ClientNotFoundException;
import com.revature.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class ClientServiceTest {
    @Test
    public void testGetAllClients() throws SQLException {
        //Mock creation
        ClientDao mockClientDao = mock(ClientDao.class);

        List<Client> mockClients = new ArrayList<>();
        mockClients.add(new Client(1, "Seyour", "Butz"));
        mockClients.add(new Client(2, "Anita-Amanda", "Huginkiss"));
        mockClients.add(new Client(3, "Ivana", "Tinkle"));
        mockClients.add(new Client(4, "Olaf", "Marfried-Sergei"));


        // Whenever the code in the Service layer calls the getAllStudents() method
        // for the dao layer, then return the list of clients
        // we have specified above

        //stubbing
        when(mockClientDao.getAllClients()).thenReturn(mockClients);

        ClientService clientService = new ClientService(mockClientDao);

        //Act
        List<Client> actualClients = clientService.getAllClients();

        //verification
        verify(mockClientDao).getAllClients();

        //Assert
        List<Client> expectedClients = new ArrayList<>(mockClients);
        Assertions.assertEquals(expectedClients, actualClients);
    }

    @Test
    public void testGetClientById() throws Exception {
        //Arrange
        ClientDao mockClientDao = mock(ClientDao.class);
        Client expectedClient = new Client(1, "Hugh", "Jazz");
        ClientService clientService = new ClientService(mockClientDao);

        //Act
        //stubbing
        when(mockClientDao.getClientByid(1)).thenReturn(expectedClient);
        Client actualClient = clientService.getClientById("1");

        //Assert
        verify(mockClientDao).getClientByid(1);
        Assertions.assertEquals(expectedClient, actualClient);
    }
    @Test
    public void test_getUpdateById_clientDoesNotExist () throws SQLException, ClientNotFoundException {
        //Arrange
        ClientDao mockClientDao = mock(ClientDao.class);
        ClientService clientService = new ClientService(mockClientDao);
        Client clientToUpdate = new Client(2,"Hugh","Bass");


        //Act+Assertion
        Assertions.assertThrows(ClientNotFoundException.class,()->{
            clientService.updateClient("1",clientToUpdate);
        });
    }

    @Test
    public void test_getClientById_clientDoesNotExist () throws SQLException, ClientNotFoundException {
        //Arrange
        ClientDao mockClientDao = mock(ClientDao.class);
        ClientService clientService = new ClientService(mockClientDao);

        //Act+Assertion
        Assertions.assertThrows(ClientNotFoundException.class,()->{
            clientService.getClientById("100");
        });
    }


}
    /*@Test
    public void testUpdateClient() throws SQLException {
        //Arrange
        ClientDao mockClientDao = mock(ClientDao.class);
        Client client = new Client(1,"Hugh","Jazz");

        client.setLast_name("Bass");

        Client updatedClient = new Client(2,"Hugh","Bass");
        ClientService clientService = new ClientService(mockClientDao);

        when(mockClientDao.updateClient(client)).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return client.equals(updatedClient);
            }
        });

    @Test
    public void test_addClient_clientAlreadyInDatabase(){
        //Arrange
        ClientDao mockClientDao = mock(ClientDao.class);
        ClientService clientService = new ClientService(mockClientDao);
        Client client = new Client(1,"Bart","Simpson");

        //Act+Assertion
        Assertions.assertThrows(ClientAlreadyExistsException.class,()->{
            clientService.addClient(client);
        });
    }

    */

