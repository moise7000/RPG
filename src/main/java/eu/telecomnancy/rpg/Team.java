package eu.telecomnancy.rpg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Représentation d'une équipe de personnages dans le jeu.
 * <p>
 * Une équipe peut contenir plusieurs {@link GameCharacter}s et offre des méthodes pour manipuler
 * les membres de l'équipe (ajouter des joueurs, dupliquer l'équipe, etc.).
 * </p>
 */
public class Team implements Duplicable<Team>{

    private final String name;
    private List<GameCharacter> players;

    //Private constructor to avoid the non use of the builder
    /**
     * Constructeur privé pour créer une équipe basée sur un constructeur de type builder.
     *
     * @param builder le constructeur de l'équipe.
     */
    private Team(TeamBuilder builder){
        this.name = builder.getName();
        this.players = builder.getPlayers();

    }

    //Helper : Duplicate Constructor
    /**
     * Constructeur de duplication.
     * <p>
     * Crée une nouvelle instance de l'équipe en copiant les personnages joueurs de l'équipe existante.
     * </p>
     *
     * @param team l'équipe à dupliquer.
     */
    private Team(Team team) {
        this.name = team.name;
        this.players = new ArrayList<>();
        for (GameCharacter gameCharacter : team.players) {
            this.players.add(gameCharacter.duplicate());
        }
    }


    /**
     * Retourne le nom de l'équipe.
     *
     * @return le nom de l'équipe.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la liste des joueurs de l'équipe.
     *
     * @return la liste des personnages joueurs.
     */
    public List<GameCharacter> getPlayers() { return this.players; }

    /**
     * Méthode pour dupliquer l'équipe.
     * <p>
     * Crée une nouvelle instance de l'équipe avec une copie profonde des personnages joueurs.
     * </p>
     *
     * @return une copie de l'équipe.
     */
    @Override
    public Team duplicate() {
        return new Team(this);
    }



    /**
     * Constructeur de l'équipe utilisant un builder.
     * Ce constructeur est privé pour éviter son utilisation directe.
     */
    public static class TeamBuilder {
        private String name;
        private List<GameCharacter> players;

        /**
         * Définir le nom de l'équipe.
         *
         * @param name le nom de l'équipe.
         * @return le builder avec le nom défini.
         */
        public TeamBuilder setName(String name){
            this.name = name;
            return this;

        }

        /**
         * Ajouter un joueur à l'équipe.
         *
         * @param player le personnage joueur à ajouter.
         * @return le builder avec le joueur ajouté.
         */
        public TeamBuilder addPlayer(GameCharacter player){
            if(this.players == null){
                this.players = new ArrayList<>();
            }
            this.players.add(player);
            return this;
        }

        /**
         * Retourne la liste des joueurs de l'équipe.
         *
         * @return la liste des personnages joueurs.
         */
        public List<GameCharacter> getPlayers(){
            if (this.players == null) {
                return new ArrayList<GameCharacter>();
            }
            return this.players;
        }

        /**
         * Retourne le nom de l'équipe.
         *
         * @return le nom de l'équipe.
         */
        public String getName() {
            return name;
        }

        /**
         * Constructeur final pour créer l'équipe à partir du builder.
         *
         * @return l'objet Team construit.
         */
        public Team build(){ return new Team(this); }
    }




}
            