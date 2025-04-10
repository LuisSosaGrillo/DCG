package dungeon;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int gold;
    private int health;
    private int maxHealth;
    private int energy;
    private int maxEnergy;
    private List<Item> inventory;
    private Item equippedWeapon; // Placeholder for equipped weapon

    public Player(String name) {
        this.name = name;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.maxEnergy = 50;
        this.energy = maxEnergy;
        this.inventory = new ArrayList<>();
        this.gold = 0;  // Starting gold
        this.equippedWeapon = null;

        // Starter items
        inventory.add(new Item("Rusty Sword", "Weapon", 10));
        inventory.add(new Item("Healing Potion", "Consumable", 20));
       
        this.equippedWeapon = inventory.get(0); //Equip Rusty Sword by default
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount);
        System.out.println(name + " healed for " + amount + " HP. Current health: " + health);
    }

    public void restoreEnergy(int amount) {
        energy = Math.min(maxEnergy, energy + amount);
        System.out.println(name + " restored " + amount + " energy. Current energy: " + energy);
    }

    public void takeDamage(int amount) {
        health -= amount;
        System.out.println(name + " took " + amount + " damage! Current health: " + health);
    }

    public void useEnergy(int amount) {
        energy -= amount;
        System.out.println(name + " used " + amount + " energy. Current energy: " + energy);
    }

    public boolean isAlive() {
        return health > 0;
    }
    
    public void equipWeapon(Item weapon) {
        if (weapon.getType().equals("Weapon")) {
            equippedWeapon = weapon;
            System.out.println(name + " equipped " + weapon.getName() + "!");
        } else {
            System.out.println("You can only equip weapons!");
        }
    }
    
    public Item getEquippedWeapon() {
        return equippedWeapon;
    }
    
    public int getAttackPower() {
        return (equippedWeapon != null) ? equippedWeapon.getValue() : 5;
    }

    public int getGold() {
        return gold;
    }
    
    public void addGold(int amount) {
        gold += amount;
        System.out.println(name + " earned " + amount + " gold! Total gold: " + gold);
    }
    
    public boolean spendGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            System.out.println(name + " spent " + amount + " gold. Remaining gold: " + gold);
            return true;
        } else {
            System.out.println("Not enough gold!");
            return false;
        }
    }
    

    public void reward() {
        System.out.println("You earned a reward for surviving this level!");

        // Random item drop
        int drop = (int)(Math.random() * 2); // 0 or 1
        if (drop == 0) {
            Item item = new Item("Energy Elixir", "Consumable", 15);
            inventory.add(item);
            System.out.println("Found item: " + item.getName());
        } else {
            Item item = new Item("Steel Blade", "Weapon", 20);
            inventory.add(item);
            System.out.println("Found item: " + item.getName());
        }
    }

    public void showStatus() {
        System.out.println(name + "'s Status → HP: " + health + "/" + maxHealth + ", Energy: " + energy + "/" + maxEnergy);
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void showInventory() {
        System.out.println("\n--- " + name + "'s Inventory ---");
        if (inventory.isEmpty()) {
            System.out.println("You are carrying nothing.");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                System.out.println((i + 1) + ". " + item.getName() + " (" + item.getType() + ", " + item.getValue() + ")");
            }
        }
        System.out.println("Equipped Weapon: " + getEquippedWeapon().getName());
        System.out.println("Gold: " + gold);
        System.out.println("---------------------------\n");
    }

    public int getLevel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLevel'");
    }
}
