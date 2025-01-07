package gameInterface.Scenes.GameLoop;




import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameConfiguration;
import gameInterface.InterfaceConfiguration;
import gameInterface.character.CharacterAnimation;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EnemyManager {

    private List<GameCharacter> enemies = new ArrayList<>();
    private List<CharacterAnimation> enemyAnimations = new ArrayList<>();

    public List<GameCharacter> createEnemies(int level) {
        List<GameCharacter> enemyList = new ArrayList<>();
        int enemyCount = Math.min(level, 3);
        double p = 0.5;

        for (int i = 0; i < enemyCount; i++) {
            Random random = new Random();
            if (random.nextDouble() < p) {
                GameCharacter enemy = InterfaceConfiguration.getShared().createNecromancerGameCharacter();
                enemy.setHealth(50 * level);
                enemyList.add(enemy);
            } else {
                GameCharacter enemy = InterfaceConfiguration.getShared().createNightBorneGameCharacter();
                enemy.setHealth(50 * level);
                enemyList.add(enemy);
            }
        }
        return enemyList;
    }

    public void setupEnemiesPosition(List<GameCharacter> enemies, Pane gameContainer, InterfaceConfiguration config) {
        double startX = config.getWindowWidth() - 200;
        double startY = config.getWindowHeight() - 400;

        for (int i = 0; i < enemies.size(); i++) {
            CharacterAnimation enemyAnim = enemies.get(i).getAnimations();
            GameCharacter enemy = enemies.get(i);
            if (Objects.equals(enemy.getName(), "Necromancer")) {
                enemyAnim.getSpriteView().setX(startX - (i * 100));
                enemyAnim.getSpriteView().setY(startY);
                enemyAnim.getSpriteView().setScaleX(-2.0);
                enemyAnim.getSpriteView().setScaleY(2.0);
            } else {
                enemyAnim.getSpriteView().setX(startX - (i * 100));
                enemyAnim.getSpriteView().setY(startY + 80);
                enemyAnim.getSpriteView().setScaleX(-4.0);
                enemyAnim.getSpriteView().setScaleY(4.0);
            }

            enemyAnimations.add(enemyAnim);
            gameContainer.getChildren().add(enemyAnim.getSpriteView());
        }
    }

    public List<CharacterAnimation> getEnemyAnimations() {
        return enemyAnimations;
    }

    public void handleEnemyTurn(Pane gameContainer, double targetX, Duration duration) {
        GameCharacter weakestEnemy = enemies.stream()
                .min((e1, e2) -> Integer.compare(e1.getHealth(), e2.getHealth()))
                .orElse(null);

        if (weakestEnemy == null) return;

        CharacterAnimation enemyAnimation = weakestEnemy.getAnimations();
        enemyAnimation.setState(CharacterAnimation.CharacterState.MOVE);

        TranslateTransition moveToPlayer = new TranslateTransition(duration, enemyAnimation.getSpriteView());
        moveToPlayer.setToX(targetX);
        moveToPlayer.setOnFinished(event -> {
            //CharacterAnimationManager.performHitAnimation(enemyAnimation);
            int damageForPlayer = weakestEnemy.attack(GameConfiguration.getShared().getBaseDamageAmount());
            // Assume performHitAnimation handles damage application to player
            // Implement processDamage method if needed
        });

        moveToPlayer.play();
    }
}

