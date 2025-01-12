package gameInterface.helpers.animatedText;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class AnimatedGlowText {
    public static Label createGlowingText(String text) {
        Label glowingLabel = new Label(text);
        glowingLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");

        // Glow effect
        Glow glow = new Glow();
        glowingLabel.setEffect(glow);

        // Animation for glow effect
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> glow.setLevel(0.5)),
                new KeyFrame(Duration.seconds(1), e -> glow.setLevel(1.5)),
                new KeyFrame(Duration.seconds(2), e -> glow.setLevel(0.5))
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.play();

        return glowingLabel;
    }
}

