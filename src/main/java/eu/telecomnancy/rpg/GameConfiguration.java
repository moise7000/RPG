package eu.telecomnancy.rpg;

public class GameConfiguration {
    private static GameConfiguration shared;

    private int difficultyLevel;
    private int maxTeamSize;

    private GameConfiguration() {
        this.difficultyLevel = 1;
        this.maxTeamSize = 4;
    }


    public static GameConfiguration getShared() {
        if (shared == null) {
            shared = new GameConfiguration();
        }
        return shared;
    }


    public int getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getMaxTeamSize() {
        return this.maxTeamSize;
    }

    public void setMaxTeamSize(int maxTeamSize) {
        this.maxTeamSize = maxTeamSize;
    }


    public CharacterCreator getCharacterCreator() {
        if (this.difficultyLevel == 1) {
            return new WarriorCreator();
        } else {
            return new WizardCreator();
        }
    }




    public static void main(String[] args) {
        GameConfiguration config = new GameConfiguration();
        config.getCharacterCreator().create("Warrior");
    }

}
