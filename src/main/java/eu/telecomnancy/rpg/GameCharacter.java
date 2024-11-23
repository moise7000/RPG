package eu.telecomnancy.rpg;


import eu.telecomnancy.rpg.Observers.CharacterEvent;
import eu.telecomnancy.rpg.Observers.CharacterObserver;
import eu.telecomnancy.rpg.Observers.CharacterSubject;
import eu.telecomnancy.rpg.Strategy.CombatStrategy;
import eu.telecomnancy.rpg.Strategy.NeutralStrategy;

import java.util.ArrayList;
import java.util.List;

public abstract class GameCharacter implements Duplicable<GameCharacter>, CharacterSubject {

    private final String name;
    private int health;
    private int experiencePoints;
    private int level;
    private CombatStrategy combatStrategy;
    private final List<CharacterObserver> observers;


    public GameCharacter(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.level = 1;
        this.health = 100;
        this.combatStrategy = new NeutralStrategy();
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(CharacterObserver observer) {observers.add(observer);}

    @Override
    public void removeObserver(CharacterObserver observer) {observers.remove(observer);}

    @Override
    public void notifyObservers(CharacterEvent event) {
        for(CharacterObserver observer : observers){
            observer.update(this, event);
        }
    }






    public void setCombatStrategy(CombatStrategy combatStrategy) {this.combatStrategy = combatStrategy;}

    public int attack(int baseDamage) {
        return combatStrategy.calculateDamageDealt(baseDamage);
    }

    public void receiveAttack(int incomingDamage) {
        int actualDamage = combatStrategy.calculateDamageReceived(incomingDamage);
        this.health -= actualDamage;

        if (this.health <= 0) {
            this.health = 0;
            notifyObservers(CharacterEvent.DEATH);
        }
    }

    public abstract void levelUp();

    @Override
    public GameCharacter duplicate() {
        try {
            return (GameCharacter) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("GameCharacter could not be copied", e);
        }
    }

    public String getName() {return name;}

    public int getHealth() {return health;}

    public void setHealth(int health) {this.health = health;}

    public int getExperiencePoints() {return experiencePoints;}
    
    public void setExperiencePoints(int experiencePoints) {this.experiencePoints = experiencePoints;}

    public int getLevel() {return level;}

    public void setLevel(int level) {this.level = level;}

    public abstract void accept(CharacterVisitor visitor);
}