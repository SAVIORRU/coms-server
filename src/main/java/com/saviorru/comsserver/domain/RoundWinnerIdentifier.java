package com.saviorru.comsserver.domain;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Stream;

public class RoundWinnerIdentifier implements WinnerIdentifier {


    public RoundWinnerIdentifier() {
    }

    @Override
    public List<Player> identifyWinner(List<Match> finishedMatches) throws Exception {
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
        List<Player> result = new ArrayList<>();
        for (int i = sortedScores.size() -1; i >= 1; i--)
        {
            result.add(sortedScores.get(i).getKey());
            if (sortedScores.get(i-1).getValue()== sortedScores.get(i).getValue() )
            {
                continue;
            }
            else
            {
                break;
            }
        }
        return result;
    }
}
