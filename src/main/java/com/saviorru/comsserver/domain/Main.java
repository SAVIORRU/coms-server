package com.saviorru.comsserver.domain;


import com.saviorru.comsserver.cly.*;
import com.saviorru.comsserver.domain.tournaments.TennisTournament;
import sun.reflect.generics.tree.Tree;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        List<Player> playerList = new ArrayList<>();
        List<Location> locationList = new ArrayList<>();
        locationList.add(new Location("table1", "1"));
        locationList.add(new Location("table2", "2"));
        locationList.add(new Location("table3", "3"));
        LocationDispatcher locationDispatcher = new LocationDispatcher();
        PlayerDispatcher playerDispatcher = new PlayerDispatcher();
        DateDispatcher dateDispatcher = new DateDispatcher(LocalDateTime.now(), 10, 18, 1);
        ScheduleImpl schedule = new ScheduleImpl();
        locationDispatcher.addAllLocation(locationList);
        for (int i = 0; i < 8; i++) {
            playerList.add(new Player("Artem"+i,"Popk"+i, LocalDate.of(1960+i,1,2)));
        }
        playerDispatcher.addPlayers(playerList);
        SchemeType schemeType = SchemeType.OLYMPIC;
        Tournament tournament = new TennisTournament(playerDispatcher, locationDispatcher, dateDispatcher, schedule, "tournament1", schemeType);

        List<Command> commandList = new ArrayList<>();
        commandList.add(new FinishTournamentCommand(tournament));
        commandList.add(new StartTournamentCommand(tournament));
        commandList.add(new ShowGridCommand(tournament));
        commandList.add(new ShowScheduleCommand(tournament));
        commandList.add(new SetMatchResultCommand(tournament,0,0,0));
        commandList.add(new HelpCommand(tournament,commandList));

        CommandManagement commandManagement = new CommandManagement(tournament,commandList);

        CommandParser commandParser = new CommandParser();
        commandParser.addParsingRule("start", new ArrayList<>());
        commandParser.addParsingRule("finish", new ArrayList<>());
        commandParser.addParsingRule("show grid", new ArrayList<>());
        commandParser.addParsingRule("show schedule", new ArrayList<>());
        commandParser.addParsingRule("set match result", Arrays.asList(ArgumentType.DIGIT,ArgumentType.DIGIT, ArgumentType.DIGIT));
        commandParser.addParsingRule("help", new ArrayList<>());

        while(true){

            System.out.print("Введите команду: ");
            command = scanner.nextLine();

            try {
                commandManagement.command(commandParser.parse(command));
            } catch(Exception e){
               System.out.println(Arrays.toString(e.getStackTrace()));
            }
            if(command.equals("exit")) break;
        }
        System.out.println("Программа завершена");
    }

}
