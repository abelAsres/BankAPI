package com.revature.controller;

import com.revature.model.Client;
import com.revature.service.ClientService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class ClientController implements Controller{
    ClientService clientService = new ClientService();

    // This lambda will implicitly have "throws Exception" based on the functional interface
    // This is something to be aware of, because you might actually want to handle some exceptions
    private Handler getAllClients = ctx -> {
        ctx.json(clientService.getAllClients());
    };

    private Handler getClientById = ctx -> {
        ctx.json(clientService.getClientById(ctx.pathParam("clientId")));
    };

    private Handler addClient = ctx -> {
        Client clientToBeAdded = ctx.bodyAsClass(Client.class);
        Client addedClient = clientService.addClient(clientToBeAdded);
        ctx.status(201);
        ctx.json(addedClient);
    };

    private Handler updateClient = ctx -> {
        Client clientToBeUpdated = ctx.bodyAsClass(Client.class);
        Client updatedClient = clientService.updateClient(ctx.pathParam("id"),clientToBeUpdated);
        ctx.status(200);
        ctx.json(updatedClient);
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.get("/clients",getAllClients);
        app.get("/clients/{clientId}",getClientById);
        app.post("/clients/",addClient);
        app.put("/clients/{clientId}",updateClient);
    }
}
