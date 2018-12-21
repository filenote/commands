package com.genrs.commands.command.impl;

import com.genrs.commands.command.Command;
import com.genrs.commands.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.genrs.commands.model.Role.USER;

public class UserCommand implements Command {

    private Command command;
    private static final Logger logger = Logger.getLogger(UserCommand.class.getName());

    public void authorizeExecution(User user, String [] args) {
        if (user.authorized(USER)) this.execute(user, args);
        else System.out.println(user.getUsername() + " does not have sufficient authorization to use command.");
    }

    private UserCommand() {
        this.command = null;
    }

    public UserCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute(User user, String... args) {
        command.execute(user, args);
    }

    public static Map<String, Command> commands() {
        return new HashMap<String, Command>() {
            {

                put("change-name", new UserCommand((user, values) -> {
                    if (values.length != 1) {
                        logger.warning("Argument amount mismatch. Need: 1, Received: " + values.length);
                    } else {
                        String username = values[0];
                        logger.info("Changing username from '" + user.getUsername() + "' to '" + username + "'.");
                        user.setUsername(username);
                    }
                }));

                put("no-args", new UserCommand((user, values) -> {
                    logger.info("values.length should be 0, values.length: " + values.length);
                }));
            }
        };
    }
}
