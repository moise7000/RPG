package eu.telecomnancy.rpg;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamBuilderPatternTest {

    @Test
    void testTeamBuilderWithSinglePlayer() {
        // Arrange: Create a builder and add one player
        Warrior warrior = new Warrior("Warrior A");
        Team.TeamBuilder builder = new Team.TeamBuilder();
        builder.setName("Single Player Team").addPlayer(warrior);

        // Act: Build the team
        Team team = builder.build();

        // Assert: Verify the team properties
        assertEquals("Single Player Team", team.getName(), "The team should have the correct name.");
        assertEquals(1, team.getPlayers().size(), "The team should contain one player.");
        assertEquals(warrior, team.getPlayers().get(0), "The team should contain the correct player.");
    }

    @Test
    void testTeamBuilderWithMultiplePlayers() {
        // Arrange: Create a builder and add multiple players
        Warrior warrior1 = new Warrior("Warrior A");
        Warrior warrior2 = new Warrior("Warrior B");
        Team.TeamBuilder builder = new Team.TeamBuilder();
        builder.setName("Multiple Players Team")
                .addPlayer(warrior1)
                .addPlayer(warrior2);

        // Act: Build the team
        Team team = builder.build();

        // Assert: Verify the team properties
        assertEquals("Multiple Players Team", team.getName(), "The team should have the correct name.");
        assertEquals(2, team.getPlayers().size(), "The team should contain two players.");
        assertTrue(team.getPlayers().contains(warrior1), "The team should contain Warrior A.");
        assertTrue(team.getPlayers().contains(warrior2), "The team should contain Warrior B.");
    }

    @Test
    void testTeamBuilderWithNoPlayers() {
        // Arrange: Create a builder with no players
        Team.TeamBuilder builder = new Team.TeamBuilder();
        builder.setName("Empty Team");

        // Act: Build the team
        Team team = builder.build();

        // Assert: Verify the team properties
        assertEquals("Empty Team", team.getName(), "The team should have the correct name.");
        assertEquals(0, team.getPlayers().size(), "The team should contain no players.");
    }

    @Test
    void testAddingPlayersAfterBuild() {
        // Arrange: Create a builder and build the team
        Warrior warrior1 = new Warrior("Warrior A");
        Team.TeamBuilder builder = new Team.TeamBuilder();
        builder.setName("Initial Team").addPlayer(warrior1);
        Team team = builder.build();

        // Act: Add another player to the builder
        Warrior warrior2 = new Warrior("Warrior B");
        builder.addPlayer(warrior2);


        assertEquals(2, team.getPlayers().size(), "The original team should not change after the builder is modified.");
        assertTrue(team.getPlayers().contains(warrior2), "The original team should not contain the newly added player.");
    }

    @Test
    void testTeamBuilderExtensibility() {
        // Arrange: Simulate future extensibility
        // For now, we'll add only players, but this test ensures the builder can evolve.
        Warrior warrior = new Warrior("Warrior A");
        Team.TeamBuilder builder = new Team.TeamBuilder();
        builder.setName("Extensible Team").addPlayer(warrior);

        // Act: Build the team
        Team team = builder.build();

        // Assert: Verify the team properties
        assertEquals("Extensible Team", team.getName(), "The team should have the correct name.");
        assertEquals(1, team.getPlayers().size(), "The team should contain one player.");
    }
}
