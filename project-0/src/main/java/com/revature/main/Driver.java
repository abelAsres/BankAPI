package com.revature.main;

import com.revature.controller.AccountTypeController;
import com.revature.controller.ClientController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
    public static Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args) {
        //create server
        Javalin app = Javalin.create();

        // This will execute before every single request
        app.before((ctx) -> {
            logger.info(ctx.method() + " request received for " + ctx.path());
        });

        //initalize contollers
        mapControllers(app, new ClientController(),new AccountTypeController(),new ExceptionController());

        //start server on default port(8080)
        app.start();
    }

    public static void mapControllers(Javalin app, Controller... controllers){
        for (Controller controller:controllers){
            controller.mapEndPoints(app);
        }
    }

}
