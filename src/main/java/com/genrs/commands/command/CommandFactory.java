package com.genrs.commands.command;

import com.genrs.commands.command.impl.*;
import com.genrs.commands.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static HashMap<String, AuthorizedCommand> map = new HashMap<>();

    private CommandFactory() {}

    public static CommandFactory initialize() {
        return new CommandFactory()
                .add(DeveloperCommand.commands())
                .add(AdministratorCommand.commands())
                .add(ModeratorCommand.commands())
                .add(UserCommand.commands());
    }

    private CommandFactory add(Map<String, AuthorizedCommand> commands) {
        if (!commands.isEmpty()) {
            System.out.println("Adding the following commands: " + String.join(", ", commands.keySet()));
            map.putAll(commands);
        }
        return this;
    }

    public void execute(User user, String string) {
        String [] stringParts = string.split(" ");
        String command = stringParts[0];
        String [] args = Arrays.copyOfRange(stringParts, 1, stringParts.length);
        execute(command, user, args);
    }

    public void execute(String s, User user, String [] args) {
        if (map.containsKey(s)) {
            AuthorizedCommand command = map.get(s);
            command.execute(user, args);
        }
    }
}
