package eu.telecomnancy.rpg;

import java.util.ArrayList;
import java.util.Collection;



public class Team {

    private final String name;

    private Collection<GameCharacter> players;

    public Team(String name) {
        this.name = name;
        players=new ArrayList<GameCharacter>();
    }

    public String getName() {
        return name;
    }
    
    public Collection<GameCharacter> getPlayers() {
        return players;
    }
    public void addPlayer(GameCharacter player) {
        players.add(player);
    }

    public void removePlayer(GameCharacter player) {
        players.remove(player);
    }

    public void removePlayer(String name) {
        for (GameCharacter player : players) {
            if (player.getName().equals(name)) {
                players.remove(player);
                return;
            }
        }
    }

    public GameCharacter getPlayer(String name) {
        for (GameCharacter player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public boolean containsPlayer(String name) {
        for (GameCharacter player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPlayer(GameCharacter player) {
        return players.contains(player);
    }

    public int size() {
        return players.size();
    }
}
