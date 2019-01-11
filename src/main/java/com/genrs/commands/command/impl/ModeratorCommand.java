package com.genrs.commands.command.impl;

import com.genrs.commands.command.AuthorizedCommand;
import com.genrs.commands.command.Command;
import com.genrs.commands.model.Role;
import com.genrs.commands.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static com.genrs.commands.model.Role.MODERATOR;

public class ModeratorCommand extends AuthorizedCommand {

    private ModeratorCommand(Command command) {
        super(command, MODERATOR);
    }

    public static Map<String, AuthorizedCommand> commands() {
        return new HashMap<String, AuthorizedCommand>() {
            {

            }
        };
    }
}
