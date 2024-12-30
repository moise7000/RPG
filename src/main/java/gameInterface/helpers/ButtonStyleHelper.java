package gameInterface.helpers;

import javafx.scene.control.Button;
import java.util.List;
public class ButtonStyleHelper {
    public static void applyHoverStyle(List<Button> buttons, String baseStyle, String hoverStyle) {
        for (Button button : buttons) { button.setStyle(baseStyle);
            button.setOnMouseEntered(event -> button.setStyle(baseStyle + hoverStyle));
            button.setOnMouseExited(event -> button.setStyle(baseStyle));
        }
    }

    public static void applyButtonStyle(List<Button> buttons, String style) {
        for (Button button : buttons) { button.setStyle(style); }
    }

    public static void applyHoverStyle(Button button, String baseStyle, String hoverStyle) {
        button.setStyle(baseStyle);
        button.setOnMouseEntered(event -> button.setStyle(baseStyle + hoverStyle));
        button.setOnMouseExited(event -> button.setStyle(baseStyle));

    }

    public static void applyButtonStyle(Button button, String style) {
        button.setStyle(style);
    }
}

