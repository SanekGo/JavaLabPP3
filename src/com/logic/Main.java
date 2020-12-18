package com.logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.droids.*;



public class Main {
    public static int selector;
    public static Scanner scanner = new Scanner(System.in);
    public static Random rand = new Random();

    public static ArrayList<Droid> droid_pool;

    public static ArrayList<Droid> team_red;

    public static ArrayList<Droid> team_blue;


    public static void main(String[] args) {
        droid_pool = new ArrayList<Droid>();
        team_red = new ArrayList<Droid>();
        team_blue = new ArrayList<Droid>();

        while(true) {
            selector = menu();
            switch(selector) {
                case (1):
                System.out.println();
                System.out.println("Доступні види дроїдів:");
                System.out.println("1 - Атакуючий");
                System.out.println("2 - Танк");
                System.out.println("3 - Лікар");
                droid_pool.add(createDroid());
                    break;
                case (2):
                poolOut(droid_pool);
                    break;
                case (3):
                setupTeams(1);
                fightStart();
                    break;
                case (4):
                    droid_pool.add(createDroidAutomated("Атакуючий Червоної команди", 1));
                    droid_pool.add(createDroidAutomated("Атакуючий Синьої команди", 1));
                    setupTeamsAutomated(1);
                    fightStart();
                    break;
                case (5):
                    System.out.println("Введіть розмір команди :");
                    selector = scanner.nextInt();
                    setupTeams(selector);
                    fightStart();
                    break;
                case(6):
                    droid_pool.add(createDroidAutomated("Атакуючий Червоної команди", 1));
                    droid_pool.add(createDroidAutomated("Танк Червоної команди 1", 2));
                    droid_pool.add(createDroidAutomated("Танк Червоної команди 2", 2));
                    droid_pool.add(createDroidAutomated("Лікар Червоної команди", 3));
                    droid_pool.add(createDroidAutomated("Атакуючий Синьої команди", 1));
                    droid_pool.add(createDroidAutomated("Танк Синьої команди 1", 2));
                    droid_pool.add(createDroidAutomated("Танк Синьої команди 2", 2));
                    droid_pool.add(createDroidAutomated("Лікар Синьої команди", 3));
                    setupTeamsAutomated(4);
                    fightStart();
                    break;
                case (7):
                    return;
            }
        }

    }



    public static int menu() {
        System.out.println();
        System.out.println("Введіть що ви хочете зробити: ");
        System.out.println("1 - Створити дроїда");
        System.out.println("2 - Вивести список доступних дроїдів");
        System.out.println("3 - Почати битву 1 на 1 (Налаштування вручну)");
        System.out.println("4 - Почати битву 1 на 1 (Автоматичне налаштування)");
        System.out.println("5 - Почати командну битву (Налшатування вручну)");
        System.out.println("6 - Почати командну битву (Автоматичне налаштування)");
        System.out.println("7 - Вийти з програми");
        selector = scanner.nextInt();
        return selector;
    }


    public static Droid createDroid() {
        String str = new String();
        Droid droid = null;
        int randomized_health = (int)(LogicDefines.BASE_HEALTH * (1 - LogicDefines.BASE_MARGIN)) +
            rand.nextInt((int)(LogicDefines.BASE_HEALTH * LogicDefines.BASE_MARGIN * 2));
        int randomized_damage = (int)(LogicDefines.BASE_DAMAGE * (1 - LogicDefines.BASE_MARGIN)) +
            rand.nextInt((int)(LogicDefines.BASE_DAMAGE * LogicDefines.BASE_MARGIN * 2));

        do 
        {System.out.print("\nОберіть будь-кого з доступних видів дроїдів : ");
        selector = scanner.nextInt();
        } while (selector < 1 || selector > 3);
        scanner.nextLine();
        System.out.print("Введіть ім'я дроїда: ");
        str = scanner.nextLine();
        switch (selector) {
            case (1):
            droid = new DroidDealer(str, randomized_health , randomized_damage);
                break;
            case (2):
            droid = new DroidTank(str, randomized_health, randomized_damage);
                break;
            case (3):
            droid = new DroidHealer(str, randomized_health, randomized_damage);
                break;
        }
        return droid;
    }


    public static Droid createDroidAutomated(String name, int droidtype) {
        Droid droid = null;
        int randomized_health = (int)(LogicDefines.BASE_HEALTH * (1 - LogicDefines.BASE_MARGIN)) +
                rand.nextInt((int)(LogicDefines.BASE_HEALTH * LogicDefines.BASE_MARGIN * 2));
        int randomized_damage = (int)(LogicDefines.BASE_DAMAGE * (1 - LogicDefines.BASE_MARGIN)) +
                rand.nextInt((int)(LogicDefines.BASE_DAMAGE * LogicDefines.BASE_MARGIN * 2));
        switch (droidtype) {
            case (1):
                droid = new DroidDealer(name, randomized_health , randomized_damage);
                break;
            case (2):
                droid = new DroidTank(name, randomized_health, randomized_damage);
                break;
            case (3):
                droid = new DroidHealer(name, randomized_health, randomized_damage);
                break;
        }
        return droid;
    }

