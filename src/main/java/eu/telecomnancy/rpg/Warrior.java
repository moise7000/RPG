package eu.telecomnancy.rpg;


/**
 * Représente un guerrier dans le système de jeu.
 * <p>
 * Les guerriers sont des personnages avec une force physique élevée
 * et des points de vie adaptés à leur niveau. Ils peuvent évoluer en
 * montant de niveau et être clonés grâce au pattern Prototype.
 * </p>
 */
public class Warrior extends GameCharacter {
    private int strength;

    /**
     * Constructeur du guerrier.
     * <p>
     * Initialise le guerrier avec un nom, des points de vie et une force
     * correspondant à son niveau initial selon les configurations du jeu.
     * </p>
     *
     * @param name le nom du guerrier.
     */
    public Warrior(String name) {
        super(name);
        GameConfiguration config = GameConfiguration.getShared();
        setHealth(config.getWarriorHealthForLevel(getLevel()));
        strength = config.getWarriorStrengthForLevel(getLevel());
    }

    /**
     * Obtient la force actuelle du guerrier.
     *
     * @return la force du guerrier.
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Définit une nouvelle valeur pour la force du guerrier.
     *
     * @param strength la nouvelle force à assigner.
     */
    public void setStrength(int strength) {this.strength = strength;}


    /**
     * Augmente le niveau du guerrier.
     * <p>
     * Cette méthode met à jour les points de vie et la force
     * en fonction du nouveau niveau, selon les paramètres du jeu.
     * </p>
     */
    @Override
    public void levelUp() {
        super.setLevel(getLevel() + 1);
        GameConfiguration config = GameConfiguration.getShared();
        setHealth(config.getWarriorHealthForLevel(getLevel()));
        strength = config.getWarriorStrengthForLevel(getLevel());
    }

    /**
     * Clone le guerrier en créant une copie identique de celui-ci.
     * <p>
     * Utilise le pattern Prototype pour reproduire un guerrier avec
     * les mêmes caractéristiques.
     * </p>
     *
     * @return un nouveau guerrier identique à l'original.
     */
    @Override
    public Warrior duplicate() {
        Warrior clonedWarrior = new Warrior(this.getName());

        clonedWarrior.strength = this.strength;
        clonedWarrior.setHealth(this.getHealth());
        clonedWarrior.setExperiencePoints(this.getExperiencePoints());
        clonedWarrior.setLevel(this.getLevel());
        clonedWarrior.setAnimations(this.getAnimations());

        return clonedWarrior;

    }

    /**
     * Accepte un visiteur pour appliquer des actions spécifiques au guerrier.
     *
     * @param visitor l'objet visiteur qui effectue des opérations sur le guerrier.
     */
    @Override
    public void accept(CharacterVisitor visitor) {visitor.visit(this);}


}
