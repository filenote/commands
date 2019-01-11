package com.genrs.commands;

import com.genrs.commands.command.CommandFactory;
import com.genrs.commands.content.ItemsKeptOnDeath;
import com.genrs.commands.content.potions.Potion;
import com.genrs.commands.content.skill.Skill;
import com.genrs.commands.content.skill.skillable.herblore.Herb;
import com.genrs.commands.content.skill.skillable.herblore.Herblore;
import com.genrs.commands.content.skill.skillable.slayer.SlayerMaster;
import com.genrs.commands.model.Item;
import com.genrs.commands.model.User;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;

import static com.genrs.commands.content.ItemsKeptOnDeath.KEPT_ITEMS;
import static com.genrs.commands.content.ItemsKeptOnDeath.LOST_ITEMS;
import static com.genrs.commands.content.potions.Potion.COMBAT;
import static com.genrs.commands.content.skill.skillable.slayer.MonsterType.BANSHEE;
import static com.genrs.commands.model.Role.*;

public class Main {

    public static final int ITEM_COUNT = 10222;
    public static final int VIAL_OF_WATER = 120;


    public static void main(String [] args) {

        User user = new User("user", USER);
        User moderator = new User("moderator", MODERATOR);
        User administrator = new User("administrator", ADMINISTRATOR);
        User developer = new User("developer", DEVELOPER);

        CommandFactory commandFactory = CommandFactory.initialize();

        commandFactory.execute(moderator, "add");
        commandFactory.execute(administrator, "add");
        commandFactory.execute(developer, "add");

        System.out.println(user.getInventory());
        System.out.println(user.getBank());

        user.getInventory().add(new Item(995, 20000));
        user.getInventory().add(new Item(20, 2));
        user.getBank().add(new Item(995, 99999));
        user.getBank().add(new Item(20, 10));

        System.out.println(user.getInventory());
        System.out.println(user.getBank());

        user.getInventory().transfer(new Item(995, 10000), user.getBank());
        user.getBank().transfer(new Item(20, 1), user.getInventory());

        System.out.println(user.getInventory());
        System.out.println(user.getBank());

        user.getInventory().add(new Item(995, Integer.MAX_VALUE));
        user.getInventory().transfer(new Item(995, Integer.MAX_VALUE), user.getBank());
        user.getEquipment().add(new Item(1, 1));
        user.getEquipment().add(new Item(2, 1));
        user.getEquipment().add(new Item(3, 20));
        user.getInventory().add(new Item(3, 200));

        System.out.println(user.getInventory());
        System.out.println(user.getBank());
        /*

        ItemsKeptOnDeath.sortItems(user);

        moderator.getInventory().add(new Item(995, Integer.MAX_VALUE));
        ItemsKeptOnDeath.sortItems(moderator);

        administrator.getInventory().add(new Item(995, 11));
        ItemsKeptOnDeath.sortItems(administrator);
*/
        developer.getInventory().add(new Item(1, 5));
        developer.getInventory().add(new Item(995, 20000));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        HashMap<String, ArrayList<Item>> developerIKOD = ItemsKeptOnDeath.sortItems(developer);
        stopWatch.split();
        System.out.println("IKOD took " + stopWatch.getSplitNanoTime() / 1000000d + "ms");

        /*
        System.out.println(developerIKOD.get(KEPT_ITEMS));
        System.out.println(developerIKOD.get(LOST_ITEMS));

        user.getSkillManager().setLevel(Skill.STRENGTH, 99);
        System.out.println(user.getSkillManager().getSkill(Skill.STRENGTH).getCurrentLevel());
        Potion.drink(user, COMBAT.getId()[1], 0);
        System.out.println(user.getSkillManager().getSkill(Skill.STRENGTH).getCurrentLevel());

        Herblore.mix(user, VIAL_OF_WATER, 253);
        Herblore.mix(user, 95, 255);
        Herblore.mix(user, Herb.TORSTOL.getClean(), 2440);

        for (int i = 0; i < 20; i++) {
            SlayerMaster.TURAEL.getRandomSlayerTask(user.getBlockList()).ifPresent(System.out::println);
        }

        user.addToBlockList(BANSHEE);
        System.out.println("*******");

        for (int i = 0; i < 20; i++) {
            SlayerMaster.TURAEL.getRandomSlayerTask(user.getBlockList()).ifPresent(System.out::println);
        }
*/
    }
}
