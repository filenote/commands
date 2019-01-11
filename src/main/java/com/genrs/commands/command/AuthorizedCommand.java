package com.genrs.commands.command;

import com.genrs.commands.model.Role;
import com.genrs.commands.model.User;

public abstract class AuthorizedCommand {

    private Command command;
    private Role role;

    protected AuthorizedCommand(Command command, Role role) {
        this.command = command;
        this.role = role;
    }

    private AuthorizedCommand() {}

    void execute(User user, String[] args) {
        if (user.authorized(role)) command.execute(user, args);
        else System.out.println(role.getValue() + " role is required to execute command.");
    }
}
