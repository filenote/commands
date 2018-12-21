package com.genrs.commands.command.impl;

import com.genrs.commands.command.Command;
import com.genrs.commands.model.Role;
import com.genrs.commands.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class ModeratorCommand implements Command {

    private final Command command;
    private static final Logger logger = Logger.getLogger(ModeratorCommand.class.getName());

    private ModeratorCommand() {
        this.command = null;
    }
    private ModeratorCommand(Command command) {
        this.command = command;
    }

    public void authorizeExecution(User user, String [] args) {
        if (user.getRole().getValue() >= Role.MODERATOR.getValue()) this.execute(user, args);
        else System.out.println(user.getUsername() + " does not have sufficient authorization to use command.");
    }

    @Override
    public void execute(User user, String... args) {
        Objects.requireNonNull(command).execute(user, args);
    }

    public static Map<String, Command> commands() {
        return new HashMap<String, Command>() {
            {

            }
        };
    }
}
