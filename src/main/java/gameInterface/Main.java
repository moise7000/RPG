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
import javafx.scene.layout.Region;
import javafx.geometry.Pos;

public class Main extends Application {
    private final InterfaceConfiguration config = InterfaceConfiguration.getShared();
    private StackPane scalingRoot;
    private StackPane gameRoot;
    private ParallaxBackground parallaxBackground;
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        // Charger la police
        try {
            Font customFont = Font.loadFont(getClass().getResourceAsStream(config.getFontPath()), config.getFontSize());
            if (customFont == null) {
                System.err.println("Impossible de charger la police personnalisée");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de la police: " + e.getMessage());
            e.printStackTrace();
        }

        // Charger la musique
        try {
            String musicPath = getClass().getResource(config.getMusicPath()).toExternalForm();
            Media media = new Media(musicPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement ou de la lecture de la musique." + e.getMessage());
        }

        // Créer le conteneur principal qui maintiendra le ratio
        scalingRoot = new StackPane();

        // Créer le conteneur de jeu avec une taille fixe
        gameRoot = new StackPane();
        gameRoot.setMinSize(config.getWindowWidth(), config.getWindowHeight());
        gameRoot.setMaxSize(config.getWindowWidth(), config.getWindowHeight());
        gameRoot.setPrefSize(config.getWindowWidth(), config.getWindowHeight());

        // Initialiser le parallax avec la taille fixe
        parallaxBackground = new ParallaxBackground(config.getWindowWidth(), config.getWindowHeight());
        parallaxBackground.addLayers(config.getParallaxLayers(), config.getParallaxSpeeds());
        parallaxBackground.startAnimation();

        // Ajouter le parallax et le contenu initial
        VBox mainMenu = MainMenuScene.create(this, config);
        gameRoot.getChildren().addAll(parallaxBackground, mainMenu);

        // Style du conteneur principal
        scalingRoot.setStyle("-fx-background-color: black;");
        scalingRoot.getChildren().add(gameRoot);
        StackPane.setAlignment(gameRoot, Pos.CENTER);

        // Créer la scène
        Scene scene = new Scene(scalingRoot);

        // Ajouter un écouteur de redimensionnement
        scene.widthProperty().addListener((obs, oldVal, newVal) -> updateScale());
        scene.heightProperty().addListener((obs, oldVal, newVal) -> updateScale());

        // Configurer la fenêtre
        primaryStage.setScene(scene);
        primaryStage.setTitle(config.getWindowTitle());
        primaryStage.setMinWidth(config.getWindowWidth() / 2);
        primaryStage.setMinHeight(config.getWindowHeight() / 2);

        // Définir la taille initiale
        primaryStage.setWidth(config.getWindowWidth());
        primaryStage.setHeight(config.getWindowHeight());

        primaryStage.show();
    }

    private void updateScale() {
        Scene scene = scalingRoot.getScene();
        double scaleX = scene.getWidth() / config.getWindowWidth();
        double scaleY = scene.getHeight() / config.getWindowHeight();
        double scale = Math.min(scaleX, scaleY);

        gameRoot.setScaleX(scale);
        gameRoot.setScaleY(scale);
    }

    public void setSceneContent(VBox newContent) {
        gameRoot.getChildren().clear();
        gameRoot.getChildren().addAll(parallaxBackground, newContent);
    }

    public void stopParallax() {parallaxBackground.stopAnimation();}
    public void startParallax() {parallaxBackground.startAnimation();}
    public void stopMusic() {mediaPlayer.stop();}
    public void startMusic() {mediaPlayer.play();}

    public static void main(String[] args) {
        launch(args);
    }
}