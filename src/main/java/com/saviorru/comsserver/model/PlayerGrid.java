package com.saviorru.comsserver.model;



import java.util.*;

public interface PlayerGrid {
    public List<Meet> getAllMeets();
    public List<Meet> getUnassignedMeets();
    public List<Meet> getAssignedMeets();
    public void assignMeet(Player firstPlayer, Player secondPlayer) throws Exception;
}
