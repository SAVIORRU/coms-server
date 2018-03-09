package com.saviorru.comsserver.model.generators;

import com.saviorru.comsserver.model.OlympicScheme;
import com.saviorru.comsserver.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class OlympicSchelduleGenerator implements ScheduleGenerator {

    private List<Match> matchList;
    private List<Player> playersLists;
    private LocalDate dateBegin;
    private LocationDispatcher locationDispatcher;
    private OlympicScheme olympicScheme;
    private Integer countPlayers;
    private List<OlympicScheme.Node> child, parents = new ArrayList<>();

    public OlympicSchelduleGenerator() {
    }

    private void init(List<Player> playersLists, LocationDispatcher locationDispatcher, LocalDate startDate) {
        this.playersLists = playersLists;
        this.matchList = new ArrayList<>();
        this.dateBegin = startDate;
        this.locationDispatcher = locationDispatcher;
        this.countPlayers = playersLists.size();
        this.olympicScheme = new OlympicScheme(countTour(this.countPlayers));
        this.child = this.olympicScheme.getFirstRound();
        addPlayers(this.playersLists);
    }

    private int countTour(int countPlayers) {
        int count = 0, st = 2, n = 0;
        do {
            n++;
            count = (int)Math.pow(st, n);
        }while (count < countPlayers);
        return n;
    }

    private void addPlayers(List<Player> playersLists) {
        if (this.countPlayers == Math.pow(2, countTour(this.countPlayers)))
            fillFirstTourStandardCountPlayer(playersLists);
        else {
            fillFirstTourNotStandardCountPlayer(playersLists);
        }
    }

    private void fillFirstTourNotStandardCountPlayer(List<Player> playersLists) {
        for (int i = 0, j = 0; j < this.child.size(); i += 2, j += 4) {
            this.child.get(j).data = playersLists.get(i);
            this.child.get(j + 1).data = playersLists.get(i + 1);
            try {
                if (locationDispatcher.getFreeLocation() != null)
                    this.matchList.add(createNewMatch(this.child.get(j).data, this.child.get(j + 1).data));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = this.matchList.size() * 2, j = 2; j < this.child.size(); i++, j += 4) {
            this.child.get(j).data = playersLists.get(i);
            addPlayersNextTour(this.child.get(j));
        }
    }

    private void fillFirstTourStandardCountPlayer(List<Player> playersLists) {
        for (int i = 0; i < playersLists.size()-1; i += 2) {
            this.child.get(i).data = playersLists.get(i);
            this.child.get(i + 1).data = playersLists.get(i + 1);
            try {
                if (locationDispatcher.getFreeLocation() != null)
                    this.matchList.add(createNewMatch(this.child.get(i).data, this.child.get(i + 1).data));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private OlympicScheme.Node addPlayersNextTour(OlympicScheme.Node node) {
        if (node.nextPositionPlayer.rightPlayer.data == null || node.nextPositionPlayer.leftPlayer.data == null) {
            if (!checkLackPartner(node)) {
                if (node.nextPositionPlayer.data == null) {
                    node.nextPositionPlayer.data = node.data;
                    return node.nextPositionPlayer;
                }
            }
        }
        return node;
    }

    private boolean checkLackPartner(OlympicScheme.Node node) {
        if ((node.nextPositionPlayer.rightPlayer.rightPlayer == null && node.nextPositionPlayer.rightPlayer.leftPlayer == null) || (node.nextPositionPlayer.rightPlayer.rightPlayer.data == null && node.nextPositionPlayer.rightPlayer.leftPlayer.data == null)) {
            if ((node.nextPositionPlayer.leftPlayer.rightPlayer == null && node.nextPositionPlayer.leftPlayer.leftPlayer == null) || (node.nextPositionPlayer.leftPlayer.rightPlayer.data == null && node.nextPositionPlayer.leftPlayer.leftPlayer.data == null)) {
                return false;
            }
        }
        return true;
    }

    private List<Match> createSchedule(List<Match> matches, List<OlympicScheme.Node> parents) {
        int countPLayedMatch = 0;
        for (int i = matches.size() - 1; i >= 0; i--) {
            if (matchList.get(i).isPlayed()){
                countPLayedMatch++;
                try {
                    addInSceme(parents,matchList.get(i).getWinner());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (countPLayedMatch == this.matchList.size() && parents.size() > 1) {
            createSchedule(this.matchList, initParents(parents));
        }
        return this.matchList;
    }

    private ArrayList<OlympicScheme.Node> initParents(List<OlympicScheme.Node> child) {
        ArrayList<OlympicScheme.Node> arrayList = new ArrayList<>();
        for (int i = 0; i < child.size(); i += 2) {
            arrayList.add(child.get(i).nextPositionPlayer);
        }
        return arrayList;
    }

    private boolean addInSceme(List<OlympicScheme.Node> child, Player winner) {
        OlympicScheme.Node node;
        for(int i = 0;i < child.size(); i++){
            if(child.get(i).data == winner){
                if(child.get(i).nextPositionPlayer.data == null) {
                    child.get(i).nextPositionPlayer.data = winner;
                    node = addPlayersNextTour(child.get(i).nextPositionPlayer);
                    node = finedTour(node);
                    if (node != null) {
                        try {
                            if (noClone(createMockMatch(node.leftPlayer.data, node.rightPlayer.data)))
                                this.matchList.add(createNewMatch(node.leftPlayer.data, node.rightPlayer.data));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean noClone(Match match) {
        for (int i = 0; i < this.matchList.size(); i++) {
            if(match.equals(this.matchList.get(i))) return false;
        }
        return true;
    }

    private Match createMockMatch(Player firstSide, Player secondSide) throws Exception {
        Location newLocation = locationDispatcher.getFreeLocation();
        return new OneOnOneMatch(firstSide, secondSide, newLocation, LocalDateTime.now());
    }
    private Match createNewMatch(Player firstSide, Player secondSide) throws Exception {
        Location newLocation = locationDispatcher.getFreeLocation();
        locationDispatcher.reserveLocation(newLocation);
        return new OneOnOneMatch(firstSide, secondSide, newLocation, LocalDateTime.now());
    }

    private OlympicScheme.Node finedTour(OlympicScheme.Node node) {
        if (node.nextPositionPlayer.rightPlayer.data != null && node.nextPositionPlayer.leftPlayer.data != null) {
            if (node.nextPositionPlayer.data == null) return node.nextPositionPlayer;
        }
        return null;
    }


    @Override
    public List<Match> generateSchedule(List<Player> playersLists, LocationDispatcher locationDispatcher, LocalDate startDate) {
        init(playersLists, locationDispatcher, startDate);
        return this.matchList;
    }

    @Override
    public List<Match> updateSchedule(List<Match> matches) {
        return createSchedule(matches, this.child);
    }


}
