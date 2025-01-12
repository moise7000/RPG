package gameInterface.helpers;

import javafx.scene.control.Button;
import java.util.List;
/**
 * Fournit des méthodes utilitaires pour styliser des boutons JavaFX.
 * <p>
 * Cette classe facilite l'application de styles de base et de styles au survol
 * pour les boutons dans l'interface graphique du jeu.
 */
public class ButtonStyleHelper {

    /**
     * Applique un style de base et un style au survol à une liste de boutons.
     *
     * @param buttons    La liste des boutons à styliser.
     * @param baseStyle  Le style de base (CSS) à appliquer aux boutons.
     * @param hoverStyle Le style supplémentaire à appliquer lors du survol.
     *
     * <p><b>Exemple :</b></p>
     * <pre>
     * ButtonStyleHelper.applyHoverStyle(buttons, "-fx-background-color: #333;", "-fx-border-color: #fff;");
     * </pre>
     */
    public static void applyHoverStyle(List<Button> buttons, String baseStyle, String hoverStyle) {
        for (Button button : buttons) { button.setStyle(baseStyle);
            button.setOnMouseEntered(event -> button.setStyle(baseStyle + hoverStyle));
            button.setOnMouseExited(event -> button.setStyle(baseStyle));
        }
    }

    /**
     * Applique un style unique à une liste de boutons.
     *
     * @param buttons La liste des boutons à styliser.
     * @param style   Le style CSS à appliquer.
     *
     * <p><b>Exemple :</b></p>
     * <pre>
     * ButtonStyleHelper.applyButtonStyle(buttons, "-fx-font-size: 16px;");
     * </pre>
     */
    public static void applyButtonStyle(List<Button> buttons, String style) {
        for (Button button : buttons) { button.setStyle(style); }
    }

    /**
     * Applique un style de base et un style au survol à un bouton unique.
     *
     * @param button     Le bouton, à styliser.
     * @param baseStyle  Le style de base (CSS) à appliquer.
     * @param hoverStyle Le style supplémentaire au survol.
     *
     * <p><b>Exemple :</b></p>
     * <pre>
     * ButtonStyleHelper.applyHoverStyle(myButton, "-fx-background-color: #444;", "-fx-border-color: #ff0;");
     * </pre>
     */
    public static void applyHoverStyle(Button button, String baseStyle, String hoverStyle) {
        button.setStyle(baseStyle);
        button.setOnMouseEntered(event -> button.setStyle(baseStyle + hoverStyle));
        button.setOnMouseExited(event -> button.setStyle(baseStyle));

    }

    /**
     * Applique un style unique à un bouton.
     *
     * @param button Le bouton, à styliser.
     * @param style  Le style CSS à appliquer.
     *
     * <p><b>Exemple :</b></p>
     * <pre>
     * ButtonStyleHelper.applyButtonStyle(myButton, "-fx-font-weight: bold;");
     * </pre>
     */
    public static void applyButtonStyle(Button button, String style) {
        button.setStyle(style);
    }
}

