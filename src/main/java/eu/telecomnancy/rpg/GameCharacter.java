package eu.telecomnancy.rpg;


public abstract class GameCharacter implements Duplicable<GameCharacter> {

    private final String name;
    private int health;
    private int experiencePoints;
    private int level;


    public GameCharacter(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.level = 1;
    }

    @Override
    public GameCharacter duplicate() {
        try {
            return (GameCharacter) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("GameCharacter could not be copied", e);
        }
    }

    public String getName() { return name; }

    public int getHealth() { return health; }

    public void setHealth(int health) { this.health = health; }

    public int getExperiencePoints() {
        return experiencePoints;
    }
    
    public void setExperiencePoints(int experiencePoints) { this.experiencePoints = experiencePoints; }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public abstract void accept(CharacterVisitor visitor);
}