package com.genrs.commands.command;

import com.genrs.commands.model.User;

public interface Command {
    void execute(User user, String... args);
}