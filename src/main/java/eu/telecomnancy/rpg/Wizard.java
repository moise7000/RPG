package eu.telecomnancy.rpg;

public class Wizard extends GameCharacter {

    private int intelligence;

    public Wizard(String name) {
        super(name);
        GameConfiguration config = GameConfiguration.getShared();
        setHealth(config.getWizardHealthForLevel(getLevel()));
        intelligence = config.getWizardIntelligenceForLevel(getLevel());
    }

    public Wizard(String name, int intelligence) {
        super(name);
        this.intelligence = intelligence;
    }

    public Wizard(Wizard wizard) {
        super(wizard.getName());
        this.intelligence = wizard.getIntelligence();
    }



    public int getIntelligence() {return intelligence;}

    public void setIntelligence(int intelligence) {this.intelligence = intelligence;}

    @Override
    public void levelUp() {
        super.setLevel(getLevel() + 1);
        GameConfiguration config = GameConfiguration.getShared();
        setIntelligence(config.getWizardIntelligenceForLevel(getLevel()));
        setHealth(config.getWizardHealthForLevel(getLevel()));

    }

    @Override
    public Wizard duplicate() {
        Wizard clonedWizard = new Wizard(this.getName());
        clonedWizard.intelligence = intelligence;
        clonedWizard.setHealth(this.getHealth());
        clonedWizard.setExperiencePoints(this.getExperiencePoints());

        return clonedWizard;
    }

    @Override
    public void accept(CharacterVisitor visitor) {visitor.visit(this);}
}
