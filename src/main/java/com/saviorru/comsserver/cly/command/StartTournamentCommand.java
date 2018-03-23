package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.tournament.Tournament;

import java.util.Scanner;

public class StartTournamentCommand implements Command {

    private Tournament tournament;

    public StartTournamentCommand(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        tournament.start();
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
