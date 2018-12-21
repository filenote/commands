package com.genrs.commands.command.impl;

import com.genrs.commands.command.Command;
import com.genrs.commands.model.Role;
import com.genrs.commands.model.User;
import com.genrs.commands.skill.SkillManager;
import com.genrs.commands.skill.SkillName;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static com.genrs.commands.Main.ITEM_COUNT;

public class AdministratorCommand implements Command {

    private final Command command;
    private static final Logger logger = Logger.getLogger(AdministratorCommand.class.getName());

    private AdministratorCommand() {
        this.command = null;
    }
    private AdministratorCommand(Command command) {
        this.command = command;
    }

    public void authorizeExecution(User user, String [] args) {
        if (user.getRole().getValue() >= Role.ADMINISTRATOR.getValue()) this.execute(user, args);
        else System.out.println(user.getUsername() + " does not have sufficient authorization to use command.");
    }

    @Override
    public void execute(User user, String... args) {
        Objects.requireNonNull(command).execute(user, args);
    }

    public static Map<String, Command> commands() {
        return new HashMap<String, Command>() {
            {
                put("item", new AdministratorCommand((user, values) -> {
                    if (values.length != 2) {
                        logger.warning("Argument amount mismatch. Need: 2, Recevied: " + values.length);
                    } else {
                        int item, amount;
                        try { item = Integer.valueOf(values[0]); }
                        catch (NumberFormatException e) {
                            logger.warning("Unable to convert `" + values[0] + "` to an integer.");
                            return;
                        }

                        try { amount = Integer.valueOf(values[1]); }
                        catch (NumberFormatException e) {
                            logger.warning("Unable to convert `" + values[1] + "` to an integer.");
                            return;
                        }

                        if (amount <= 0) {
                            logger.info("Amount should be greater than 0.");
                            return;
                        }

                        if (item < 0  || item > ITEM_COUNT) {
                            logger.info("Not a valid item id.");
                            return;
                        }
                        logger.info("Giving " + user.getUsername() + " " + amount + " of " + item + ".");
                    }
                }));

                put("set-level", new AdministratorCommand((user, args) -> {
                    if (args.length != 2) {
                        logger.info("Argument amount mismatch. Required: 2, Received: " + args.length);
                        return;
                    }

                    SkillName skill;
                    Integer level;

                    try { skill = SkillName.valueOf(args[0].toUpperCase()); }
                    catch (IllegalArgumentException e) {
                        logger.warning("Unable to find " + args[0].toUpperCase() + " in skill names.");
                        return;
                    }

                    try { level = Integer.valueOf(args[1]); }
                    catch (NumberFormatException e) {
                        logger.warning("Unable to convert " + args[1] + " into an integer.");
                        return;
                    }

                    if (level <= 0 || level > 99) {
                        logger.info("Invalid input for level. Level should be between 1 and 99");
                        return;
                    }

                    user.getSkillManager().getSkill(skill)
                            .setLevel(level)
                            .setCurrentLevel(level)
                            .setExperience(SkillManager.getExperienceForLevel(level));

                    logger.info(user.getUsername() + "'s " + skill + " level is now set to " + level + " with" +
                            " experience of " + user.getSkillManager().getSkill(skill).getExperience() + ".");
                }));
            }
        };
    }
}
