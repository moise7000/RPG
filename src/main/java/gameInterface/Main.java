package gameInterface;

import gameInterface.Parallax.ParallaxBackground;
import gameInterface.Scenes.MainMenuScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;




public class Main extends Application {
    private final InterfaceConfiguration config = InterfaceConfiguration.getShared();
    private StackPane root;
    private ParallaxBackground parallaxBackground;
    private MediaPlayer mediaPlayer;



    @Override
    public void start(Stage primaryStage) {

        try {
            //String fontPath = getClass().getResource(config.getFontPath()).toExternalForm();
            Font customFont = Font.loadFont(getClass().getResourceAsStream(config.getFontPath()), config.getFontSize());

            if (customFont == null) {
                System.err.println("Impossible de charger la police personnalisée");
            } else {
                System.out.println("Police chargée avec succès: " + customFont.getName());
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de la police: " + e.getMessage());
            e.printStackTrace();
        }



        try {
            String musicPath = getClass().getResource(config.getMusicPath()).toExternalForm();
            Media media = new Media(musicPath);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement ou de la lecture de la musique." + e.getMessage());
        }




        // Initialiser le parallaxe une seule fois
        parallaxBackground = new ParallaxBackground(
                config.getWindowWidth(),
                config.getWindowHeight()
        );
        parallaxBackground.addLayers(config.getParallaxLayers(), config.getParallaxSpeeds());
        parallaxBackground.startAnimation();

        // Conteneur principal
        root = new StackPane(parallaxBackground);
        VBox mainMenu = MainMenuScene.create(this, config); // Menu principal initial
        root.getChildren().add(mainMenu);

        // Scène principale
        Scene mainScene = new Scene(root, config.getWindowWidth(), config.getWindowHeight());
        primaryStage.setTitle(config.getWindowTitle());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    // Méthode pour changer dynamiquement le contenu sans affecter le parallaxe
    public void setSceneContent(VBox newContent) {
        root.getChildren().remove(1); // Supprimer le contenu actuel
        root.getChildren().add(newContent); // Ajouter le nouveau contenu
    }



    public static void main(String[] args) {
        launch(args);
    }
}
