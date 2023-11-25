package com.battles;

import com.droid.Droid;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.Files.delete;

public class Battle {
    private static BufferedWriter bufferedWriter;
    private String filePath;

    public Battle() {
    }

    public Battle(String filePath) {
        this.filePath = filePath;
    }

    public void move(Droid droid1, Droid droid2) {
        System.out.println(droid1.getName() + " your turn");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Attack");
        System.out.println("2 - Heal");
        System.out.print("\nChoose the action - ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1) {
            int dealt = droid1.attack(droid1);
            droid2.setHealth(droid2.getHealth() - dealt);
            System.out.println(droid1.getName() + " dealt " + dealt + " damage to " + droid2.getName());
            writeToFile(droid1.getName() + " dealt " + dealt + " damage to " + droid2.getName());
            scanner.nextLine();
        }
        else if(choice == 2) {
            droid1.RestoreHealth(droid1.getHeal());
            System.out.println(droid1.getName() + " restore " + droid1.getHeal() + " health");
            writeToFile(droid1.getName() + " restore " + droid1.getHeal() + " health");
            scanner.nextLine();
            droid1.restoreHeal();
        }
    }

     public void teamMove(Droid droid1, Droid droid2, Droid droid3) {
        if(!droid2.isAlive())
            move(droid1, droid3);
        else if(!droid3.isAlive())
            move(droid1, droid2);
        else {
            System.out.println(droid1.getName() + " your turn");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1 - Attack");
            System.out.println("2 - Heal");
            System.out.print("\nChoose the action - ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 1) {
                System.out.println("Choose enemy to attack");
                System.out.println("1 - " + droid2.getName());
                System.out.println("2 - " + droid3.getName());
                System.out.print("\nChoose the action - ");
                choice = scanner.nextInt();
                scanner.nextLine();
                int dealt = droid1.attack(droid1);
                if(choice == 1) {
                    droid2.setHealth(droid2.getHealth() - dealt);
                    System.out.println(droid1.getName() + " dealt " + dealt + " damage to " + droid2.getName());
                    writeToFile(droid1.getName() + " dealt " + dealt + " damage to " + droid2.getName());
                    scanner.nextLine();
                }
                else if(choice == 2) {
                    droid2.setHealth(droid3.getHealth() - dealt);
                    System.out.println(droid1.getName() + " dealt " + dealt + " damage to " + droid3.getName());
                    writeToFile(droid1.getName() + " dealt " + dealt + " damage to " + droid3.getName());
                    scanner.nextLine();
                }
            }
            else if(choice == 2) {
                droid1.RestoreHealth(droid1.getHeal());
                System.out.println(droid1.getName() + " restore " + droid1.getHeal() + " health");
                writeToFile(droid1.getName() + " restore " + droid1.getHeal() + " health");
                scanner.nextLine();
                droid1.restoreHeal();
            }
        }
     }

    public void soloBattle(Droid droid1, Droid droid2) throws IOException {
        String path = "lastSoloBattle.txt";
        if (Files.exists(Path.of(path)))
            delete(Path.of(path));
        openFile(path);
        while(droid1.isAlive() && droid2.isAlive()) {
            for (int i = 0; i < 50; ++i) System.out.println();
            move(droid1, droid2);
            if(droid2.isAlive()) {
                for (int i = 0; i < 50; ++i) System.out.println();
                move(droid2, droid1);
                if(droid1.isAlive()) {
                    for (int i = 0; i < 50; ++i) System.out.println();
                    droid1.soloBattleStatus(droid1, droid2);
                    writeToFile(droid1.soloBattleStatusToWrite(droid1, droid2));
                }
            }
        }
        if(droid1.isAlive()) {
            System.out.println(droid1.getName() + " wins");
            writeToFile(droid1.getName() + " wins");
        }
        else {
            System.out.println(droid2.getName() + " wins");
            writeToFile(droid2.getName() + " wins");
        }
        closeFile();
    }

    public void teamBattle(Droid t1droid1, Droid t1droid2, Droid t2droid1, Droid t2droid2, String team1, String team2) throws IOException {
        String path = "lastTeamBattle.txt";
        if (Files.exists(Path.of(path)))
            delete(Path.of(path));
        openFile(path);
        while((t1droid1.isAlive() || t1droid2.isAlive()) && (t2droid1.isAlive() || t2droid2.isAlive())) {
            if(t1droid1.isAlive() || t1droid2.isAlive())
                System.out.println("Team " + team1 + " turn:");
            if(t1droid1.isAlive()) {

                teamMove(t1droid1, t2droid1, t2droid2);
                for (int i = 0; i < 50; ++i) System.out.println();
            }

            if(t1droid2.isAlive()) {
                teamMove(t1droid2, t2droid1, t2droid2);
                for (int i = 0; i < 50; ++i) System.out.println();
            }

            if(t2droid1.isAlive() || t2droid2.isAlive())
                System.out.println("Team " + team2 + " turn:");
            if(t2droid1.isAlive()) {
                teamMove(t2droid1, t1droid1, t1droid2);
                for (int i = 0; i < 50; ++i) System.out.println();
            }

            if(t2droid2.isAlive()) {
                teamMove(t2droid2, t1droid1, t1droid2);
                for (int i = 0; i < 50; ++i) System.out.println();
            }

            t1droid1.teamBattleStatus(t1droid1, t1droid2, t2droid1, t2droid2, team1, team2);
            writeToFile(t1droid1.teamBattleStatusToWrite(t1droid1, t1droid2, t2droid1, t2droid2, team1, team2));
        }
        if(t1droid1.isAlive() || t1droid2.isAlive()) {
            System.out.println("Team " + team1 + " wins");
            writeToFile("Team " + team1 + " wins");
        }
        else {
            System.out.println("Team " + team2 + " wins");
            writeToFile("Team " + team2 + " wins");
        }
        closeFile();
    }

    public static void openFile(String filePath) {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.out.println("File opening error: " + e.getMessage());
        }
    }

    public static void closeFile() {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            System.out.println("File closing error: " + e.getMessage());
        }
    }

    public static void writeToFile(String data) {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public String readLine(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            return "error" + e.getMessage();
        }
    }

    public static void readLinesFromFile(String filePath) {
        Battle battle = new Battle(filePath);
        String line;
        line = battle.readLine(filePath);
        System.out.println(line);
    }
}

