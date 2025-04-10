package dungeon.zones;

import java.util.Random;
import java.util.Scanner;

import dungeon.Player;
import dungeon.Enemy;
import dungeon.Item;

public class CombatZone extends Zone {
    @Override
    public void enter(Player player) {
        System.out.println("\n[ Combat Zone ]");
        Enemy enemy = Enemy.generateRandomEnemy();

        Scanner scanner = new Scanner(System.in);

        System.out.println("A wild " + enemy.getName() + " appears!");

        while (enemy.getHealth() > 0 && player.getHealth() > 0) {
            System.out.println("\nChoose an action: ");
            System.out.println("1. Attack");
            System.out.println("2. Use Potion (if any)");
            System.out.print("> ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (player.getEnergy() >= 5) {
                        int damage = player.getAttackPower();
                        enemy.takeDamage(damage);
                        System.out.println("You strike with your " + player.getEquippedWeapon().getName() + " for " + damage + " damage!");
                        player.useEnergy(5);
                    } else {
                        System.out.println("You're too tired to attack!");
                    }
                    break;
                case 2:
                    boolean usedPotion = false;
                    for (var item : player.getInventory()) {
                        if (item.getType().equals("Consumable")) {
                            player.heal(item.getValue());
                            player.getInventory().remove(item);
                            System.out.println("Used: " + item.getName());
                            usedPotion = true;
                            break;
                        }
                    }
                    if (!usedPotion) {
                        System.out.println("No potions found in inventory!");
                    }
                    break;
                default:
                    System.out.println("Invalid input.");
            }

            // Enemy's turn
            if (enemy.getHealth() > 0) {
                int enemyDamage = enemy.attack();
                player.takeDamage(enemyDamage);
            }
        }

        if (player.getHealth() > 0) {
            System.out.println("You defeated the enemy!");
            player.restoreEnergy(10); // small energy boost after winning
            
            int rewardGold = 20 + (int)(Math.random() * 10); // 20-30 gold
            player.addGold(rewardGold);
        }
    }
}
