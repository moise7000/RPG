package eu.telecomnancy.rpg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Team {

    private final String name;
    private List<GameCharacter> players;

    //MARK: Getter
    public String getName() {
        return name;
    }

    public GameCharacter getPlayer(String name) {
        for (GameCharacter player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }



    //Private constructor to avoid the non use of the builder
    private Team(TeamBuilder builder){
        this.name = builder.getName();
        this.players = builder.getPlayers();

    }

    public static class TeamBuilder {
        private String name;
        private List<GameCharacter> players;

        public TeamBuilder setName(String name){
            this.name = name;
            return this;

        }

        public TeamBuilder addPlayer(GameCharacter player){
            if(this.players == null){
                this.players = new ArrayList<>();
            }
            this.players.add(player);
            return this;
        }

        public List<GameCharacter> getPlayers(){
            return this.players;
        }

        public String getName() {
            return name;
        }

        public Team build(){
            return new Team(this);
        }
    }





    public Team(String name) {
        this.name = name;
        players=new ArrayList<GameCharacter>();
    }

    public Team(List<GameCharacter> players) {
        this.name = "default";
        this.players=players;

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
