package gameInterface.Parallax;


import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;


public class ParallaxLayer {

    private final ImageView imageView1;
    private final ImageView imageView2;
    private final double scrollSpeed;
    private double position = 0;

    private final double width;

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

    public ImageView[] getImageViews() {
        return new ImageView[]{imageView1, imageView2};
    }

}
