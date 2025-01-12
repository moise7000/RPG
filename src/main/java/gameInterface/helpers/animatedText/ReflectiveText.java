package gameInterface.helpers.animatedText;

import javafx.scene.effect.Reflection;
import javafx.scene.control.Label;

public class ReflectiveText {
    public static Label createReflectiveText(String text) {
        Label reflectiveLabel = new Label(text);
        reflectiveLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");

        // Apply a reflection effect
        Reflection reflection = new Reflection();
        reflection.setTopOpacity(0.5);
        reflection.setBottomOpacity(0.0);
        reflection.setFraction(0.7); // Control the length of the reflection
        reflectiveLabel.setEffect(reflection);

        return reflectiveLabel;
    }
}
