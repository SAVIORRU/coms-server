package com.saviorru.comsserver;

import com.saviorru.comsserver.domain.*;
import com.saviorru.comsserver.domain.tournaments.TennisTournament;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static sun.audio.AudioPlayer.player;

public class TournamentReporsTests {
    private Tournament tournament;
    private final int countPlayers = 8;
    private List<Player> playerList;
    private List<Location> locationList;
    private LocationDispatcher locationDispatcher;
    private Schedule schedule;
    private PlayerDispatcher playerDispatcher;
    private DateDispatcher dateDispatcher;
    private SchemeType schemeType;

    @Before
    public void init() throws Exception {
        playerList = new ArrayList<>();
        playerList.add(new Player("Alex", "Kopylov", LocalDate.now()));
        playerList.add(new Player("Jonh", "Doe", LocalDate.now()));
        playerList.add(new Player("Mike", "Cher", LocalDate.now()));
        playerList.add(new Player("Rost", "Rogozin", LocalDate.now()));
        locationList = new ArrayList<>();
        locationList.add(new Location("table1", "1"));
        locationList.add(new Location("table2", "2"));
        locationList.add(new Location("table3", "3"));
        locationDispatcher  = new LocationDispatcher();
        playerDispatcher = new PlayerDispatcher();
        dateDispatcher = new DateDispatcher(LocalDateTime.now(),10,18,1);
        schedule = new ScheduleImpl();
        locationDispatcher.addAllLocation(locationList);
        playerDispatcher.addPlayers(playerList);
        schemeType = SchemeType.OLYMPIC;
        tournament = new TennisTournament(playerDispatcher, locationDispatcher,dateDispatcher,schedule,"tournament1",schemeType);
    }

    @Test()
    public void reportToStringTest() throws Exception
    {
        tournament.start();
        while (tournament.getNextMatch() != null) {
            tournament.finishMatch(tournament.getNextMatch(), new Points(1, 0));
        }
        tournament.finish();
        TournamentReport report = new TournamentReport(tournament);
        System.out.print(report);
    }

}
