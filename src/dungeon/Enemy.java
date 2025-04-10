package dungeon;

import java.util.Random;

public class Enemy {
    private String name;
    private int health;
    private int damageMin;
    private int damageMax;

    public Enemy(String name, int health, int damageMin, int damageMax) {
        this.name = name;
        this.health = health;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }

    public int attack() {
        Random rand = new Random();
        return damageMin + rand.nextInt(damageMax - damageMin + 1);
    }

    public static Enemy generateEnemyForLevel(int level) {
        Random rand = new Random();
        String[] names = {"Goblin", "Skeleton", "Zombie"};
        String name = names[rand.nextInt(names.length)];

        int baseHealth = 30 + (level / 5) * 10; // scales every 5 levels
        int health = baseHealth + rand.nextInt(10); // random bonus

        int baseDamageMin = 5 + (level / 10) * 2;
        int baseDamageMax = baseDamageMin + 3;

        return new Enemy(name, health, baseDamageMin, baseDamageMax);
    }
}
