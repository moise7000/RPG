package eu.telecomnancy.rpg;

public class GameConfiguration {
    private static GameConfiguration shared;

    private int difficultyLevel;
    private int maxTeamSize;




    //Characters statistics
    private static final class WarriorStats {
        static final int BASE_HEALTH = 100;
        static final int BASE_STRENGTH = 50; //AKA damage
        static final int HEALTH_PER_LEVEL = 20;
        static final int STRENGTH_PER_LEVEL = 50;
    }

    private static final class WizardStats {
        static final int BASE_HEALTH = 80;
        static final int BASE_INTELLIGENCE = 15; //AKA damage
        static final int HEALTH_PER_LEVEL = 15;
        static final int INTELLIGENCE_PER_LEVEL = 12;
    }

    private static final class CombatMultipliers {
        //Aggressive strategy
        static final double AGGRESSIVE_DAMAGE_MULTIPLIER = 1.5;
        static final double AGGRESSIVE_DEFENSE_MULTIPLIER = 1.3;

        //Defense strategy
        static final double DEFENSIVE_DAMAGE_MULTIPLIER = 0.7;
        static final double DEFENSIVE_DEFENSE_MULTIPLIER = 0.6;
    }

    private static final class ProgressionStats {
        static final int BAS_XP_GAIN = 100;
        static final int XP_PER_LEVEL = 150;
        static final int XP_GAIN_PER_LEVEL = 50;
        static final int MAX_LEVEL = 20;
    }


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



    public int getWarriorBaseHealth() {return WarriorStats.BASE_HEALTH * difficultyLevel;}

    public int getWarriorBaseStrength() {return WarriorStats.BASE_STRENGTH * difficultyLevel;}

    public int getWarriorHealthForLevel(int level) {
        return getWarriorBaseHealth() + (WarriorStats.HEALTH_PER_LEVEL * (level - 1));
    }

    public int getWarriorStrengthForLevel(int level) {
        return getWarriorBaseStrength() + (WarriorStats.STRENGTH_PER_LEVEL * (level - 1));
    }

    public int getWizardBaseHealth() {return WizardStats.BASE_HEALTH * difficultyLevel;}

    public int getWizardBaseIntelligence() {return WizardStats.BASE_INTELLIGENCE * difficultyLevel;}

    public int getWizardHealthForLevel(int level) {
        return getWizardBaseHealth() + (WarriorStats.HEALTH_PER_LEVEL * (level - 1));
    }

    public int getWizardIntelligenceForLevel(int level) {
        return getWizardBaseIntelligence() + (WizardStats.INTELLIGENCE_PER_LEVEL * (level - 1));
    }

    public double getAggressiveDamageMultiplier() {return CombatMultipliers.AGGRESSIVE_DAMAGE_MULTIPLIER;}

    public double getAggressiveDefenseMultiplier() {return CombatMultipliers.AGGRESSIVE_DEFENSE_MULTIPLIER;}

    public double getDefensiveDamageMultiplier() {return CombatMultipliers.DEFENSIVE_DAMAGE_MULTIPLIER;}

    public double getDefensiveDefenseMultiplier() {return CombatMultipliers.DEFENSIVE_DEFENSE_MULTIPLIER;}



    public int getBaseXpGain() {return ProgressionStats.BAS_XP_GAIN;}

    public int getXpPerLevel() {return ProgressionStats.XP_PER_LEVEL;}

    public int getMaxLevel() {return ProgressionStats.MAX_LEVEL;}

    public int getXpRequiredForLevel(int level) {
        return ProgressionStats.BAS_XP_GAIN + (ProgressionStats.XP_PER_LEVEL * (level - 1));
    }





    public int getDifficultyLevel() {return this.difficultyLevel;}

    public void setDifficultyLevel(int difficultyLevel) {this.difficultyLevel = difficultyLevel;}

    public int getMaxTeamSize() {return this.maxTeamSize;}

    public void setMaxTeamSize(int maxTeamSize) {this.maxTeamSize = maxTeamSize;}


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
