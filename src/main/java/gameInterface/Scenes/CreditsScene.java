package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.Scenes.MainMenuScene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Crée la scène des crédits pour l'application.
 * <p>
 * Cette scène affiche le texte des crédits ainsi qu'un bouton permettant de revenir au menu principal.
 *
 * <p><b>Utilisation :</b></p>
 * <pre>
 * VBox creditsScene = CreditsScene.create(mainApp, config);
 * </pre>
 */
public class CreditsScene {

    /**
     * Crée la scène des crédits avec le texte des crédits et un bouton pour revenir au menu principal.
     *
     * @param mainApp L'application principale, utilisée pour changer de scène.
     * @param config La configuration de l'interface, qui contient des paramètres tels que les styles et le texte des crédits.
     * @return Un VBox représentant la scène des crédits.
     */
    public static VBox create(Main mainApp, InterfaceConfiguration config) {
        VBox creditsLayout = new VBox(20);
        creditsLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // Texte des crédits
        Text creditsText = new Text(config.getCreditsText());
        creditsText.setStyle("-fx-font-size: 16px; -fx-text-alignment: center;");

        // Bouton de retour
        Button backButton = new Button(config.getExitButtonLabel());
        backButton.setStyle(config.getButtonStyle());

        backButton.setOnAction(e -> mainApp.setSceneContent(MainMenuScene.create(mainApp, config)));

        creditsLayout.getChildren().addAll(creditsText, backButton);
        return creditsLayout;
    }
}
