package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.Scenes.MainMenuScene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CreditsScene {

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
