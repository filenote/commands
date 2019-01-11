package com.genrs.commands.command.impl;

import com.genrs.commands.command.AuthorizedCommand;
import com.genrs.commands.command.Command;

import java.util.HashMap;
import java.util.Map;

import static com.genrs.commands.model.Role.DEVELOPER;

public class DeveloperCommand extends AuthorizedCommand {

    private DeveloperCommand(Command command) {
        super(command, DEVELOPER);
    }

    public static Map<String, AuthorizedCommand> commands() {
        return new HashMap<String, AuthorizedCommand>() {
            {

            }
        };
    }
}
