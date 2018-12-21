package com.genrs.commands.command;

import com.genrs.commands.command.impl.AdministratorCommand;
import com.genrs.commands.command.impl.DeveloperCommand;
import com.genrs.commands.command.impl.ModeratorCommand;
import com.genrs.commands.command.impl.UserCommand;
import com.genrs.commands.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommandFactory {

    private static final Logger logger = Logger.getLogger(CommandFactory.class.getName());
    private static HashMap<String, Command> map = new HashMap<>();
    private static CommandFactory instance;

    private CommandFactory() {}

    public static CommandFactory getInstance() {
        if (instance == null)
            instance = initialize();
        return instance;
    }

    private static CommandFactory initialize() {
        return new CommandFactory()
                .add(DeveloperCommand.commands())
                .add(AdministratorCommand.commands())
                .add(ModeratorCommand.commands())
                .add(UserCommand.commands());
    }

    private CommandFactory add(Map<String, Command> commands) {
        logger.info("Adding the following commands: " + String.join(", ", commands.keySet()));
        map.putAll(commands);
        return this;
    }

    public void execute(User user, String string) {
        String [] stringParts = string.split(" ");
        String command = stringParts[0];
        String [] args = Arrays.copyOfRange(stringParts, 1, stringParts.length);
        execute(command, user, args);
    }

    public void execute(String s, User user, String... args) {
        if(map.containsKey(s)) {
            Command command = map.get(s);

            if (command instanceof UserCommand) ((UserCommand) command).authorizeExecution(user, args);
            else if (command instanceof ModeratorCommand) ((ModeratorCommand) command).authorizeExecution(user, args);
            else if (command instanceof AdministratorCommand) ((AdministratorCommand) command).authorizeExecution(user, args);
            else if (command instanceof DeveloperCommand) ((DeveloperCommand) command).authorizeExecution(user, args);
            else {
                logger.warning("Command does not have an instance.");
            }
        } else {
            logger.info("Command '" + s + "' does not exist.");
        }
    }
}
