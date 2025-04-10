package dungeon;

import dungeon.zones.CombatZone;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Hero");

        System.out.println("Welcome to DCG!");

            for (int level = 1; level <= 100; level++) {
            System.out.println("\n=== LEVEL " + level + " ===");

            if (level % 10 == 1 || level == 1) {
                displayStoryForLevel(level);
            }

            // Randomly choose a zone
            int zoneType = (int)(Math.random() * 3); // 0 = Combat, 1 = Rest, 2 = Shop
            switch (zoneType) {
                case 0 -> new dungeon.zones.CombatZone().enter(player, level);
                case 1 -> new dungeon.zones.RestZone().enter(player, level);
                case 2 -> new dungeon.zones.ShopZone().enter(player, level);
            }
            

            // Check if player is still alive
            if (player.getHealth() <= 0) {
                System.out.println("\nYou died in the dungeon as a hero. Game over!");
                return;
            }

            // Give reward
            player.reward();
        }

        System.out.println("\nCongratulations! You completed all 100 levels!");
    }
    public static void displayStoryForLevel(int level) {
        switch (level) {
            case 1 -> {
                System.out.println("\nYou awaken at the edge of the Forgotten Crypt, haunted by a dream that felt more like a warning.");
                System.out.println("Legends speak of a relic buried deep within this place - the Soulforge - said to grant immense power or madness.");
                System.out.println("You descend with nothing but a blade, and the name of the one who vanished before you.");
            }
            case 11 -> {
                System.out.println("\nA cold mist clings to your armor as you step into the Sunken Caverns.");
                System.out.println("You feel the presence of something watching from beneath the moss-covered stones.");
                System.out.println("The Soulforge’s pull grows stronger, but so does the weight in your chest.");
            }
            case 21 -> {
                System.out.println("\nThe air grows hot — lava glows beneath the iron grates of the Flameheart Forge.");
                System.out.println("You remember a story your mentor once told: 'Beware the voice that offers strength too easily.'");
                System.out.println("A whisper echoes behind your eyes, tempting… promising… welcoming.");
            }
            case 31 -> {
                System.out.println("\nNature rots in the Withered Garden, vines twisting with spite and shadow.");
                System.out.println("You stumble across a statue with a cracked inscription: 'The forge burns brightest for the broken.'");
                System.out.println("You begin to question whether the Soulforge grants power… or steals it.");
            }
            case 41 -> {
                System.out.println("\nThe Twilight Keep rises in silence, its halls echoing with long-dead chants.");
                System.out.println("A ghost in armor salutes you — not to fight, but in recognition.");
                System.out.println("You feel it now: you’ve walked this path before.");
            }
            case 51 -> {
                System.out.println("\nSnow crunches beneath your boots in the Frostmourne Depths, yet your breath feels warm.");
                System.out.println("Buried in the ice, a sword-shaped gap glows with heat from another realm.");
                System.out.println("Someone came here before you… and left something behind.");
            }
            case 61 -> {
                System.out.println("\nGravity bends sideways in the Astral Archives, where books float and whisper aloud.");
                System.out.println("You read a passage written in your own handwriting — but you never wrote it.");
                System.out.println("Reality is bending, and something is watching from between the words.");
            }
            case 71 -> {
                System.out.println("\nYou slog through the Abyssal Mire, each step sinking deeper into memory and doubt.");
                System.out.println("A child’s voice sings your name, then fades into a scream.");
                System.out.println("You don't know what’s real anymore, but you keep walking.");
            }
            case 81 -> {
                System.out.println("\nFlames pour from the sky inside the Soul Furnace, and yet nothing burns.");
                System.out.println("Your reflection screams from every molten surface.");
                System.out.println("The Soulforge is close now — and it knows you’ve come.");
            }
            case 91 -> {
                System.out.println("\nThe Void Crown stands still, frozen in a moment outside of time.");
                System.out.println("You see yourself, past and future, kneeling before a throne made of light and ruin.");
                System.out.println("The final step is yours to choose — ascend, or vanish like all who came before.");
            }
            default -> {
            }
        }
    }
    
}