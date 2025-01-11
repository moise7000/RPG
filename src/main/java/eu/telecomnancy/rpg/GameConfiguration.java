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


    private static final class VisitorStats {
        //BuffVisitor stats
        private static final class BuffStats {
            //Warrior buffs
            static final int WARRIOR_HEALTH_BUFF = 10;
            static final int WARRIOR_STRENGTH_BUFF = 10;
            static final int WARRIOR_XP_BUFF = 20;

            //Wizard buffs
            static final int WIZARD_HEALTH_BUFF = 50;
            static final int WIZARD_INTELLIGENCE_BUFF = 30;
            static final int WIZARD_XP_BUFF = 50;

            static final int LEVEL_BUFF = 1;
        }


        //HealVisitor stats
        private static final class HealStats {
            static final int WARRIOR_HEAL_AMOUNT = 5;
            static final int WIZARD_HEAL_AMOUT = 10;
        }

        //DamageVisitor stats
        static final class DamageStats {
            static final int BASE_DAMAGE_AMOUNT = 5;
        }

    }

    //BuffVisitor getters
    public int getWarriorHealthBuff() {return VisitorStats.BuffStats.WARRIOR_HEALTH_BUFF * difficultyLevel;}

    public int getWarriorStrengthBuff() {return VisitorStats.BuffStats.WARRIOR_STRENGTH_BUFF * difficultyLevel;}

    public int getWarriorXpBuff() {return VisitorStats.BuffStats.WARRIOR_XP_BUFF * difficultyLevel;}

    public int getWizardHealthBuff() {return VisitorStats.BuffStats.WIZARD_HEALTH_BUFF * difficultyLevel;}

    public int getWizardIntelligenceBuff() {return VisitorStats.BuffStats.WIZARD_INTELLIGENCE_BUFF * difficultyLevel;}

    public int getWizardXpBuff() {return VisitorStats.BuffStats.WIZARD_XP_BUFF * difficultyLevel;}

    public int getCharacterXpBuff() {
        return difficultyLevel * (VisitorStats.BuffStats.WARRIOR_XP_BUFF + VisitorStats.BuffStats.WIZARD_XP_BUFF)/2;
    }

    public int getLevelBuff() {return VisitorStats.BuffStats.LEVEL_BUFF;}


    //HealVisitor getters
    public int getWarriorHealAmount() {return VisitorStats.HealStats.WARRIOR_HEAL_AMOUNT * difficultyLevel;}

    public int getWizardHealAmount() {return VisitorStats.HealStats.WIZARD_HEAL_AMOUT * difficultyLevel;}

    public  int getCharacterHealAmount() {
        return difficultyLevel * (VisitorStats.HealStats.WARRIOR_HEAL_AMOUNT + VisitorStats.HealStats.WIZARD_HEAL_AMOUT)/2;
    }


    //DamageVisitor getters
    public int getBaseDamageAmount() {return VisitorStats.DamageStats.BASE_DAMAGE_AMOUNT * difficultyLevel;}



    private static final class DecoratorStats {
        static final class ArmorStats {
            static final double BASE_DAMAGE_REDUCTION = 0.25;
            static final double DAMAGE_REDUCTION_PER_LEVEL = 0.05;
        }

        static final class InvincibilityStats {
            static final int BASE_MINIMUM_HEALTH = 10;
            static final int MINIMUM_HEALTH_PER_LEVEL = 5;
        }
    }

    //ArmorDecorator getters
    public double getArmorDamageReduction(int level) {
        double reduction  = DecoratorStats.ArmorStats.BASE_DAMAGE_REDUCTION +
                (DecoratorStats.ArmorStats.DAMAGE_REDUCTION_PER_LEVEL * level);
        //Limit the reduction to 90% maximum
        return Math.min(0.9, reduction * difficultyLevel);
    }


    //InvincibilityDecorator getters
    public int getMinimumHealth(int level) {
        return (DecoratorStats.InvincibilityStats.BASE_MINIMUM_HEALTH +
                (DecoratorStats.InvincibilityStats.MINIMUM_HEALTH_PER_LEVEL * level )) * difficultyLevel;
    }



}
