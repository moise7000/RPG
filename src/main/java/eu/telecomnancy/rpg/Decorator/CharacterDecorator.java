package eu.telecomnancy.rpg.Decorator;

import eu.telecomnancy.rpg.CharacterVisitor;
import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.Observers.CharacterEvent;
import eu.telecomnancy.rpg.Observers.CharacterObserver;

/**
 * Classe abstraite représentant un décorateur pour un personnage.
 * <p>
 * Cette classe permet de modifier ou d'étendre les fonctionnalités d'un
 * personnage sans altérer la classe d'origine. Elle agit comme un
 * intermédiaire entre le personnage de base et ses comportements modifiés.
 * </p>
 */
public abstract class CharacterDecorator extends GameCharacter {
    protected GameCharacter decoratedCharacter;

/**
 * Constructeur du décorateur de personnage.
 *
 * @param character Le personnage à décorer.
 */
    public CharacterDecorator(GameCharacter character) {
        super(character.getName());
        this.decoratedCharacter = character;
        this.setHealth(character.getHealth());
        this.setLevel(character.getLevel());
        this.setExperiencePoints(character.getExperiencePoints());
    }


    /**
     * Monte le personnage de niveau en propageant l'appel au personnage décoré.
     * <p>
     * Met également à jour les attributs (santé, niveau, points d'expérience)
     * du décorateur pour refléter ceux du personnage décoré après la montée de niveau.
     * </p>
     */
    @Override
    public void levelUp() {
        decoratedCharacter.levelUp();
        this.setHealth(decoratedCharacter.getHealth());
        this.setLevel(decoratedCharacter.getLevel());
        this.setExperiencePoints(decoratedCharacter.getExperiencePoints());
    }

    /**
     * Accepte un visiteur en déléguant au personnage décoré.
     *
     * @param visitor Le visiteur à accepter.
     */
    @Override
    public void accept(CharacterVisitor visitor) {decoratedCharacter.accept(visitor);}


    /**
     * Ajoute un observateur au personnage décoré.
     *
     * @param observer L'observateur à ajouter.
     */
    @Override
    public void addObserver(CharacterObserver observer) {decoratedCharacter.addObserver(observer);}


    /**
     * Supprime un observateur du personnage décoré.
     *
     * @param observer L'observateur à supprimer.
     */
    @Override
    public void removeObserver(CharacterObserver observer) {decoratedCharacter.removeObserver(observer);}


    /**
     * Notifie les observateurs d'un événement concernant le personnage décoré.
     *
     * @param event L'événement à notifier.
     */
    @Override
    public void notifyObservers(CharacterEvent event){decoratedCharacter.notifyObservers(event);}


    /**
     * Retourne le personnage décoré.
     *
     * @return Le personnage décoré.
     */
    public GameCharacter getDecoratedCharacter() {return decoratedCharacter;}


    /**
     * Met à jour le personnage décoré.
     *
     * @param character Le nouveau personnage à décorer.
     */
    public void setDecoratedCharacter(GameCharacter character) {this.decoratedCharacter = character;}

    /**
     * Retire et retourne le personnage décoré.
     *
     * @return Le personnage décoré avant suppression.
     */
    public GameCharacter removeDecoratedCharacter() {return decoratedCharacter;}
}
