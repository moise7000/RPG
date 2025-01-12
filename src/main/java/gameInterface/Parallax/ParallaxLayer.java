package gameInterface.Parallax;


import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;


/**
 * Représente une couche dans un arrière-plan en parallaxe.
 * <p>
 * Une couche contient deux images qui défilent à une vitesse spécifique pour simuler un effet de profondeur
 * dans l'arrière-plan. Lorsque la première image se déplace hors de l'écran, elle est replacée après la deuxième image.
 *
 * <p><b>Utilisation :</b></p>
 * <pre>
 * ParallaxLayer layer = new ParallaxLayer("background_layer.png", 0.5, 800, 600);
 * layer.update();
 * </pre>
 */
public class ParallaxLayer {
    /** Premier ImageView pour afficher l'image de la couche. */
    private final ImageView imageView1;

    /** Deuxième ImageView pour afficher une copie de l'image de la couche. */
    private final ImageView imageView2;

    /** Vitesse de défilement de la couche. */
    private final double scrollSpeed;

    /** Position actuelle de la couche. */
    private double position = 0;

    /** Largeur de l'écran (utilisé pour gérer la répétition des images). */
    private final double width;

    /**
     * Crée une couche de parallaxe avec une image et une vitesse de défilement.
     *
     * @param imagePath Le chemin vers l'image de la couche.
     * @param scrollSpeed La vitesse de défilement de la couche (plus la valeur est faible, plus la couche défile lentement).
     * @param width La largeur de l'écran.
     * @param height La hauteur de l'écran.
     */
    public ParallaxLayer(String imagePath, double scrollSpeed, double width, double height) {
        this.scrollSpeed = scrollSpeed;
        this.width = width;

        Image image = new Image(imagePath);
        imageView1 = new ImageView(image);
        imageView2 = new ImageView(image);

        imageView1.setFitWidth(width);
        imageView1.setFitHeight(height);
        imageView2.setFitWidth(width);
        imageView2.setFitHeight(height);

        imageView1.setTranslateX(width);
    }

    /**
     * Met à jour la position des images en fonction de la vitesse de défilement.
     * Si la première image sort de l'écran, elle est repositionnée après la deuxième image pour créer un effet de répétition infinie.
     */
    public void update() {
        position -= scrollSpeed;

        // Reset the position when the images have completely scrolled
        if (position <= -width) {
            position = 0;
        }

        // Update the positions of the images
        imageView1.setTranslateX(position);
        imageView2.setTranslateX(position + width);
    }

    /**
     * Retourne les deux ImageView représentant la couche de parallaxe.
     *
     * @return Un tableau contenant les deux ImageView de la couche.
     */
    public ImageView[] getImageViews() {
        return new ImageView[]{imageView1, imageView2};
    }

}
