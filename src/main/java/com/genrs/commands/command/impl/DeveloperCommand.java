package com.genrs.commands.command.impl;

import com.genrs.commands.command.Command;
import com.genrs.commands.model.Role;
import com.genrs.commands.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class DeveloperCommand implements Command {

    private final Command command;
    private static final Logger logger = Logger.getLogger(DeveloperCommand.class.getName());

    private DeveloperCommand() {
        this.command = null;
    }
    private DeveloperCommand(Command command) {
        this.command = command;
    }

    public void authorizeExecution(User user, String [] args) {
        if (user.getRole().getValue() >= Role.DEVELOPER.getValue()) this.execute(user, args);
        else System.out.println(user.getUsername() + " does not have sufficient authorization to use command.");
    }

    @Override
    public void execute(User user, String... args) {
        Objects.requireNonNull(command).execute(user, args);
    }

    public static Map<String, Command> commands() {
        return new HashMap<String, Command>() {
            {
                put("d", new DeveloperCommand((user, args) ->
                        System.out.println("This is a command in the DeveloperCommand class.")));
            }
        };
    }
}
