package com.revature.controller;

import com.revature.model.AccountType;
import com.revature.service.AccountTypeService;
import io.javalin.Javalin;
import io.javalin.http.Handler;


public class AccountTypeController implements Controller{
   AccountTypeService accountTypeService = new AccountTypeService();

    private Handler removeAccountType = ctx->{
        ctx.json(accountTypeService.removeClient(ctx.pathParam("accountTypeId")));
    };

    private Handler addAccountType = ctx -> {
        AccountType toBeAddedAccountType = ctx.bodyAsClass(AccountType.class);
        AccountType addedAccountType = accountTypeService.addAccountType(toBeAddedAccountType);
        ctx.status(201);
        ctx.json(addedAccountType);
    };

    private Handler updateAccountType = ctx -> {
        String accountTypeId = ctx.pathParam("accountTypeId");
        AccountType accountType = accountTypeService.updateAccountType(accountTypeId,ctx.bodyAsClass(AccountType.class));

        ctx.status(201);
        ctx.json(accountType);
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.delete("/account-types/{accountTypeId}",removeAccountType);
        app.post("/account-types",addAccountType);
        app.put("/account-types/{accountTypeId}",updateAccountType);
    }

}
