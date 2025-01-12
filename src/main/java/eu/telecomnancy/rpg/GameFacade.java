package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.Decorator.Decorators.ArmoredDecorator;
import eu.telecomnancy.rpg.Decorator.Decorators.InvincibleDecorator;
import eu.telecomnancy.rpg.Visitors.BuffVisitor;
import eu.telecomnancy.rpg.Visitors.HealVisitor;

import java.util.*;

/**
 * La classe {@code GameFacade} centralise et simplifie les interactions avec les équipes et les personnages du jeu.
 * <p>
 * Elle encapsule les fonctionnalités principales du jeu telles que la gestion des équipes,
 * les combats, les soins, les améliorations et les décorateurs de personnages.
 * <p>
 * Cette classe suit le **pattern Façade** pour fournir une interface unifiée.
 */
public class GameFacade {
    private Map<String, List<GameCharacter>> teams;
    private GameConfiguration config;

    /**
     * Constructeur de la façade du jeu.
     * Initialise les équipes et récupère la configuration du jeu.
     */
    public GameFacade() {
        this.teams = new HashMap<>();
        this.config = GameConfiguration.getShared();
    }

    /**
     * Crée une nouvelle équipe avec le nom spécifié.
     *
     * @param teamName Le nom de l'équipe à créer.
     * @throws IllegalArgumentException si l'équipe existe déjà.
     */
    public void createTeam(String teamName) {
        if(!teams.containsKey(teamName)) {
            throw new IllegalArgumentException("Team " + teamName + " already exists");
        }
        teams.put(teamName, new ArrayList<>());
    }

    /**
     * Ajoute un personnage à une équipe existante.
     *
     * @param teamName Le nom de l'équipe.
     * @param character Le personnage, à ajouter.
     * @throws IllegalStateException si l'équipe est pleine.
     */
    public void addCharacterToTeam(String teamName, GameCharacter character) {
        //validateTeam(teamName)
        List<GameCharacter> team = teams.get(teamName);
        if (teams.size() >= config.getMaxTeamSize()) {
            throw new IllegalStateException("Team " + teamName + " is full");
        }
        team.add(character);
    }

    /**
     * Supprime une équipe du jeu.
     *
     * @param teamName Le nom de l'équipe à supprimer.
     */
    public void removeTeam(String teamName) {
        validateTeam(teamName);
        teams.remove(teamName);
    }

    /**
     * Récupère un personnage d'une équipe en fonction de son index.
     *
     * @param teamName Le nom de l'équipe.
     * @param index    L'index du personnage dans l'équipe.
     * @return Le personnage ciblé.
     */
    public GameCharacter getCharacterFromTeam(String teamName, int index) {
        //validateTeam(teamName)
        return teams.get(teamName).get(index);
    }


    /**
     * Permet à un personnage d'une équipe d'attaquer un personnage d'une autre équipe.
     *
     * @param attackingTeam  Le nom de l'équipe attaquante.
     * @param attackerIndex  L'index du personnage attaquant.
     * @param defendingTeam  Le nom de l'équipe défensive.
     * @param defenderIndex  L'index du personnage défenseur.
     */
    public void attackTeam(String attackingTeam, int attackerIndex, String defendingTeam, int defenderIndex) {
        GameCharacter attacker = getCharacterFromTeam(attackingTeam, attackerIndex);
        GameCharacter defender = getCharacterFromTeam(defendingTeam, defenderIndex);

        if (attacker instanceof Warrior) {
            defender.receiveAttack(((Warrior) attacker).getStrength());
        } else if (attacker instanceof Wizard) {
            defender.receiveAttack(((Wizard) attacker).getIntelligence());
        }
    }

    /**
     * Soigne tous les personnages d'une équipe.
     *
     * @param teamName Le nom de l'équipe à soigner.
     */
    public void healTeam(String teamName) {
        //validateTeam(teamName)
        CharacterVisitor healVisitor = new HealVisitor();
        for (GameCharacter character : teams.get(teamName)) {
            character.accept(healVisitor);
        }
    }

    /**
     * Applique un buff à tous les personnages d'une équipe.
     *
     * @param teamName Le nom de l'équipe à améliorer.
     */

    public void buffTeam(String teamName) {
        validateTeam(teamName);
        CharacterVisitor buffVisitor = new BuffVisitor();
        for (GameCharacter character : teams.get(teamName)) {
            character.accept(buffVisitor);
        }
    }

    /**
     * Ajoute une armure à un personnage d'une équipe.
     *
     * @param teamName      Le nom de l'équipe.
     * @param characterIndex L'index du personnage à protéger.
     */
    public void addArmorToCharacter(String teamName, int characterIndex) {
        GameCharacter character = getCharacterFromTeam(teamName, characterIndex);
        teams.get(teamName).set(characterIndex, new ArmoredDecorator(character));
    }

    /**
     * Rend un personnage invincible dans une équipe.
     *
     * @param teamName      Le nom de l'équipe.
     * @param characterIndex L'index du personnage à rendre invincible.
     */
    public void invincibilityToCharacter(String teamName, int characterIndex) {
        GameCharacter character = getCharacterFromTeam(teamName, characterIndex);
        teams.get(teamName).set(characterIndex, new InvincibleDecorator(character));
    }

    /**
     * Vérifie si une équipe existe.
     *
     * @param teamName Le nom de l'équipe.
     * @throws IllegalArgumentException si l'équipe n'existe pas.
     */
    private void validateTeam(String teamName) {
        if(!teams.containsKey(teamName)) {
            throw new IllegalArgumentException("Team " + teamName + " does not exist");
        }
    }

    /**
     * Retourne une vue non modifiable des équipes du jeu.
     *
     * @return Une carte des équipes avec leurs personnages.
     */
    public Map<String, List<GameCharacter>> getTeams() {
        return Collections.unmodifiableMap(teams);
    }


}
