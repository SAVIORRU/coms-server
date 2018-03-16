package com.saviorru.comsserver.domain;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Stream;

public class RoundWinnerIdentifier implements WinnerIdentifier {


    public RoundWinnerIdentifier() {
    }

    @Override
    public List<List<Player>> identifyWinner(List<Match> finishedMatches) throws Exception {
        if (finishedMatches == null) throw new NullPointerException();

        Map<Player, Integer> playerScores = new HashMap<>();
        for (Match match: finishedMatches)
        {
            if (match == null) throw new NullPointerException();
            Player winner = match.getWinner();
            if (playerScores.containsKey(winner))
            {
                Integer score = playerScores.get(winner);
                score = score + 1;
                playerScores.replace(winner, score);

            }
            else
            {
                playerScores.put(winner, 1);
            }
        }
        Stream<Map.Entry<Player, Integer>> sortedStream = playerScores.entrySet().stream();
        List<Pair<Player, Integer>> sortedScores = new ArrayList<>();
        sortedStream.sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(e ->sortedScores.add(new Pair<Player, Integer>(e.getKey(),e.getValue())));
        List<List<Player>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int changesCount = 0;
        for (int i = sortedScores.size() -1; i >= 1; i--)
        {
            //if (changesCount > 2) break;
            if (!(result.get(changesCount).contains(sortedScores.get(i).getKey())))
                result.get(changesCount).add(sortedScores.get(i).getKey());
            if (sortedScores.get(i).getValue() != sortedScores.get(i-1).getValue() )
            {
                changesCount = changesCount +1;
                if (changesCount > 2) break;
                result.add(new ArrayList<>());
                result.get(changesCount).add(sortedScores.get(i-1).getKey());
            }
        }
        return result;
    }
}
