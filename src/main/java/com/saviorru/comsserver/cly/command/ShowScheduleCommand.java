package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShowScheduleCommand implements Command {

    private Schedule schedule;
    private Tournament tournament;

    public ShowScheduleCommand(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() {
        try {
            schedule = tournament.getSchedule();
            showSchedule();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String nameCommand() {
        return "show schedule";
    }

    @Override
    public String commandFormat() {
        return "command";
    }

    private void showSchedule() {

        List<Match> matches = new ArrayList<>(schedule.getAllMatches());
        matches.sort(new Comparator<Match>() {
            public int compare(Match o1, Match o2) {
                return (o1.getDate().isAfter(o2.getDate())) ? 1 : (o1.getDate().equals(o2.getDate())) ? 0 : -1;
            }
        });
        printSchedule(matches);
    }

    private void printSchedule(List<Match> matches) {
        int count = 0;
        for (Match match : matches) {
            String isPlayedMatch = (match.isPlayed()) ? " сыгран" : " не сыгран";
            count++;
            if (!match.isPlayed())
                System.out.println("Номер матча: " + count + " " + "Игрок 1 : " + match.getFirstSide() + "\n" + " Игрок 2 : " + match.getSecondSide() + "\n" + "Дата матча : " + match.getDate().toString() + "\n" + " Статус матча: " +
                        isPlayedMatch);
            else {
                try {
                    System.out.println("Номер матча: " + count + " " + "Игрок 1 : " + match.getFirstSide() + "\n" + "Игрок 2 : " + match.getSecondSide() + "\n" + "Дата матча : " + match.getDate().toString() + "\n" + " Статус матча: " +
                            isPlayedMatch + "\n" + " Очки первого игрока : " + match.getPointsFirstSide() + "\n" + " Очки первого игрока : " + match.getPointsSecondSide() +
                            "\n" + " Победитель : " + match.getWinner());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
