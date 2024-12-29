package eu.telecomnancy.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameConfigurationTest {

    private GameConfiguration config;

    @BeforeEach
    void setup() {
        config = GameConfiguration.getShared();
    }

    @Test
    void testSingletonInstance() {
        GameConfiguration anotherConfig = GameConfiguration.getShared();
        assertSame(config, anotherConfig, "GameConfiguration should be a singleton");
    }

    @Test
    void testMaxSizeTeam() {
        assertEquals(4, config.getMaxTeamSize());
    }

}



