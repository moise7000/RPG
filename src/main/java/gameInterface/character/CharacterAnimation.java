package gameInterface.character;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Gère les animations des personnages à partir de feuilles de sprites.
 * <p>
 * Cette classe permet de définir plusieurs animations (idle, attaque, déplacement, etc.)
 * et de passer d'une animation à une autre en fonction de l'état du personnage.
 * <p>
 * Les animations sont créées via des timelines et des spritesheets.
 */
public class CharacterAnimation {
    private final ImageView spriteView;
    private Map<CharacterState, AnimationData> animations;
    private Timeline currentAnimation;
    private CharacterState currentState;
    private Map<CharacterAnimation.CharacterState, Integer> animationsFrameCounts;

    /**
     * Les différents états possibles pour l'animation d'un personnage.
     */
    public enum CharacterState {
        IDLE,
        ATTACK,
        DEATH,
        MOVE,
        HIT
    }

    /**
     * Contient les données associées à une animation : la spritesheet, la timeline et la taille des frames.
     */
    private static class AnimationData {
        final Image spriteSheet;
        final Timeline timeline;
        final int frameWidth;
        final int frameHeight;

        /**
         * Crée un conteneur pour les données d'une animation.
         *
         * @param spriteSheet La feuille de sprites.
         * @param timeline    La timeline de l'animation.
         * @param frameWidth  La largeur d'une frame.
         * @param frameHeight La hauteur d'une frame.
         */
        AnimationData(Image spriteSheet, Timeline timeline, int frameWidth, int frameHeight) {
            this.spriteSheet = spriteSheet;
            this.timeline = timeline;
            this.frameWidth = frameWidth;
            this.frameHeight = frameHeight;
        }
    }

    /**
     * Constructeur de l'animation du personnage.
     * Initialise la vue du sprite et les conteneurs d'animations.
     */
    public CharacterAnimation() {
        this.spriteView = new ImageView();
        this.animations = new HashMap<>();
        this.animationsFrameCounts = new HashMap<>();
        this.currentState = CharacterState.IDLE;


    }

    /**
     * Ajoute une animation pour un état spécifique.
     *
     * @param state         L'état du personnage (ex : ATTACK, MOVE).
     * @param spriteSheetPath Le chemin vers la feuille de sprites.
     * @param frameCount    Le nombre de frames dans l'animation.
     * @param frameWidth    La largeur d'une frame.
     * @param frameHeight   La hauteur d'une frame.
     * @param frameDuration La durée d'affichage de chaque frame.
     */
    public void addAnimation(CharacterState state, String spriteSheetPath, int frameCount, int frameWidth, int frameHeight, Duration frameDuration) {
        Image spriteSheet = new Image(spriteSheetPath);

        Timeline timeline = new Timeline();
        for (int i = 0; i < frameCount; i++) {
            final int frameIndex = i;
            KeyFrame keyFrame = new KeyFrame(
                    frameDuration.multiply(i),
                    event -> updateFrame(state, frameIndex)
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(Animation.INDEFINITE);
        AnimationData animData = new AnimationData(spriteSheet, timeline, frameWidth, frameHeight);
        animations.put(state, animData);
        animationsFrameCounts.put(state, frameCount);

        // Initialiser la première animation
        if (spriteView.getImage() == null) {

            spriteView.setImage(spriteSheet);
            spriteView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        }
    }

    /**
     * Ajoute plusieurs animations en une seule opération.
     *
     * @param states           Liste des états des animations.
     * @param spriteSheetPaths Chemins des feuilles de sprites.
     * @param frameCounts      Nombres de frames pour chaque animation.
     * @param frameWidth       Largeur des frames.
     * @param frameHeight      Hauteur des frames.
     * @param frameDuration    Durée d'affichage de chaque frame.
     * @throws IllegalArgumentException si les tailles des listes ne correspondent pas.
     */
    public void addAnimations(List<CharacterState> states,List<String> spriteSheetPaths,
                              List<Integer> frameCounts, int frameWidth, int frameHeight,
                              Duration frameDuration) {
        if (states.size() != spriteSheetPaths.size() || states.size() != frameCounts.size()) {
            throw new IllegalArgumentException("The size of states, spriteSheetPaths and frameCounts must be the same.");
        }
        for (int i = 0; i < states.size(); i++) {
            addAnimation(states.get(i), spriteSheetPaths.get(i), frameCounts.get(i), frameWidth, frameHeight, frameDuration);
        }
    }

    public int getFrameCount(CharacterState state) {
        return animationsFrameCounts.get(state);
    }





    /**
     * Modifie la frame affichée pour une animation donnée.
     *
     * @param state      L'état de l'animation.
     * @param frameIndex L'index de la frame à afficher.
     */
    private void updateFrame(CharacterState state, int frameIndex) {
        AnimationData animData = animations.get(state);
        if (animData != null) {
            if (spriteView.getImage() != animData.spriteSheet) {
                spriteView.setImage(animData.spriteSheet);
            }
            spriteView.setViewport(new Rectangle2D(
                    frameIndex * animData.frameWidth, 0,
                    animData.frameWidth, animData.frameHeight
            ));
        }
    }

    /**
     * Définit le nouvel état de l'animation et lance celle-ci.
     *
     * @param newState Le nouvel état à activer.
     */
    public void setState(CharacterState newState) {
        if (currentAnimation != null) {
            currentAnimation.stop();
        }

        AnimationData newAnimData = animations.get(newState);
        if (newAnimData != null) {
            spriteView.setImage(newAnimData.spriteSheet);
            currentAnimation = newAnimData.timeline;
            currentState = newState;
            currentAnimation.play();
        }
    }

    public ImageView getSpriteView() {
        return spriteView;
    }

    public CharacterState getCurrentState() {
        return currentState;
    }

    public void stopAnimation() {
        if (currentAnimation != null) {
            currentAnimation.stop();
        }
    }



    public void resumeAnimation() {
        if (currentAnimation != null) {
            currentAnimation.play();
        }
    }
}

/**
 * Gère l'animation d'un sprite simple à partir d'une feuille de sprites.
 */
class SpriteAnimation {
    private final Timeline timeline;
    private final ImageView imageView;
    private final int frameCount;
    private final int frameWidth;
    private final int frameHeight;
    private int currentFrame;

    /**
     * Initialise l'animation du sprite.
     *
     * @param imageView    L'imageView qui affiche le sprite.
     * @param spriteSheet  La feuille de sprites.
     * @param frameCount   Le nombre de frames dans la feuille.
     * @param frameWidth   La largeur d'une frame.
     * @param frameHeight  La hauteur d'une frame.
     * @param frameDuration La durée d'affichage de chaque frame.
     */

    public SpriteAnimation(ImageView imageView, Image spriteSheet, int frameCount, int frameWidth, int frameHeight, Duration frameDuration) {
        this.imageView = imageView;
        this.frameCount = frameCount;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.currentFrame = 0;

        imageView.setImage(spriteSheet);
        imageView.setViewport(new javafx.geometry.Rectangle2D(0, 0, frameWidth, frameHeight));

        timeline = new Timeline(
                new KeyFrame(frameDuration, event -> nextFrame())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private void nextFrame() {
        currentFrame = (currentFrame + 1) % frameCount;
        imageView.setViewport(new javafx.geometry.Rectangle2D(
                currentFrame * frameWidth, 0, frameWidth, frameHeight
        ));
    }

    public void play() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
        currentFrame = 0;
        imageView.setViewport(new javafx.geometry.Rectangle2D(0, 0, frameWidth, frameHeight));
    }
}