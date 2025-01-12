package eu.telecomnancy.rpg;


import eu.telecomnancy.rpg.Observers.CharacterEvent;
import eu.telecomnancy.rpg.Observers.CharacterObserver;
import eu.telecomnancy.rpg.Observers.CharacterSubject;
import eu.telecomnancy.rpg.Strategy.CombatStrategy;
import eu.telecomnancy.rpg.Strategy.NeutralStrategy;
import gameInterface.character.CharacterAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant un personnage du jeu.
 * <p>
 * Les personnages du jeu disposent d'attributs essentiels tels que la santé,
 * l'expérience et le niveau. Ils peuvent utiliser des stratégies de combat,
 * notifier des observateurs d'événements et être clonés grâce au pattern Prototype.
 * </p>
 */
public abstract class GameCharacter implements Duplicable<GameCharacter>, CharacterSubject {

    private final String name;
    private int health;
    private int experiencePoints;
    private int level;
    private CombatStrategy combatStrategy;
    private final List<CharacterObserver> observers;
    private CharacterAnimation animations;



    /**
     * Constructeur principal d'un personnage.
     * <p>
     * Initialise les attributs par défaut avec une stratégie neutre
     * et une santé initiale de 100.
     * </p>
     *
     * @param name le nom du personnage.
     */
    public GameCharacter(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.level = 1;
        this.health = 100;
        this.combatStrategy = new NeutralStrategy();
        this.observers = new ArrayList<>();
        this.animations = null;

    }

    /**
     * Constructeur avec des paramètres personnalisés.
     *
     * @param name           le nom du personnage.
     * @param health         les points de vie initiaux.
     * @param combatStrategy la stratégie de combat initiale.
     */
    public GameCharacter(String name, int health, CombatStrategy combatStrategy) {
        this.name = name;
        this.experiencePoints = 0;
        this.level = 1;
        this.health = health;
        this.combatStrategy = combatStrategy;
        this.observers = new ArrayList<>();
        this.animations = null;

    }

    public GameCharacter(String name, int health, CombatStrategy combatStrategy, CharacterAnimation animations) {
        this.name = name;
        this.experiencePoints = 0;
        this.level = 1;
        this.health = health;
        this.combatStrategy = combatStrategy;
        this.animations = animations;
        this.observers = new ArrayList<>();


    }

    /**
     * Ajoute un observateur pour surveiller les événements du personnage.
     *
     * @param observer l'observateur à ajouter.
     */
    @Override
    public void addObserver(CharacterObserver observer) {observers.add(observer);}


    /**
     * Retire un observateur des événements du personnage.
     *
     * @param observer l'observateur à retirer.
     */
    @Override
    public void removeObserver(CharacterObserver observer) {observers.remove(observer);}


    /**
     * Notifie tous les observateurs d'un événement concernant le personnage.
     *
     * @param event l'événement à notifier.
     */
    @Override
    public void notifyObservers(CharacterEvent event) {
        for(CharacterObserver observer : observers){
            observer.update(this, event);
        }
    }





    /**
     * Définit une nouvelle stratégie de combat pour le personnage.
     *
     * @param combatStrategy la stratégie de combat à appliquer.
     */
    public void setCombatStrategy(CombatStrategy combatStrategy) {this.combatStrategy = combatStrategy;}

    /**
     * Calcule les dégâts infligés par le personnage en fonction de sa stratégie.
     *
     * @param baseDamage les dégâts de base.
     * @return les dégâts réels infligés.
     */
    public int attack(int baseDamage) {
        return combatStrategy.calculateDamageDealt(baseDamage);
    }

    /**
     * Reçoit une attaque et met à jour les points de vie en fonction de la stratégie.
     *
     * @param incomingDamage les dégâts reçus.
     */
    public void receiveAttack(int incomingDamage) {
        int actualDamage = combatStrategy.calculateDamageReceived(incomingDamage);
        this.health -= actualDamage;

        if (this.health <= 0) {
            this.health = 0;
            notifyObservers(CharacterEvent.DEATH);
        }
    }

    /**
     * Augmente le niveau du personnage.
     * <p>
     * Cette méthode est à implémenter par les sous-classes pour
     * définir le comportement spécifique lors d'un changement de niveau.
     * </p>
     */
    public abstract void levelUp();

    /**
     * Crée une copie du personnage à l'aide du pattern Prototype.
     *
     * @return une copie du personnage actuel.
     * @throws RuntimeException si la copie échoue.
     */
    @Override
    public GameCharacter duplicate() {
        try {
            return (GameCharacter) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("GameCharacter could not be copied", e);
        }
    }

    /**
     * Retourne le nom du personnage.
     *
     * @return le nom du personnage.
     */
    public String getName() {return name;}

    /**
     * Retourne les points de vie actuels du personnage.
     *
     * @return les points de vie du personnage.
     */
    public int getHealth() {return health;}


    public int getMaxHealth() {return health;}

    /**
     * Définit une nouvelle valeur pour les points de vie du personnage.
     *
     * @param health les nouveaux points de vie.
     */
    public void setHealth(int health) {this.health = health;}


    /**
     * Retourne les points d'expérience accumulés par le personnage.
     *
     * @return les points d'expérience.
     */
    public int getExperiencePoints() {return experiencePoints;}


    /**
     * Définit une nouvelle valeur pour les points d'expérience du personnage.
     *
     * @param experiencePoints les nouveaux points d'expérience.
     */
    public void setExperiencePoints(int experiencePoints) {this.experiencePoints = experiencePoints;}

    /**
     * Retourne le niveau actuel du personnage.
     *
     * @return le niveau du personnage.
     */
    public int getLevel() {return level;}

    /**
     * Définit une nouvelle valeur pour le niveau du personnage.
     *
     * @param level le nouveau niveau.
     */
    public void setLevel(int level) {this.level = level;}

    /**
     * Accepte un visiteur pour exécuter des opérations spécifiques sur le personnage.
     *
     * @param visitor le visiteur, à appliquer au personnage.
     */
    public abstract void accept(CharacterVisitor visitor);

    public CharacterAnimation getAnimations() {return this.animations;}

    public void setAnimations(CharacterAnimation animations) {this.animations = animations;}



    public int getAttackPower() {
        if (this instanceof Warrior) {
            return ((Warrior) this).getStrength();
        } else {
            return ((Wizard) this).getIntelligence();
        }
    }
}