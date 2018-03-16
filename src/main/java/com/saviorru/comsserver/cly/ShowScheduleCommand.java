package com.saviorru.comsserver.cly;

import com.saviorru.comsserver.domain.Match;
import com.saviorru.comsserver.domain.Schedule;
import com.saviorru.comsserver.domain.Tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowScheduleCommand extends Command {

    private Schedule schedule;

    public ShowScheduleCommand(Tournament tournament) {
        super(tournament);
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

    private void showSchedule() {

        List<Match> matches = new ArrayList<>(schedule.getAllMatches());
        matches.sort(new Comparator<Match>() {
            public int compare(Match o1, Match o2) {
                return (o1.getDate().isAfter(o2.getDate())) ? 1 : (o1.getDate().equals(o2.getDate())) ? 0 : -1;
            }
        });
        for (Match match : matches) {
            String isPlayedMatch = (match.isPlayed()) ? "сыгран" : "не сыгран";
            if (!match.isPlayed())
                System.out.println("Игрок 1 : " + match.getFirstSide() + "/n" + "Игрок 2 : " + match.getSecondSide() + "/n" + "Дата матча : " + match.getDate().toString() + "Статус матча : " +
                        isPlayedMatch);
            else {
                try {
                    System.out.println("Игрок 1 : " + match.getFirstSide() + "/n" + "Игрок 2 : " + match.getSecondSide() + "/n" + "Дата матча : " + match.getDate().toString() + "Статус матча : " +
                            isPlayedMatch + "/n" + "Очки первого игрока : " + match.getPointsFirstSide() + "/n" + "Очки первого игрока : " + match.getPointsSecondSide() +
                            "/n" + "Победитель : " + match.getWinner());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
