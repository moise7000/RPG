package eu.telecomnancy.rpg;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrototypeTest {

    @Test
    void testCharacterCloning() {
        // Arrange: Create a Warrior and clone it
        Warrior originalWarrior = new Warrior("Original Warrior");
        originalWarrior.setExperiencePoints(500);
        originalWarrior.setLevel(10);

        // Act: Clone the warrior
        Warrior clonedWarrior = originalWarrior.duplicate();

        // Assert: Verify the clone is identical but not the same object
        assertNotSame(originalWarrior, clonedWarrior, "The clone should be a different object.");
        assertEquals(originalWarrior.getName(), clonedWarrior.getName(), "The clone should have the same name.");
        assertEquals(originalWarrior.getLevel(), clonedWarrior.getLevel(), "The clone should have the same level.");
        assertEquals(originalWarrior.getExperiencePoints(), clonedWarrior.getExperiencePoints(), "The clone should have the same experience points.");
        assertEquals(originalWarrior.getHealth(), clonedWarrior.getHealth(), "The clone should have the same health.");
    }

    @Test
    void testTeamCloning() {
        // Arrange: Create a team with two players
        Warrior warrior = new Warrior("Warrior A");
        Warrior anotherWarrior = new Warrior("Warrior B");
        Team.TeamBuilder builder = new Team.TeamBuilder();
        builder.setName("Original Team").addPlayer(warrior).addPlayer(anotherWarrior);
        Team originalTeam = builder.build();

        // Act: Clone the team
        Team clonedTeam = originalTeam.duplicate();

        // Assert: Verify the team clone is identical but not the same object
        assertNotSame(originalTeam, clonedTeam, "The cloned team should be a different object.");
        assertEquals(originalTeam.getName(), clonedTeam.getName(), "The cloned team should have the same name.");
        assertNotSame(originalTeam.getPlayers(), clonedTeam.getPlayers(), "The player lists should be different objects.");
        assertEquals(originalTeam.getPlayers().size(), clonedTeam.getPlayers().size(), "The cloned team should have the same number of players.");

        // Assert: Verify each player is also cloned
        for (int i = 0; i < originalTeam.getPlayers().size(); i++) {
            GameCharacter originalPlayer = originalTeam.getPlayers().get(i);
            GameCharacter clonedPlayer = clonedTeam.getPlayers().get(i);

            assertNotSame(originalPlayer, clonedPlayer, "Each player in the team should be a cloned object.");
            assertEquals(originalPlayer.getName(), clonedPlayer.getName(), "Each cloned player should have the same name.");
        }
    }

    @Test
    void testPrototypeForDefaultTeamCreation() {
        // Arrange: Create a prototype team
        Warrior prototypeWarrior = new Warrior("Prototype Warrior");
        Team.TeamBuilder prototypeBuilder = new Team.TeamBuilder();
        prototypeBuilder.setName("Prototype Team").addPlayer(prototypeWarrior);
        Team prototypeTeam = prototypeBuilder.build();

        // Act: Clone the prototype to create a new team
        Team newTeam = prototypeTeam.duplicate();

        // Assert: Verify the new team is based on the prototype but is a separate object
        assertNotSame(prototypeTeam, newTeam, "The new team should be a different object.");
        assertEquals(prototypeTeam.getName(), newTeam.getName(), "The new team should have the same name as the prototype.");
        assertEquals(prototypeTeam.getPlayers().size(), newTeam.getPlayers().size(), "The new team should have the same number of players.");

        // Assert: Modifications to the new team don't affect the prototype
        newTeam.getPlayers().get(0).setHealth(200); // Modify the first player's health in the new team
        assertNotEquals(prototypeTeam.getPlayers().get(0).getHealth(), newTeam.getPlayers().get(0).getHealth(),
                "Modifications to the new team should not affect the prototype.");
    }
}

