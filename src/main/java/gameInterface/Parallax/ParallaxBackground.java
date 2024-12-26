package gameInterface.Parallax;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;



public class ParallaxBackground extends Pane {
    private final List<ParallaxLayer> layers = new ArrayList<>();
    private final AnimationTimer animator;
    private boolean isAnimating = false;


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


    public void addLayers( List<String> imagePaths, List<Double> speeds ) {
        if (imagePaths.size() != speeds.size()) {
            throw new IllegalArgumentException("The size of imagePaths and speeds must be the same.");
        }

        for (int i = 0; i < imagePaths.size(); i++) {
            addLayer(imagePaths.get(i), speeds.get(i));
        }
    }

    private void updateLayers() {
        for (ParallaxLayer layer : layers) {
            layer.update();
        }
    }

    public void startAnimation() {
        if (!isAnimating) {
            animator.start();
            isAnimating = true;
        }
    }

    public void stopAnimation() {
        if (isAnimating) {
            animator.stop();
            isAnimating = false;
        }
    }


}
