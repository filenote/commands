package com.genrs.commands.command.impl;

import com.genrs.commands.command.AuthorizedCommand;
import com.genrs.commands.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.genrs.commands.model.Role.ADMINISTRATOR;

public class AdministratorCommand extends AuthorizedCommand {

    public static final Set<String> keys = commands().keySet();

    private AdministratorCommand(Command command) {
        super(command, ADMINISTRATOR);
    }

    public static Map<String, AuthorizedCommand> commands() {
        return new HashMap<String, AuthorizedCommand>(){
            {
                put("add", new AdministratorCommand((user, args) -> {
                    System.out.println("this is and admin command.");
                }));
            }
        };
    }
}
