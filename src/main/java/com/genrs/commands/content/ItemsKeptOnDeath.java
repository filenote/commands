package com.genrs.commands.content;

import com.genrs.commands.model.Item;
import com.genrs.commands.model.User;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toCollection;

public class ItemsKeptOnDeath {

    public static final String KEPT_ITEMS = "kept";
    public static final String LOST_ITEMS = "lost";

    private static ArrayList<Item> getItems(User user) {
        Item[] inventory = user.getInventory().getItems();
        Item[] equipment = user.getEquipment().getItems();

        return Stream.of(ArrayUtils.addAll(inventory, equipment))
                .filter(Objects::nonNull)
                .map(Item::new)
                .filter(Item::isValid)
                .sorted(comparingInt(Item::getValue).reversed())
                .collect(toCollection(ArrayList::new));
    }

    public static HashMap<String, ArrayList<Item>> sortItems(User user) {
        ArrayList<Item> items = getItems(user);

        ArrayList<Item> kept = new ArrayList<>();
        for (Item item : items) {
            while (kept.size() < 3 && item.getAmount() > 0) {
                kept.add(new Item(item.getId(), 1));
                item.subtract(1);
            }
            if (kept.size() == 3) break;
        }



                /*.stream()
                .flatMap(values -> {
                    Item item = values.get(0);
                    if (item.isStackable()) {
                        int sum = values.stream().mapToInt(Item::getAmount).sum();
                        int id = item.getId();
                        return Stream.of(new Item(id, sum));
                    }
                    return values.stream();
                }).collect(toCollection(ArrayList::new));
*/
        // System.out.println(collect);

        /*

        ArrayList<Item> lost = items.stream()
                .filter(Item::isValid)
                .collect(groupingBy(Item::getId))
                .values().stream()
                .flatMap(values -> {
                    Item item = values.get(0);
                    if (item.isStackable()) {
                        int sum = values.stream().mapToInt(Item::getAmount).sum();
                        int id = item.getId();
                        return Stream.of(new Item(id, sum));
                    }
                    return values.stream();
                }).collect(Collectors.toCollection(ArrayList::new));

        HashMap<String, ArrayList<Item>> map = new HashMap<>();
        map.put(KEPT_ITEMS, kept);
        map.put(LOST_ITEMS, lost);
        return map;
        */
        return null;
    }
}
