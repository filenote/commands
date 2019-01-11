package com.genrs.commands.command.impl;

import com.genrs.commands.command.AuthorizedCommand;
import com.genrs.commands.command.Command;
import com.genrs.commands.model.Role;
import com.genrs.commands.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.genrs.commands.model.Role.USER;

public class UserCommand extends AuthorizedCommand {

    private UserCommand(Command command) {
        super(command, USER);
    }

    public static Map<String, AuthorizedCommand> commands() {
        return new HashMap<String, AuthorizedCommand>() {
            {

            }
        };
    }
}
