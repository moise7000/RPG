package gameInterface.Scenes.GameLoop;



import eu.telecomnancy.rpg.GameCharacter;
import gameInterface.character.CharacterAnimation;

public class PositionManager {
    private static PositionManager instance;
    private final double windowWidth;
    private final double windowHeight;

    private PositionManager(double windowWidth, double windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public static PositionManager getInstance(double windowWidth, double windowHeight) {
        if (instance == null) {
            instance = new PositionManager(windowWidth, windowHeight);
        }
        return instance;
    }

    public void setupPlayerPosition(CharacterAnimation playerAnim) {
        playerAnim.getSpriteView().setX(0);
        playerAnim.getSpriteView().setY(windowHeight - 350);
        playerAnim.getSpriteView().setScaleX(2.0);
        playerAnim.getSpriteView().setScaleY(2.0);
    }

    public void setupEnemyPosition(CharacterAnimation enemyAnim, GameCharacter enemy, int index) {
        double startX = windowWidth - 200;
        double startY = windowHeight - 400;

        if (enemy.getName().equals("Necromancer")) {
            enemyAnim.getSpriteView().setX(startX - (index * 100));
            enemyAnim.getSpriteView().setY(startY);
            enemyAnim.getSpriteView().setScaleX(-2.0);
            enemyAnim.getSpriteView().setScaleY(2.0);
        } else {
            enemyAnim.getSpriteView().setX(startX - (index * 100));
            enemyAnim.getSpriteView().setY(startY + 80);
            enemyAnim.getSpriteView().setScaleX(-4.0);
            enemyAnim.getSpriteView().setScaleY(4.0);
        }
    }

    public double getPlayerInitialX() {
        return 0;
    }

    public double getPlayerInitialY() {
        return windowHeight - 350;
    }
}
