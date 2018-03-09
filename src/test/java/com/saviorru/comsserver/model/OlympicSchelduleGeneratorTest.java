package com.saviorru.comsserver.model;

import com.saviorru.comsserver.model.generators.OlympicSchelduleGenerator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OlympicSchelduleGeneratorTest {


    private OlympicSchelduleGenerator olympicGenerator;
    private List<Match> matchesList;
    private List<Player> playersList;
    private LocationDispatcher locationDispatcher;

    @Before
    public void init() {
        olympicGenerator = new OlympicSchelduleGenerator();
        playersList = new ArrayList<>();
        matchesList = new ArrayList<>();
        locationDispatcher = new LocationDispatcher();
        try {
            locationDispatcher.addLocation(new Location("table1", ""));
            locationDispatcher.addLocation(new Location("table2", ""));
            locationDispatcher.addLocation(new Location("table3", ""));
            locationDispatcher.addLocation(new Location("table4", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            playersList.add(new Player("Player1", "1", LocalDate.of(1965, 1, 20)));
            playersList.add(new Player("Player2", "2", LocalDate.of(1965, 1, 19)));
            playersList.add(new Player("Player3", "3", LocalDate.of(1965, 1, 21)));
            playersList.add(new Player("Player4", "4", LocalDate.of(1965, 1, 22)));
            playersList.add(new Player("Player5", "5", LocalDate.of(1965, 1, 23)));
            playersList.add(new Player("Player6", "6", LocalDate.of(1965, 1, 25)));
            playersList.add(new Player("Player7", "7", LocalDate.of(1965, 1, 29)));
            playersList.add(new Player("Player8", "8", LocalDate.of(1965, 1, 2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGenerateScheduleAllLocationsFree() {
        try {
            matchesList = olympicGenerator.generateSchedule(playersList, locationDispatcher
                    , LocalDate.now());
            assertEquals(4, matchesList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGenerateScheduleNotAllLocationsFree() {
        try {
            locationDispatcher.reserveLocation(locationDispatcher.getFreeLocation());
            matchesList = olympicGenerator.generateSchedule(playersList, locationDispatcher
                    , LocalDate.now());
            assertEquals(3, matchesList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateScheduleCreateNewMatch() {
        matchesList = olympicGenerator.generateSchedule(playersList, locationDispatcher
                , LocalDate.now());
        matchesList.get(0).setPoints(10, 11);
        matchesList.get(0).setStateMatch(StateMatch.PLAYED);
        try {
            matchesList.get(0).getLocation().setBusy(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        matchesList.get(1).setPoints(11, 10);
        matchesList.get(1).setStateMatch(StateMatch.PLAYED);
        matchesList = olympicGenerator.updateSchedule(matchesList);
        try {
            matchesList.get(1).getLocation().setBusy(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Match newMatch = new OneOnOneMatch(playersList.get(1), playersList.get(2), matchesList.get(matchesList.size() - 1).getLocation(), matchesList.get(matchesList.size() - 1).getDate());
            assertEquals(newMatch, matchesList.get(matchesList.size() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateScheduleNotCreateNewMatchWhenOneMatchPlayed() {
        matchesList = olympicGenerator.generateSchedule(playersList, locationDispatcher
                , LocalDate.now());
        matchesList.get(0).setPoints(10, 11);
        matchesList.get(0).setStateMatch(StateMatch.PLAYED);
        try {
            matchesList.get(0).getLocation().setBusy(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        matchesList = olympicGenerator.updateSchedule(matchesList);
        assertEquals(4, matchesList.size());
    }

    @Test
    public void testUpdateScheduleNotCreateNewMatchWhenTwoMatchPlayedFromDifferentBranches() {
        matchesList = olympicGenerator.generateSchedule(playersList, locationDispatcher
                , LocalDate.now());
        matchesList.get(0).setPoints(10, 11);
        matchesList.get(0).setStateMatch(StateMatch.PLAYED);
        try {
            matchesList.get(0).getLocation().setBusy(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        matchesList.get(3).setPoints(10, 11);
        matchesList.get(3).setStateMatch(StateMatch.PLAYED);
        try {
            matchesList.get(3).getLocation().setBusy(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        matchesList = olympicGenerator.updateSchedule(matchesList);
        assertEquals(4, matchesList.size());
    }

    @Test
    public void testUpdateScheduleCreateNewMatchOnThirdTour() {
        matchesList = olympicGenerator.generateSchedule(playersList, locationDispatcher
                , LocalDate.now());
        try {
            matchesList.get(0).setPoints(12, 11);
            matchesList.get(0).setStateMatch(StateMatch.PLAYED);
            matchesList.get(0).getLocation().setBusy(false);

            matchesList.get(1).setPoints(12, 11);
            matchesList.get(1).setStateMatch(StateMatch.PLAYED);
            matchesList.get(1).getLocation().setBusy(false);

            matchesList.get(2).setPoints(12, 11);
            matchesList.get(2).setStateMatch(StateMatch.PLAYED);
            matchesList.get(2).getLocation().setBusy(false);

            matchesList.get(3).setPoints(12, 11);
            matchesList.get(3).setStateMatch(StateMatch.PLAYED);
            matchesList.get(3).getLocation().setBusy(false);

            matchesList = olympicGenerator.updateSchedule(matchesList);

            matchesList.get(4).setPoints(12, 11);
            matchesList.get(4).setStateMatch(StateMatch.PLAYED);
            matchesList.get(4).getLocation().setBusy(false);

            matchesList.get(5).setPoints(12, 11);
            matchesList.get(5).setStateMatch(StateMatch.PLAYED);
            matchesList.get(5).getLocation().setBusy(false);

            matchesList = olympicGenerator.updateSchedule(matchesList);

            Match newMatch = new OneOnOneMatch(playersList.get(0), playersList.get(4), matchesList.get(matchesList.size() - 1).getLocation(), matchesList.get(matchesList.size() - 1).getDate());
            assertEquals(newMatch, matchesList.get(matchesList.size() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getGeneratorType() {
    }
}