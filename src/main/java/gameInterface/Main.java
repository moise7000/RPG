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
import javafx.application.Platform;

/**
 * La classe principale du jeu qui gère la fenêtre de l'application, le menu principal, la musique et les effets parallax.
 * <p>
 * Cette classe étend <code>Application</code> et est responsable du lancement de l'application JavaFX, du chargement des ressources
 * (police et musique), ainsi que de la gestion de la scène du jeu avec des effets visuels et de mise à l'échelle dynamique.
 * </p>
 */
public class Main extends Application {
    private final InterfaceConfiguration config = InterfaceConfiguration.getShared();
    private StackPane scalingRoot;
    private StackPane gameRoot;
    private ParallaxBackground parallaxBackground;
    private MediaPlayer mediaPlayer;

    /**
     * Méthode principale pour démarrer l'application. Crée la scène, le fond parallax, le menu principal, et configure les éléments visuels.
     *
     * @param primaryStage Le stage principal de l'application.
     */
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
            setMusicVolume(0.5);
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

    /**
     * Met à jour le facteur de mise à l'échelle pour s'adapter à la taille de la fenêtre.
     */
    private void updateScale() {
        Scene scene = scalingRoot.getScene();
        double scaleX = scene.getWidth() / config.getWindowWidth();
        double scaleY = scene.getHeight() / config.getWindowHeight();
        double scale = Math.min(scaleX, scaleY);

        gameRoot.setScaleX(scale);
        gameRoot.setScaleY(scale);
    }

    /**
     * Change le contenu de la scène du jeu en fonction du nouveau contenu passé.
     *
     * @param newContent Le nouveau contenu à afficher.
     */
    public void setSceneContent(VBox newContent) {
        gameRoot.getChildren().clear();
        gameRoot.getChildren().addAll(parallaxBackground, newContent);
    }

    public void stopParallax() {parallaxBackground.stopAnimation();}
    public void startParallax() {parallaxBackground.startAnimation();}
    public void stopMusic() {mediaPlayer.stop();}
    public void startMusic() {mediaPlayer.play();}

    /**
     * Récupère la scène actuellement affichée.
     *
     * @return La scène actuelle.
     */
    public Scene getScene() {return scalingRoot.getScene();}




    /**
     * Définit le volume de la musique.
     *
     * @param volume La valeur du volume entre 0.0 (silence) et 1.0 (volume maximal).
     */
    public void setMusicVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(Math.max(0.0, Math.min(1.0, volume))); // Assurer que le volume est entre 0.0 et 1.0
        }
    }

    /**
     * Méthode main pour lancer l'application.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }
}