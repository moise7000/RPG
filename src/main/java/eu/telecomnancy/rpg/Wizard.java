package eu.telecomnancy.rpg;

/**
 * Représentation d'un personnage de type Mage (Wizard) dans le jeu.
 * <p>
 * Les mages sont caractérisés par leur intelligence et possèdent des statistiques spécifiques
 * (vie, intelligence) qui évoluent en fonction du niveau.
 * </p>
 */
public class Wizard extends GameCharacter {

    private int intelligence;

    /**
     * Constructeur de base pour créer un mage avec un nom.
     * <p>
     * Ce constructeur initialise la santé et l'intelligence du mage en fonction des configurations
     * globales définies dans {@link GameConfiguration}.
     * </p>
     *
     * @param name le nom du mage.
     */
    public Wizard(String name) {
        super(name);
        GameConfiguration config = GameConfiguration.getShared();
        setHealth(config.getWizardHealthForLevel(getLevel()));
        intelligence = config.getWizardIntelligenceForLevel(getLevel());
    }

    /**
     * Constructeur pour créer un mage avec un nom et une intelligence spécifique.
     *
     * @param name         le nom du mage.
     * @param intelligence  la valeur d'intelligence du mage.
     */
    public Wizard(String name, int intelligence) {
        super(name);
        this.intelligence = intelligence;
    }

    /**
     * Constructeur de copie pour dupliquer un mage.
     *
     * @param wizard le mage à dupliquer.
     */
    public Wizard(Wizard wizard) {
        super(wizard.getName());
        this.intelligence = wizard.getIntelligence();
    }


    /**
     * Retourne l'intelligence du mage.
     *
     * @return l'intelligence du mage.
     */
    public int getIntelligence() {return intelligence;}

    /**
     * Définir l'intelligence du mage.
     *
     * @param intelligence la nouvelle intelligence du mage.
     */
    public void setIntelligence(int intelligence) {this.intelligence = intelligence;}

    /**
     * Incrémente le niveau du mage et met à jour sa santé et son intelligence en fonction
     * de la configuration du jeu.
     */
    @Override
    public void levelUp() {
        super.setLevel(getLevel() + 1);
        GameConfiguration config = GameConfiguration.getShared();
        setIntelligence(config.getWizardIntelligenceForLevel(getLevel()));
        setHealth(config.getWizardHealthForLevel(getLevel()));

    }

    /**
     * Crée une copie du mage.
     *
     * @return un nouveau mage identique à celui-ci.
     */
    @Override
    public Wizard duplicate() {
        Wizard clonedWizard = new Wizard(this.getName());
        clonedWizard.intelligence = intelligence;
        clonedWizard.setHealth(this.getHealth());
        clonedWizard.setExperiencePoints(this.getExperiencePoints());
        clonedWizard.setLevel(this.getLevel());
        clonedWizard.setAnimations(this.getAnimations());
        clonedWizard.setIntelligence(this.getIntelligence());

        return clonedWizard;
    }

    /**
     * Accepte un visiteur de personnage pour appliquer des actions spécifiques.
     *
     * @param visitor le visiteur à appliquer.
     */
    @Override
    public void accept(CharacterVisitor visitor) {visitor.visit(this);}
}
