package eu.telecomnancy.rpg.Visitors;

import eu.telecomnancy.rpg.Warrior;
import eu.telecomnancy.rpg.Wizard;

public interface CharacterVisitor {


    void visit(Warrior warrior);
    void visit(Wizard wizard);
}
