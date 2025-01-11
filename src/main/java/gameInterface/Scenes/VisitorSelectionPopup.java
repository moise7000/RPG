package gameInterface.Scenes;

import eu.telecomnancy.rpg.Visitors.BuffVisitor;
import eu.telecomnancy.rpg.Visitors.HealVisitor;
import gameInterface.InterfaceConfiguration;
import gameInterface.Scenes.GameLoop.GameManager;
import gameInterface.character.CharacterAnimation;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;

public class VisitorSelectionPopup {
    private static Stage popup;

    public static void show(InterfaceConfiguration config) {
        popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initStyle(StageStyle.TRANSPARENT);
        popup.setTitle("Select Visitor");

        VBox mainContainer = new VBox(20);
        mainContainer.setStyle("""
        -fx-background-color: rgba(0, 0, 0, 0.4); 
        -fx-background-radius: 10;
        -fx-padding: 20;
    """);
        mainContainer.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Choose a Bonus");
        titleLabel.setStyle(config.getGameTitleStyle());

        HBox visitorsContainer = new HBox(20);
        visitorsContainer.setAlignment(Pos.CENTER);

        // Add visitors side by side
        visitorsContainer.getChildren().addAll(
                createVisitorCard(
                        config.getBuffName(),
                        config.getBuffDescription(),
                        config.getBuffAnimation(),
                        config
                ),
                createVisitorCard(
                        config.getHealName(),
                        config.getHealDescription(),
                        config.getHealAnimation(),
                        config
                )
        );

        Button closeButton = new Button(config.getExitButtonLabel());
        closeButton.setStyle(config.getButtonStyle());
        closeButton.setOnAction(e -> popup.close());
        ButtonStyleHelper.applyHoverStyle(closeButton, config.getButtonStyle(), config.getButtonHoverStyle());


        mainContainer.getChildren().addAll(titleLabel, visitorsContainer, closeButton);

        Scene popupScene = new Scene(mainContainer);
        popupScene.setFill(null);

        popup.setScene(popupScene);
        popup.sizeToScene();
        popup.show();
    }

    private static VBox createVisitorCard(String name, String description, CharacterAnimation visitorAnimation, InterfaceConfiguration config) {
        VBox card = new VBox(5);
        card.setStyle("""
        -fx-background-color: rgba(32, 32, 32, 0.7);
    -fx-background-radius: 10;
        -fx-padding: 20;
        -fx-min-width: 300;
        -fx-min-height: 400;
    """);
        card.setAlignment(Pos.TOP_CENTER);

        // Name with pixel font
        Label nameLabel = new Label(name);
        nameLabel.setStyle(config.getVisitorNameStyle());

        // Create a fixed-height region for the image
        VBox imageContainer = new VBox();
        imageContainer.setMinHeight(100);
        imageContainer.setMaxHeight(100);
        imageContainer.setAlignment(Pos.CENTER);
        visitorAnimation.getSpriteView().setScaleX(2);
        visitorAnimation.getSpriteView().setScaleY(2);
        imageContainer.getChildren().add(visitorAnimation.getSpriteView());


        // Description with pixel font
        Label descLabel = new Label(description);
        descLabel.setStyle(config.getVisitorDescriptionStyle());

        // Add spacing region to push the select button to the bottom
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Select button with pixel font
        Button selectButton = new Button(config.getSelectLabel());
        selectButton.setStyle(config.getButtonStyle());
        selectButton.setOnAction(e -> {
            applyVisitor(name);
            popup.close();
        });
        ButtonStyleHelper.applyHoverStyle(selectButton, config.getButtonStyle(), config.getButtonHoverStyle());

        Region spacer2 = new Region();
        spacer2.setPrefHeight(30);

        card.getChildren().addAll(nameLabel, imageContainer, descLabel, spacer, selectButton, spacer2);
        return card;
    }

    private static void applyVisitor(String visitorType) {
        GameManager gameManager = GameManager.getInstance();
        switch(visitorType) {
            case "Buff bonus":
                BuffVisitor buffVisitor = new BuffVisitor();
                buffVisitor.visit(gameManager.getPlayerCharacter());
                break;
            case "Heal bonus":
                HealVisitor healVisitor = new HealVisitor();
                healVisitor.visit(gameManager.getPlayerCharacter());
                break;
        }
    }
}