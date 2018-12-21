package com.genrs.commands;

import com.genrs.commands.command.CommandFactory;
import com.genrs.commands.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static com.genrs.commands.model.Role.*;

public class Main {

    public static final int ITEM_COUNT = 10222;

    static {
        InputStream stream = Main.class.getClassLoader()
                .getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
            Logger logger = Logger.getLogger(Main.class.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {

        User user = new User("user", USER);
        User moderator = new User("moderator", MODERATOR);
        User administrator = new User("administrator", ADMINISTRATOR);
        User developer = new User("developer", DEVELOPER);

        CommandFactory commandFactory = CommandFactory.getInstance();

        commandFactory.execute("doesn't-exist", administrator);
        commandFactory.execute("change-name", moderator, "mod");
        commandFactory.execute("item", moderator, "995", "10_000_000");
        commandFactory.execute("item", administrator, "a", "10_000_000");
        commandFactory.execute("item", administrator, "99", "a");
        commandFactory.execute("item", administrator, "-1", "2147483648");
        commandFactory.execute("item", administrator, "0", "0");
        commandFactory.execute("no-args", administrator);
        commandFactory.execute("d", administrator);
        commandFactory.execute("d", developer);

        commandFactory.execute("set-level", administrator, "strength", "20");
        commandFactory.execute("set-level", administrator, "se", "20");
        commandFactory.execute("set-level", administrator, "strength", "se");
        commandFactory.execute("set-level", moderator, "strength", "20");


        commandFactory.execute(administrator, "set-level strength 20");
        commandFactory.execute(moderator, "set-level strength");
        commandFactory.execute(moderator, "set-level strength 20");
        commandFactory.execute(administrator, "set=level strength 20");
        commandFactory.execute(administrator, "set-level stre");
    }

}