    public static void poolOut(ArrayList<Droid> list) {
        System.out.format("№  %-10s %-15s %-10s %-10s %n", "Клас", "Ім'я", "Здоров'я", "Шкодження");
        for (int i = 0; i < list.size(); i++) {
            System.out.format("%d  %-10s %-15s %-10d %-10d %n", i + 1, list.get(i).getDroidClass(), list.get(i).getName(),
            list.get(i).getHealth(), list.get(i).getDamage()) ;
        }
        System.out.println();
        return;
    }

    public static void setupTeams(int team_size) {
        System.out.println();
        if (team_size == 1) System.out.println("Налаштування бою 1 на 1.");
        System.out.println("Доступні дроїди:");
        poolOut(droid_pool);
        for (int i = 0; i < team_size; i++) {
            System.out.println("Оберівть бійця з Червоної команди: ");
            selector = scanner.nextInt();
            team_red.add(droid_pool.get(selector - 1));
            droid_pool.remove(selector - 1);
            System.out.println("Обновлений список:");
            poolOut(droid_pool);
        }
        System.out.println("Завершення створення Червоної команди:");
        poolOut(team_red);
        for (int i = 0; i < team_size; i++) {
            System.out.println("Оберіть бійця з Синьої команди: ");
            selector = scanner.nextInt();
            team_blue.add(droid_pool.get(selector - 1));
            droid_pool.remove(selector - 1);
            System.out.println("Обновлени список:\n");
            poolOut(droid_pool);
        }
        System.out.println("Заверешння створення Синьої команди:");
        poolOut(team_blue);

        System.out.println("Налаштування команд завершено");
    }

    public static void setupTeamsAutomated(int team_size) {
        for (int cnt = 0; cnt < team_size; cnt++) {
            team_red.add(droid_pool.get(0));
            droid_pool.remove(0);
        }
        for (int cnt = 0; cnt < team_size; cnt++) {
            team_blue.add(droid_pool.get(0));
            droid_pool.remove(0);
        }
        System.out.println("Налаштування команд завершено");
        System.out.println("Заверешення створення Червоної команди:");
        poolOut(team_red);
        System.out.println("Завершення створення Синьої команди:");
        poolOut(team_blue);
    }

        public static void fightStart() {
        for (int cnt = 1;(team_red.size() != 0) && (team_blue.size() != 0); cnt++) {
            fightTick();

            System.out.println("Хід " + cnt);
            System.out.println("Склад Червоної команди:");
            poolOut(team_red);
            System.out.println("Склад Синьої команди:");
            poolOut(team_blue);
        }
        if (team_red.size() == 0 && team_blue.size() == 0) {
            System.out.println("Ніяка команда не перемогла.");
        } else if (team_red.size() == 0) {
            System.out.println("Перемогла Синя команда!");
        } else {
            System.out.println("Перемогла Червона команда!");

        }
        return;
    }


    private static void fightTick() {
        int randslave;


        for (int i = 0; i < team_red.size(); i++) {
            
            if (team_red.get(i).getDroidClass().equals(LogicDefines.HEALER_CLASS)) {
                do {
                randslave = rand.nextInt(team_red.size());
                } while (randslave == i);
                team_red.get(i).attack(team_red.get(randslave));
            } else {
                randslave = rand.nextInt(team_blue.size());
                team_red.get(i).attack(team_blue.get(randslave));
            }
        }


        for (int i = 0; i < team_blue.size(); i++) {
            
            if (team_blue.get(i).getDroidClass().equals(LogicDefines.HEALER_CLASS)) {
                do {
                randslave = rand.nextInt(team_blue.size());
                } while (randslave == i);
                team_blue.get(i).attack(team_blue.get(randslave));
            } else {
                randslave = rand.nextInt(team_red.size());
                team_blue.get(i).attack(team_red.get(randslave));

            }
        }
        
        for (int cnt = 0; cnt < team_red.size(); cnt++) {
            if (team_red.get(cnt).getHealth() <= 0) team_red.remove(cnt);
        }
        for (int cnt = 0; cnt < team_blue.size(); cnt++) {
            if (team_blue.get(cnt).getHealth() <= 0) team_blue.remove(cnt);
        }
    }
}
