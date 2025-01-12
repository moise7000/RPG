package gameInterface.Parallax;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;


/**
 * Gère un arrière-plan en parallaxe avec plusieurs couches défilantes.
 * <p>
 * Chaque couche se déplace à une vitesse différente pour créer un effet de profondeur
 * et de dynamisme dans l'interface graphique.
 *
 * <p><b>Utilisation :</b></p>
 * <pre>
 * ParallaxBackground background = new ParallaxBackground(800, 600);
 * background.addLayer("background_layer1.png", 0.5);
 * background.addLayer("background_layer2.png", 1.0);
 * background.startAnimation();
 * </pre>
 */
public class ParallaxBackground extends Pane {
    /** Liste des couches du parallaxe. */
    private final List<ParallaxLayer> layers = new ArrayList<>();

    /** Animation qui met à jour les couches. */
    private final AnimationTimer animator;

    /** Indique si l'animation est en cours. */
    private boolean isAnimating = false;

    /**
     * Crée un arrière-plan en parallaxe avec des dimensions spécifiques.
     *
     * @param width  La largeur de l'arrière-plan.
     * @param height La hauteur de l'arrière-plan.
     */
    public ParallaxBackground(double width, double height) {

        animator = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateLayers();
            }
        };


        setMaxSize(width, height);
        setClip(new javafx.scene.shape.Rectangle(width, height));
    }


    /**
     * Ajoute une couche d'image à l'arrière-plan avec une vitesse de défilement spécifique.
     *
     * @param imagePath Le chemin vers l'image de la couche.
     * @param speed     La vitesse de défilement de la couche (plus la valeur est faible, plus la couche est lente).
     *
     * <p><b>Exemple :</b></p>
     * <pre>
     * background.addLayer("clouds.png", 0.3);
     * </pre>
     */
    public void addLayer(String imagePath, double speed) {
        ParallaxLayer layer = new ParallaxLayer(
                imagePath,
                speed,
                getMaxWidth(),
                getMaxHeight()
        );

        layers.add(layer);


        getChildren().addAll(layer.getImageViews());
    }


    /**
     * Ajoute plusieurs couches d'images à l'arrière-plan avec leurs vitesses respectives.
     *
     * @param imagePaths Liste des chemins vers les images des couches.
     * @param speeds     Liste des vitesses de défilement pour chaque couche.
     *
     * @throws IllegalArgumentException si les tailles des listes sont différentes.
     *
     * <p><b>Exemple :</b></p>
     * <pre>
     * List&lt;String&gt; images = Arrays.asList("sky.png", "mountains.png", "trees.png");
     * List&lt;Double&gt; speeds = Arrays.asList(0.2, 0.5, 1.0);
     * background.addLayers(images, speeds);
     * </pre>
     */
    public void addLayers( List<String> imagePaths, List<Double> speeds ) {
        if (imagePaths.size() != speeds.size()) {
            throw new IllegalArgumentException("The size of imagePaths and speeds must be the same.");
        }

        for (int i = 0; i < imagePaths.size(); i++) {
            addLayer(imagePaths.get(i), speeds.get(i));
        }
    }

    /**
     * Met à jour la position de toutes les couches pour créer l'effet de défilement.
     */
    private void updateLayers() {
        for (ParallaxLayer layer : layers) {
            layer.update();
        }
    }

    /**
     * Démarre l'animation des couches de l'arrière-plan.
     *
     * <p><b>Exemple :</b></p>
     * <pre>
     * background.startAnimation();
     * </pre>
     */
    public void startAnimation() {
        if (!isAnimating) {
            animator.start();
            isAnimating = true;
        }
    }

    /**
     * Arrête l'animation des couches de l'arrière-plan.
     *
     * <p><b>Exemple :</b></p>
     * <pre>
     * background.stopAnimation();
     * </pre>
     */
    public void stopAnimation() {
        if (isAnimating) {
            animator.stop();
            isAnimating = false;
        }
    }


}
