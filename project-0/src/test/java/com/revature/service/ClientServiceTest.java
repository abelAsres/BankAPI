package com.revature.service;

import com.revature.dao.ClientDao;
import com.revature.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        mockClients.add(new Client(1,"Seyour","Butz"));
        mockClients.add(new Client(2,"Anita-Amanda","Huginkiss"));
        mockClients.add(new Client(3,"Ivana","Tinkle"));
        mockClients.add(new Client(4,"Olaf","Marifrend-Sergei"));


        // Whenever the code in the Service layer calls the getAllStudents() method
        // for the dao layer, then return the list of students
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


        Assertions.assertEquals(expectedClients,actualClients);
    }
}
