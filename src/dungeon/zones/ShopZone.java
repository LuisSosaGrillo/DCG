package dungeon.zones;

import java.util.InputMismatchException;
import java.util.Scanner;
import dungeon.Player;
import dungeon.Item;

public class ShopZone extends Zone {
    @Override
    public void enter(Player player, int level) {
        System.out.println("\n[ Shop Zone ]");
        System.out.println("You encounter a wandering merchant.");

        Scanner scanner = new Scanner(System.in);

        // Optional inventory check BEFORE shop menu
        System.out.print("Would you like to view your inventory? (y/n): ");
        String view = scanner.nextLine().trim().toLowerCase();
        if (view.equals("y")) {
            player.showInventory();
        }

        // Now display shop options
        System.out.println("The merchant offers you an item:");
        System.out.println("1. Health Potion (+25 HP)");
        System.out.println("2. Energy Drink (+20 Energy)");
        System.out.println("3. Iron Sword (+15 Damage Weapon)");
        System.out.print("> ");

        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine()); // safer than nextInt()
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. You fumble awkwardly and say nothing.");
        }

        switch (choice) {
            case 1:
                Item potion = new Item("Health Potion", "Consumable", 25);
                player.getInventory().add(potion);
                System.out.println("You received a Health Potion!");
                break;
            case 2:
                Item energy = new Item("Energy Drink", "Consumable", 20);
                player.getInventory().add(energy);
                System.out.println("You received an Energy Drink!");
                break;
            case 3:
                if (player.spendGold(30)) {
                    Item weapon = new Item("Iron Sword", "Weapon", 15);
                    player.getInventory().add(weapon);
                    System.out.println("You received an Iron Sword!");

                    // Ask to equip
                    System.out.print("Do you want to equip it now? (y/n): ");
                    String answer = scanner.nextLine().trim().toLowerCase();
                    if (answer.equals("y")) {
                        player.equipWeapon(weapon);
                    }
                }
                break;
            default:
                System.out.println("The merchant shrugs and leaves...");
                break;
        }
    }
}
