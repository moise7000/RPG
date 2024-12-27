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



public class CharacterAnimation {
    private final ImageView spriteView;
    private Map<CharacterState, AnimationData> animations;
    private Timeline currentAnimation;
    private CharacterState currentState;

    public enum CharacterState {
        IDLE,
        ATTACK,
        DEATH,
        MOVE,
        HIT
    }

    private static class AnimationData {
        final Image spriteSheet;
        final Timeline timeline;
        final int frameWidth;
        final int frameHeight;

        AnimationData(Image spriteSheet, Timeline timeline, int frameWidth, int frameHeight) {
            this.spriteSheet = spriteSheet;
            this.timeline = timeline;
            this.frameWidth = frameWidth;
            this.frameHeight = frameHeight;
        }
    }

    public CharacterAnimation() {
        this.spriteView = new ImageView();
        this.animations = new HashMap<>();
        this.currentState = CharacterState.IDLE;
        spriteView.setStyle("-fx-interpolation-hint: nearest-neighbor");
    }

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

        // Initialiser la première animation
        if (spriteView.getImage() == null) {
            spriteView.setImage(spriteSheet);
            spriteView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        }
    }

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