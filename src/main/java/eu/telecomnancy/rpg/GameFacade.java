package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.Decorator.Decorators.ArmoredDecorator;
import eu.telecomnancy.rpg.Decorator.Decorators.InvincibleDecorator;
import eu.telecomnancy.rpg.Visitors.BuffVisitor;
import eu.telecomnancy.rpg.Visitors.HealVisitor;

import java.util.*;

public class GameFacade {
    private Map<String, List<GameCharacter>> teams;
    private GameConfiguration config;

    public GameFacade() {
        this.teams = new HashMap<>();
        this.config = GameConfiguration.getShared();
    }

    public void createTeam(String teamName) {
        if(!teams.containsKey(teamName)) {
            throw new IllegalArgumentException("Team " + teamName + " already exists");
        }
        teams.put(teamName, new ArrayList<>());
    }

    public void addCharacterToTeam(String teamName, GameCharacter character) {
        //validateTeam(teamName)
        List<GameCharacter> team = teams.get(teamName);
        if (teams.size() >= config.getMaxTeamSize()) {
            throw new IllegalStateException("Team " + teamName + " is full");
        }
        team.add(character);
    }

    public void removeTeam(String teamName) {
        //validateTeam(teamName)
        teams.remove(teamName);
    }

    public GameCharacter getCharacterFromTeam(String teamName, int index) {
        //validateTeam(teamName)
        return teams.get(teamName).get(index);
    }

    public void attackTeam(String attackingTeam, int attackerIndex, String defendingTeam, int defenderIndex) {
        GameCharacter attacker = getCharacterFromTeam(attackingTeam, attackerIndex);
        GameCharacter defender = getCharacterFromTeam(defendingTeam, defenderIndex);

        if (attacker instanceof Warrior) {
            defender.receiveAttack(((Warrior) attacker).getStrength());
        } else if (attacker instanceof Wizard) {
            defender.receiveAttack(((Wizard) attacker).getIntelligence());
        }
    }

    public void healTeam(String teamName) {
        //validateTeam(teamName)
        CharacterVisitor healVisitor = new HealVisitor();
        for (GameCharacter character : teams.get(teamName)) {
            character.accept(healVisitor);
        }
    }

    public void buffTeam(String teamName) {
        //validateTeam(teamName)
        CharacterVisitor buffVisitor = new BuffVisitor();
        for (GameCharacter character : teams.get(teamName)) {
            character.accept(buffVisitor);
        }
    }

    public void addArmorToCharacter(String teamName, int characterIndex) {
        GameCharacter character = getCharacterFromTeam(teamName, characterIndex);
        teams.get(teamName).set(characterIndex, new ArmoredDecorator(character));
    }

    public void invincibilityToCharacter(String teamName, int characterIndex) {
        GameCharacter character = getCharacterFromTeam(teamName, characterIndex);
        teams.get(teamName).set(characterIndex, new InvincibleDecorator(character));
    }

    private void validateTeam(String teamName) {
        if(!teams.containsKey(teamName)) {
            throw new IllegalArgumentException("Team " + teamName + " does not exist");
        }
    }

    public Map<String, List<GameCharacter>> getTeams() {
        return Collections.unmodifiableMap(teams);
    }


}
