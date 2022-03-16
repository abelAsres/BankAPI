package com.revature.controller;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.model.ClientAccount;
import com.revature.service.AccountService;
import com.revature.service.ClientService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class ClientController implements Controller{
    ClientService clientService = new ClientService();
    AccountService accountService= new AccountService();

    // This lambda will implicitly have "throws Exception" based on the functional interface
    // This is something to be aware of, because you might actually want to handle some exceptions
    private Handler getAllClients = ctx -> {
        ctx.json(clientService.getAllClients());
    };

    private Handler getClientById = ctx -> {
        ctx.json(clientService.getClientById(ctx.pathParam("client_id")));
    };

    private Handler addClient = ctx -> {
        Client clientToBeAdded = ctx.bodyAsClass(Client.class);
        Client addedClient = clientService.addClient(clientToBeAdded);
        ctx.status(201);
        ctx.json(addedClient);
    };

    private Handler updateClient = ctx -> {
        Client clientToBeUpdated = ctx.bodyAsClass(Client.class);
        Client updatedClient = clientService.updateClient(ctx.pathParam("client_id"),clientToBeUpdated);
        ctx.status(200);
        ctx.json(updatedClient);
    };

    private Handler removeClient = ctx -> {
        ctx.json(clientService.removeClient(ctx.pathParam("client_id")));
    };



    private Handler getAccountsByClientId = ctx -> {
        String clientId = ctx.pathParam("client_id");
        Client client = clientService.getClientById(clientId);
        List<ClientAccount> clientAccounts;
        int amountLessThan= ctx.queryParam("amountLessThan") == null ? 0 : Integer.parseInt(ctx.queryParam("amountLessThan"));
        int amountGreaterThan = ctx.queryParam("amountGreaterThan") == null ? 0 : Integer.parseInt(ctx.queryParam("amountGreaterThan"));
        if(amountLessThan != 0 || amountGreaterThan != 0){
            clientAccounts= accountService.getAllAccountByClientId(client.getId(),
                    amountLessThan,
                    amountGreaterThan);
        }else {
           clientAccounts = accountService.getAllAccountByClientId(client.getId());
        }
        ctx.json(clientAccounts);
    };

    private Handler addAccountForClient = ctx -> {
        Client client = clientService.getClientById(ctx.pathParam("client_id"));
        Account addedClientAccount = accountService.addAccountForClient(client.getId());
        ctx.status(201);
        ctx.json(addedClientAccount);
    };

    private Handler getClientAccountByAccountId = ctx -> {
        Client client = clientService.getClientById(ctx.pathParam("client_id"));
        Account account = accountService.getAccountById(ctx.pathParam("account_id"));
        ClientAccount clientAccount = accountService.getClientAccountByAccountId(client,account);
        ctx.json(clientAccount);
    };

    private Handler updateClientAccountByAccountId = ctx -> {
        String accountId =ctx.pathParam("account_id");
        String clientId = ctx.pathParam("client_id");
        Account account = ctx.bodyAsClass(Account.class);
        Account accountUpdated = accountService.updateClientAccountByAccountId(accountId,account,clientId);
        ctx.status(201);
        ctx.json(accountUpdated);
    };

    private Handler removeClientAccountByAccountId = ctx -> {
        Client client = clientService.getClientById(ctx.pathParam("client_id"));
        Account account = accountService.getAccountById(ctx.pathParam("account_id"));
        ctx.json(accountService.removeClientAccountById(account,client));
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.get("/clients",getAllClients);
        app.get("/clients/{client_id}",getClientById);
        app.post("/clients/",addClient);
        app.put("/clients/{client_id}",updateClient);
        app.delete("/clients/{client_id}",removeClient);
        app.get("/clients/{client_id}/accounts",getAccountsByClientId);
        app.post("/clients/{client_id}/accounts",addAccountForClient);
        app.get("/clients/{client_id}/accounts/{account_id}",getClientAccountByAccountId);
        app.put("/clients/{client_id}/accounts/{account_id}",updateClientAccountByAccountId);
        app.delete("/clients/{client_id}/accounts/{account_id}",removeClientAccountByAccountId);
    }
}
