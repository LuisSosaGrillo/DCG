package dungeon.zones;

import java.util.Scanner;

import dungeon.Player;
import dungeon.Item;

public class RestZone extends Zone {
    @Override
    public void enter(Player player, int level) {
        System.out.println("\n[ Rest Zone ]");
        System.out.println("You find a quiet room with a cozy campfire.");

        int healAmount = 30;
        int energyRestore = 20;

        player.heal(healAmount);
        player.restoreEnergy(energyRestore);

        System.out.println("You rest and recover " + healAmount + " HP and " + energyRestore + " energy.");
    }
}
