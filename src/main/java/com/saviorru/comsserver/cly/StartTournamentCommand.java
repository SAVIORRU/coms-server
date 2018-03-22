package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.tournament.Tournament;

import java.util.Scanner;

public class StartTournamentCommand extends Command {

    public StartTournamentCommand(Tournament tournament) {
        super(tournament);
    }

    @Override
    public Boolean execute() {
        try {

            tournament.start();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String nameCommand() {
        return "start";
    }

    @Override
    public String commandFormat() {
        return "command";
    }

    private boolean addPlayers() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        Integer countPlayers = 0;
        while (true) {
            System.out.print("Введите кол-чо игроков:");
            if (scanner.hasNextInt()) {
                countPlayers = scanner.nextInt();
                if (countPlayers < 0) {
                    System.out.println("Количество игроков должно быть больше нуля");
                    continue;
                }
                break;
            } else {
                System.out.println("Необходимо ввести число игроков");
            }
        }
        return false;
    }

    private boolean initPlayerDispatcher(Integer countPlayers) {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (countPlayers > 0) {
            System.out.print("Введите данные игрока, формат ввода:");
        }
        return false;
    }
}
