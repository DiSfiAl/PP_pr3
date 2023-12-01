package com.droid;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Droid {
    private String name;
    private int health;
    private int type;
    private int heal;
    private boolean low = false;
    static private int healBoost;
    private int damage;
    private int maxDamage;
    private int minDamage;
    private Scanner scanner;

    public Droid() {
        this.scanner = new Scanner(System.in);
    }
    public void setHealth(int health) { this.health = health; }
    public void setHeal() { heal += healBoost; }
    public void setHealBoost() { healBoost = health / 10; }
    public void setLow() { this.low = true; }
    public void RestoreHealth(int heal) { health += heal; }
    public void restoreHeal() { heal = health / 10; }
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getHeal() { return heal; }
    public int getDamage() { return damage; }
    public int getMaxDamage() { return maxDamage; }
    public int getMinDamage() { return minDamage; }
    public int getType() { return type; }
    public boolean getLow() { return low; }
    public boolean isAlive() {
        return getHealth() > 0;
    }
    public String showType() {
        if(getType() == 1) {
            return "Tank";
        }
        else if(getType() == 2) {
            return "Defensive";
        }
        else if(getType() == 3) {
            return "Lower";
        }
        else
            return "No type";
    }
    public static void showDroidInfo(Droid droid) {
        System.out.println("Name: " + droid.getName());
        System.out.println("Max Damage: " + droid.getMaxDamage());
        System.out.println("Min Damage: " + droid.getMinDamage());
        System.out.println("Critical Damage: " + droid.getDamage() * 3);
        System.out.println("Health: " + droid.getHealth());
        System.out.println("Heal Ability(Doubling with every attach): " + droid.getHeal());
        System.out.println("Type: " + droid.showType());
    }
    public static void showAllDroids(List<Droid> droids) {
        System.out.println("All Droids:");
        System.out.println("---------------");
        for (Droid droid : droids) {
            showDroidInfo(droid);
            System.out.println("---------------");
        }
    }
    public static void showDroidByName(List<Droid> droids, String name) {
        boolean result = false;
        System.out.println("Droid with name " + name + ":");
        for (Droid droid : droids) {
            if(Objects.equals(name, droid.getName())) {
                showDroidInfo(droid);
                result = true;
            }
        }
        if(!result)
            System.out.println("No droid with that name");
        }
    public void createDroid() {
        System.out.println("Enter info about droid:");
        System.out.print("Name: ");
        name = scanner.nextLine();

        System.out.print("Damage: ");
        damage = scanner.nextInt();
        scanner.nextLine();

        maxDamage = getDamage() + getDamage() / 5 * 2;
        minDamage = getDamage() - getDamage() / 10 * 3;

        System.out.print("Life: ");
        health = scanner.nextInt();
        scanner.nextLine();

        setHealBoost();
        setHeal();
        while(type < 1 || type > 3) {
            System.out.print("Choose droid's type:\n1 - Tank(Deals extra 10% of damage)\n2 - Defensive(restore extra 15% health)\n3 - Lower(save 1 health after last attack first time)\nType: ");
            type = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("Droid " + name + " added.");
    }
    public boolean setDroid(List<Droid> droids, String name) {
        boolean result = false;
        for (Droid droid : droids) {
            if (Objects.equals(name, droid.getName())) {
                this.name = droid.getName();
                this.damage = droid.getDamage();
                this.maxDamage = droid.getMaxDamage();
                this.minDamage = droid.getMinDamage();
                this.type = droid.getType();
                this.low = droid.getLow();
                this.health = droid.getHealth();
                this.heal = droid.getHeal();
                result = true;
            }
        }
        return result;
    }
    public int attack(Droid droid) {
        int rand = (int)Math.floor(Math.random() * (maxDamage - minDamage + 1) + minDamage);
        int critChance = (int)Math.floor(Math.random() * 100 + 1);
        setHeal();
        if(critChance > 90) { return droid.getDamage() * 3; }
        else {
            return rand;
        }
    }

    public void soloBattleStatus(Droid droid1, Droid droid2) {
        System.out.println("Name: " + droid1.getName());
        System.out.println("Health: " + droid1.getHealth());
        System.out.println("Name: " + droid2.getName());
        System.out.println("Health: " + droid2.getHealth());
        scanner.nextLine();
    }

    public String soloBattleStatusToWrite(Droid droid1, Droid droid2) {
        return "\nName: " + droid1.getName() + "\nHealth: " + droid1.getHealth() + "\nName: " + droid2.getName() + "\nHealth: " + droid2.getHealth() + "\n";
    }

    public void teamBattleStatus(Droid droid1, Droid droid2, Droid droid3, Droid droid4, String team1, String team2) {
        System.out.println("Team: " + team1);
        System.out.println("Name: " + droid1.getName());
        if(!droid1.isAlive())
            droid1.setHealth(0);
        System.out.println("Health: " + droid1.getHealth());

        System.out.println("Name: " + droid2.getName());
        if(!droid2.isAlive())
            droid2.setHealth(0);
        System.out.println("Health: " + droid2.getHealth());

        System.out.println("Team: " + team2);
        System.out.println("Name: " + droid3.getName());
        if(!droid3.isAlive())
            droid3.setHealth(0);
        System.out.println("Health: " + droid3.getHealth());

        System.out.println("Name: " + droid4.getName());
        if(!droid4.isAlive())
            droid4.setHealth(0);
        System.out.println("Health: " + droid4.getHealth());
        scanner.nextLine();
    }

    public String teamBattleStatusToWrite(Droid droid1, Droid droid2, Droid droid3, Droid droid4, String team1, String team2) {
        return "\nTeam: " + team1 + "\nName: " + droid1.getName() + "\nHealth: " + droid1.getHealth() + "\nName: " + droid2.getName() + "\nHealth: " + droid2.getHealth() + "\n" + "\nTeam: " + team2 + "\nName: " + droid3.getName() + "\nHealth: " + droid3.getHealth() + "\nName: " + droid4.getName() + "\nHealth: " + droid4.getHealth() + "\n";
    }
}
