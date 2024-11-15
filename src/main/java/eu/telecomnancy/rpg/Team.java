package eu.telecomnancy.rpg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Team implements Duplicable<Team>{

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

    //Helper : Duplicate Constructor
    public Team(Team team) {
        this.name = team.name;
        this.players = new ArrayList<>();
        for (GameCharacter gameCharacter : team.players) {
            this.players.add(gameCharacter.duplicate());
        }
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
            if (this.players == null) {
                return new ArrayList<GameCharacter>();
            }
            return this.players;
        }

        public String getName() {
            return name;
        }

        public Team build(){ return new Team(this); }
    }

    @Override
    public Team duplicate() {
        return new Team(this);
    }


}
            