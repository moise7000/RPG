package eu.telecomnancy.rpg;

import java.util.Random;


public class Wizard extends GameCharacter {

    private int intelligence;

    public Wizard(String name) {
        super(name);
        intelligence = getLevel() * 10+new Random().nextInt(10);
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
