package eu.telecomnancy.rpg;

public interface CharacterVisitor {
    void visit(Character character);

    void visit(Warrior warrior);
    void visit(Wizard wizard);
}
