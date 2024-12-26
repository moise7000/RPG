package gameInterface.character;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CharacterAnimation {
    private final ImageView spriteView;
    private Timeline currentAnimation;
    private CharacterState currentState;

    public enum CharacterState {
        IDLE,
        ATTACK,
        DEATH,
        MOVE,
        HIT
    }

    public CharacterAnimation() {
        this.spriteView = new ImageView();
        this.currentState = CharacterState.IDLE;
        //spriteView.setSmooth(false);
        spriteView.setStyle("-fx-interpolation-hint: nearest-neighbor");
    }

    public void addAnimation(CharacterState state, String spriteSheetPath, int frameCount, int frameWidth, int frameHeight, Duration frameDuration) {
        if (state == currentState) {
            Image spriteSheet = new Image(spriteSheetPath);
            spriteView.setImage(spriteSheet);
            spriteView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));

            // Stop any existing animation
            if (currentAnimation != null) {
                currentAnimation.stop();
            }

            // Create new animation timeline
            currentAnimation = new Timeline();

            for (int i = 0; i < frameCount; i++) {
                final int frameIndex = i;
                KeyFrame keyFrame = new KeyFrame(
                        frameDuration.multiply(i),
                        event -> updateFrame(frameIndex, frameWidth, frameHeight)
                );
                currentAnimation.getKeyFrames().add(keyFrame);
            }

            // Configure animation properties
            currentAnimation.setCycleCount(Animation.INDEFINITE);
            currentAnimation.setAutoReverse(false);

            // Start the animation
            currentAnimation.play();
        }
    }

    private void updateFrame(int frameIndex, int frameWidth, int frameHeight) {
        spriteView.setViewport(new Rectangle2D(
                frameIndex * frameWidth, 0,
                frameWidth, frameHeight
        ));
    }

    public void setState(CharacterState newState) {
        if (currentState != newState) {
            currentState = newState;
            if (currentAnimation != null) {
                currentAnimation.play();
            }
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

class SpriteAnimation {
    private final Timeline timeline;
    private final ImageView imageView;
    private final int frameCount;
    private final int frameWidth;
    private final int frameHeight;
    private int currentFrame;

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