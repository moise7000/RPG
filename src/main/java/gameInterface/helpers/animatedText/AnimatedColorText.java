package gameInterface.helpers.animatedText;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class AnimatedColorText {
    public static Label createColorChangingText(String text) {
        Label colorLabel = new Label(text);
        colorLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");

        // Animation for color change
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> colorLabel.setTextFill(Color.RED)),
                new KeyFrame(Duration.seconds(1), e -> colorLabel.setTextFill(Color.YELLOW)),
                new KeyFrame(Duration.seconds(2), e -> colorLabel.setTextFill(Color.GREEN)),
                new KeyFrame(Duration.seconds(3), e -> colorLabel.setTextFill(Color.BLUE))
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.play();

        return colorLabel;
    }
}

