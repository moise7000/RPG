package gameInterface.helpers.animatedText;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class MovingText {
    public static Label createMovingText(String text) {
        Label movingLabel = new Label(text);
        movingLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");

        // Animation de déplacement
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), movingLabel);
        transition.setToY(-100); // Déplacer le texte vers le haut
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();

        return movingLabel;
    }
}
