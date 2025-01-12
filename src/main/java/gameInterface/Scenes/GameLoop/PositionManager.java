package gameInterface.Scenes.GameLoop;



import eu.telecomnancy.rpg.GameCharacter;
import gameInterface.character.CharacterAnimation;

/**
 * Gère la position des personnages dans la fenêtre du jeu.
 * Cette classe s'occupe de l'initialisation des positions du joueur et des ennemis.
 * Les positions sont définies en fonction de la taille de la fenêtre du jeu.
 */
public class PositionManager {
    private static PositionManager instance;
    private final double windowWidth;
    private final double windowHeight;

    private PositionManager(double windowWidth, double windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    /**
     * Obtient l'instance unique de {@code PositionManager}, ou crée une nouvelle instance si elle n'existe pas.
     *
     * @param windowWidth La largeur de la fenêtre du jeu
     * @param windowHeight La hauteur de la fenêtre du jeu
     * @return L'instance unique de `PositionManager`
     */
    public static PositionManager getInstance(double windowWidth, double windowHeight) {
        if (instance == null) {
            instance = new PositionManager(windowWidth, windowHeight);
        }
        return instance;
    }

    /**
     * Configure la position initiale du joueur dans la fenêtre du jeu.
     * Le joueur est placé en bas à gauche de l'écran avec une mise à l'échelle spécifique.
     *
     * @param playerAnim L'animation du personnage du joueur
     */
    public void setupPlayerPosition(CharacterAnimation playerAnim) {
        playerAnim.getSpriteView().setX(0);
        playerAnim.getSpriteView().setY(windowHeight - 350);
        playerAnim.getSpriteView().setScaleX(2.0);
        playerAnim.getSpriteView().setScaleY(2.0);
    }

    /**
     * Configure la position initiale d'un ennemi dans la fenêtre du jeu.
     * L'ennemi est placé à droite de l'écran avec une mise à l'échelle spécifique en fonction de son type.
     *
     * @param enemyAnim L'animation du personnage de l'ennemi
     * @param enemy Le personnage ennemi
     * @param index L'indice de l'ennemi, utilisé pour décaler la position si plusieurs ennemis sont présents
     */
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
