package dungeon.zones;

import java.util.InputMismatchException;
import java.util.Scanner;
import dungeon.Player;
import dungeon.Enemy;
import dungeon.Item;

public class CombatZone extends Zone {
    @Override
    public void enter(Player player, int level) {
        Enemy enemy = Enemy.generateEnemyForLevel(level);

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n[ Combat Zone ]");
        System.out.println("A wild " + enemy.getName() + " appears!");

        System.out.println("Enemy HP: " + enemy.getHealth());
        System.out.println("Player HP: " + player.getHealth());

        while (enemy.getHealth() > 0 && player.getHealth() > 0) {
            System.out.println("\nYou grip your " + player.getEquippedWeapon().getName() + " as the " + enemy.getName() + " snarls.");
            System.out.println("What will you do?");
            System.out.println("1. Attack");
            System.out.println("2. Use Potion");
            System.out.println("3. View Inventory (will lose your turn)");
            System.out.print("> ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! You hesitate...");
                continue;
            }

            switch (choice) {
                case 1:
                    if (player.getEnergy() >= 5) {
                        int damage = player.getAttackPower();
                        enemy.takeDamage(damage);
                        player.useEnergy(5);
                        String[] attackLines = {
                            "You lunge forward and strike!",
                            "A flash of steel - your weapon connects.",
                            "You slash with precision!",
                            "With a shout, you go on the offensive.",
                            "You leap forward and bring down your weapon."
                        };
                        System.out.println(attackLines[(int)(Math.random() * attackLines.length)] + " It deals " + damage + " damage!");
                    } else {
                        System.out.println("You're too tired to attack!");
                    }
                    break;
                case 2:
                    boolean usedPotion = false;
                    for (Item item : player.getInventory()) {
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
                case 3:
                    player.showInventory();
                    System.out.println("You hesitated to act. The enemy takes advantage...");
                    break;
                default:
                    System.out.println("You freeze and do nothing.");
            }

            // Enemy's turn
            if (enemy.getHealth() > 0) {
                int enemyDamage = enemy.attack();
                player.takeDamage(enemyDamage);
                System.out.println("The " + enemy.getName() + " retaliates for " + enemyDamage + " damage!");
            }
        }

        if (player.getHealth() > 0) {
            System.out.println("You defeated the " + enemy.getName() + "!");
            player.restoreEnergy(10);
            int rewardGold = 20 + (int)(Math.random() * 10);
            player.addGold(rewardGold);
        } else {
            System.out.println("You were defeated...");
        }
    }
}
