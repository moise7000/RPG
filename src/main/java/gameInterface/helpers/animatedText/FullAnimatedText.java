package gameInterface.helpers.animatedText;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.scene.effect.Glow;

public class FullAnimatedText {
    public static Label createFullAnimatedText(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");

        // Ajouter effet de brillance
        Glow glow = new Glow();
        glow.setLevel(1.5);
        label.setEffect(glow);

        // Animation de mouvement
        TranslateTransition move = new TranslateTransition(Duration.seconds(2), label);
        move.setToY(-100);
        move.setCycleCount(TranslateTransition.INDEFINITE);
        move.setAutoReverse(true);

        // Animation de brillance
        Timeline glowTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> glow.setLevel(0.5)),
                new KeyFrame(Duration.seconds(1), e -> glow.setLevel(1.5)),
                new KeyFrame(Duration.seconds(2), e -> glow.setLevel(0.5))
        );
        glowTimeline.setCycleCount(Timeline.INDEFINITE);

        move.play();
        glowTimeline.play();

        return label;
    }
}
