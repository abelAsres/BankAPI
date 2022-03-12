package com.revature.main;

import com.revature.controller.AccountTypeController;
import com.revature.controller.ClientController;
import com.revature.controller.Controller;
import io.javalin.Javalin;

public class Driver {
    public static void main(String[] args) {
        //create server
        Javalin app = Javalin.create();

        //initalize contollers
        mapControllers(app, new ClientController(),new AccountTypeController());

        //start server on default port(8080)
        app.start();
    }

    public static void mapControllers(Javalin app, Controller... controllers){
        for (Controller controller:controllers){
            controller.mapEndPoints(app);
        }
    }

}
