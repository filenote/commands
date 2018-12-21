package com.genrs.commands.command;

import com.genrs.commands.command.impl.AdministratorCommand;
import com.genrs.commands.command.impl.DeveloperCommand;
import com.genrs.commands.command.impl.ModeratorCommand;
import com.genrs.commands.command.impl.UserCommand;
import com.genrs.commands.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.genrs.commands.model.Role.*;

public class CommandFactory {

    private static final Logger logger = Logger.getLogger(CommandFactory.class.getSimpleName());
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
                .add(UserCommand.commands())
                .add("commands", new UserCommand((user, args) -> {
                    if (args.length != 0) {
                        logger.warning("Command does not require any arguments.");
                        return;
                    }
                    StringBuilder printer = new StringBuilder();
                    if (user.authorized(USER)) printer.append(print(UserCommand.class));
                    if (user.authorized(MODERATOR)) printer.append(", ").append(print(ModeratorCommand.class));
                    if (user.authorized(ADMINISTRATOR)) printer.append(", ").append(print(AdministratorCommand.class));
                    if (user.authorized(DEVELOPER)) printer.append(", ").append(print(DeveloperCommand.class));
                    System.out.println("Available commands: " + printer);
                }));
    }

    private CommandFactory add(String key, Command command) {
        if (!map.containsKey(key)) map.put(key, command);
        else logger.warning(key + " already exists, and can't be added.");
        return this;
    }

    private CommandFactory add(Map<String, Command> commands) {
        logger.info("Adding the following commands: " + String.join(", ", commands.keySet()));
        map.putAll(commands);
        return this;
    }

    private static String print(Class c) {
        return String.join(", ", map.entrySet().stream()
                .filter(entry -> entry.getValue().getClass() == c)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
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
