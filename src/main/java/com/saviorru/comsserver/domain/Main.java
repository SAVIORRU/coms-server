package com.saviorru.comsserver.domain;


import com.saviorru.comsserver.cly.*;
import com.saviorru.comsserver.cly.command.*;
import com.saviorru.comsserver.domain.dispatcher.DateDispatcher;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.schedule.*;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.tournament.TennisTournament;
import com.saviorru.comsserver.domain.tournament.Tournament;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        ConsoleController controller = new ConsoleController();
        while(true){

            System.out.print("Введите команду: ");
            command = scanner.nextLine();

            try {
                String output = controller.parseAndExecute(command);
                System.out.print("\n");
                System.out.print(output);
                System.out.print("\n");
            } catch(Exception e){
               System.out.println(Arrays.toString(e.getStackTrace()));
            }
            if(command.equals("exit")) break;
        }
        System.out.println("Программа завершена");
    }

}
