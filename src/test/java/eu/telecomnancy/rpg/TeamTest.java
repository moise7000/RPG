package eu.telecomnancy.rpg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TeamTest {

    @Test
    void testTeamBuilder() {
        // Arrange
        GameCharacter char1 = new Wizard("Gandalf");
        GameCharacter char2 = new Wizard("Merlin");

        // Act
        Team team = new Team.TeamBuilder()
                .setName("Fellowship")
                .addPlayer(char1)
                .addPlayer(char2)
                .build();

        // Assert
        Assertions.assertEquals("Fellowship", team.getName());
        Assertions.assertEquals(2, team.getPlayers().size());
        Assertions.assertSame(char1, team.getPlayers().get(0));
        Assertions.assertSame(char2, team.getPlayers().get(1));
    }

    @Test
    void testTeamDuplication() {
        // Arrange
        GameCharacter char1 = new Wizard("Gandalf");
        GameCharacter char2 = new Wizard("Merlin");
        Team originalTeam = new Team.TeamBuilder()
                .setName("Fellowship")
                .addPlayer(char1)
                .addPlayer(char2)
                .build();

        // Act
        Team duplicatedTeam = originalTeam.duplicate();

        // Assert
        Assertions.assertEquals("Fellowship", duplicatedTeam.getName());
        Assertions.assertEquals(2, duplicatedTeam.getPlayers().size());

        // Verify that the players are duplicated, not the same objects
        Assertions.assertNotSame(char1, duplicatedTeam.getPlayers().get(0));
        Assertions.assertNotSame(char2, duplicatedTeam.getPlayers().get(1));

        Assertions.assertTrue(duplicatedTeam.getPlayers().get(0) instanceof Wizard);
        Assertions.assertTrue(duplicatedTeam.getPlayers().get(1) instanceof Wizard);
    }

    @Test
    void testGameCharacterDuplication() {
        // Arrange
        Wizard originalWizard = new Wizard("Gandalf");
        originalWizard.setIntelligence(100);

        // Act
        Wizard duplicatedWizard = (Wizard) originalWizard.duplicate();

        // Assert
        Assertions.assertNotSame(originalWizard, duplicatedWizard);
        Assertions.assertEquals("Gandalf", duplicatedWizard.getName());
        Assertions.assertEquals(100, duplicatedWizard.getIntelligence());
    }

    @Test
    void testWizardDuplication() {
        // Arrange
        Wizard originalWizard = new Wizard("Gandalf");
        originalWizard.setIntelligence(100);
        originalWizard.setHealth(800);
        originalWizard.setExperiencePoints(20);


        // Act
        Wizard duplicatedWizard =  originalWizard.duplicate();

        // Assert
        Assertions.assertNotSame(originalWizard, duplicatedWizard);
        Assertions.assertEquals("Gandalf", duplicatedWizard.getName());
        Assertions.assertEquals(100, duplicatedWizard.getIntelligence());
        Assertions.assertEquals(800, duplicatedWizard.getHealth());
        Assertions.assertEquals(20, duplicatedWizard.getExperiencePoints());

    }

    @Test
    void testWarriorDuplication() {
        // Arrange
        Warrior originalWarrior = new Warrior("Legolas");
        originalWarrior.setStrength(50);
        originalWarrior.setHealth(100);
        originalWarrior.setExperiencePoints(200);

        // Act
        Warrior duplicatedWarrior =  originalWarrior.duplicate();

        //Assert
        Assertions.assertNotSame(originalWarrior, duplicatedWarrior);
        Assertions.assertEquals("Legolas", duplicatedWarrior.getName());
        Assertions.assertEquals(50, duplicatedWarrior.getStrength());
        Assertions.assertEquals(100, duplicatedWarrior.getHealth());
        Assertions.assertEquals(200, duplicatedWarrior.getExperiencePoints());


    }

    @Test
    void testWarriorDuplicationAndModification() {

        // Arrange
        Warrior originalWarrior = new Warrior("Legolas");
        originalWarrior.setStrength(50);
        originalWarrior.setHealth(100);
        originalWarrior.setExperiencePoints(200);


        Warrior duplicatedWarrior =  originalWarrior.duplicate();


        duplicatedWarrior.setExperiencePoints(300);
        duplicatedWarrior.setStrength(30);
        duplicatedWarrior.setHealth(30);


        Assertions.assertNotSame(originalWarrior, duplicatedWarrior);
        Assertions.assertNotEquals(originalWarrior.getStrength(), duplicatedWarrior.getStrength());
        Assertions.assertNotEquals(originalWarrior.getHealth(), duplicatedWarrior.getHealth());
        Assertions.assertNotEquals(originalWarrior.getExperiencePoints(), duplicatedWarrior.getExperiencePoints());

    }
}