package com.main;

import com.battles.Battle;
import com.droid.Droid;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean exit = false;
        Battle battle = new Battle();
        Scanner scanner = new Scanner(System.in);
        while(!exit) {
            for (int i = 0; i < 50; ++i) System.out.println();
            System.out.println("1 - Create new droid");
            System.out.println("2 - Show droids");
            System.out.println("3 - Start a fight");
            System.out.println("4 - Play last fight");
            System.out.println("5 - Exit");
            System.out.print("\nChoose the action - ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1) {
                for (int i = 0; i < 50; ++i) System.out.println();
                Droid droid = new Droid();
                droid.createDroid();
                battle.addDroid(droid);
            }

            else if(choice == 2) {
                for (int i = 0; i < 50; ++i) System.out.println();
                System.out.println("1 - Show Droid By Name");
                System.out.println("2 - Show All Droids");
                System.out.println("0 - Go Back");
                System.out.print("Choose the action - ");
                choice = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < 50; ++i) System.out.println();
                if(choice == 1) {
                    System.out.print("Enter name - ");
                    String name = scanner.nextLine();
                    battle.showDroid(name);
                }
                else if(choice == 2)
                    battle.showDroids();
            }

            else if(choice == 3) {
                for (int i = 0; i < 50; ++i) System.out.println();
                System.out.println("1 - 1 vs 1 Fight");
                System.out.println("2 - Team vs Team Fight");
                System.out.println("0 - Go Back");
                System.out.print("Choose the action - ");
                choice = scanner.nextInt();
                scanner.nextLine();
                Droid droid1 = new Droid(), droid2 = new Droid();
                for (int i = 0; i < 50; ++i) System.out.println();
                if(choice == 1) {
                    System.out.print("Enter first droid's name - ");
                    String name = scanner.nextLine();
                    if(!droid1.setDroid(battle.getDroids(), name))
                        System.out.print("No droid with that name");
                    else {
                        System.out.print("Enter second droid's name - ");
                        name = scanner.nextLine();
                        if(!droid2.setDroid(battle.getDroids(), name))
                            System.out.print("No droid with that name");
                        else
                            battle.soloBattle(droid1, droid2);
                    }
                }
                else if(choice == 2) {
                    Droid droid3 = new Droid(), droid4 = new Droid();
                    String team1, team2;
                    System.out.print("Enter first team name - ");
                    team1 = scanner.nextLine();
                    System.out.print("Enter first droid's name - ");
                    String name = scanner.nextLine();
                    if(!droid1.setDroid(battle.getDroids(), name))
                        System.out.print("No droid with that name");
                    else {
                        System.out.print("Enter second droid's name - ");
                        name = scanner.nextLine();
                        if (!droid2.setDroid(battle.getDroids(), name))
                            System.out.print("No droid with that name");
                        else {
                            System.out.print("Enter first team name - ");
                            team2 = scanner.nextLine();
                            System.out.print("Enter first droid's name - ");
                            name = scanner.nextLine();
                            if (!droid3.setDroid(battle.getDroids(), name))
                                System.out.print("No droid with that name");
                            else {
                                System.out.print("Enter second droid's name - ");
                                name = scanner.nextLine();
                                if (!droid4.setDroid(battle.getDroids(), name))
                                    System.out.print("No droid with that name");
                                else {
                                    for (int i = 0; i < 50; ++i) System.out.println();
                                    battle.teamBattle(droid1, droid2, droid3, droid4, team1, team2);
                                }
                            }
                        }
                    }
                }
            }

            else if(choice == 4) {
                for (int i = 0; i < 50; ++i) System.out.println();
                System.out.println("1 - 1 vs 1 Fight");
                System.out.println("2 - Team vs Team Fight");
                System.out.println("0 - Go Back");
                System.out.print("Choose the action - ");
                choice = scanner.nextInt();
                scanner.nextLine();
                String filePath = null;
                if(choice == 1)
                    filePath = "lastSoloBattle.txt";
                else if(choice == 2)
                    filePath = "lastTeamBattle.txt";
                for (int i = 0; i < 50; ++i) System.out.println();
                Battle.readLinesFromFile(filePath);
            }

            else if(choice == 5) {
                exit = true;
                System.out.println("Goodbye");
            }
            scanner.nextLine();
        }
        scanner.close();
    }
}