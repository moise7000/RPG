package gameInterface.Scenes.GameLoop;

import gameInterface.character.CharacterAnimation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Gère les animations d'un personnage dans le jeu.
 * <p>
 * Cette classe permet de coordonner une séquence d'animations pour un personnage en utilisant des transitions JavaFX.
 * Elle prend en charge des animations telles que le déplacement, l'attaque, et l'impact, et peut gérer des effets comme le flip du sprite lors du mouvement.
 * </p>
 */
public class CharacterAnimationManager {
    private static CharacterAnimationManager instance;

    public enum AnimationType {
        MOVE,
        MOVE_WITH_FLIP,
        ATTACK,
        HIT
    }

    /**
     * Représente une étape dans une séquence d'animation.
     */
    public static class AnimationStep {
        private final AnimationType type;
        private final double targetX;
        private final Duration duration;
        private final boolean shouldFlip;  // Nouveau paramètre pour le flip

        /**
         * Constructeur pour créer une étape d'animation.
         *
         * @param type       Le type d'animation
         * @param targetX    La position cible pour l'animation
         * @param duration   La durée de l'animation
         * @param shouldFlip Indique si un flip horizontal est nécessaire
         */
        public AnimationStep(AnimationType type, double targetX, Duration duration, boolean shouldFlip) {
            this.type = type;
            this.targetX = targetX;
            this.duration = duration;
            this.shouldFlip = shouldFlip;
        }

        /**
         * Constructeur pour créer une étape d'animation sans flip.
         *
         * @param type     Le type d'animation
         * @param targetX  La position cible pour l'animation
         * @param duration La durée de l'animation
         */
        public AnimationStep(AnimationType type, double targetX, Duration duration) {
            this(type, targetX, duration, false);
        }

        /**
         * Constructeur pour créer une étape d'animation sans position cible ni flip.
         *
         * @param type     Le type d'animation
         * @param duration La durée de l'animation
         */
        public AnimationStep(AnimationType type, Duration duration) {
            this(type, 0, duration, false);
        }
    }

    /**
     * Obtient l'instance unique de {@link CharacterAnimationManager}.
     *
     * @return L'instance unique de `CharacterAnimationManager`.
     */
    public static CharacterAnimationManager getInstance() {
        if (instance == null) {
            instance = new CharacterAnimationManager();
        }
        return instance;
    }



    public enum AnimationDirection {
        LEFT, RIGHT
    }



    /**
     * Exécute une séquence d'animations pour un personnage.
     * La séquence peut inclure des déplacements, attaques et impacts.
     *
     * @param direction   La direction du mouvement (gauche ou droite)
     * @param characterAnimation L'animation du personnage à exécuter
     * @param steps       La liste des étapes d'animation
     * @param onComplete  Une action à exécuter une fois l'animation terminée
     */
    public void performAnimationSequence(AnimationDirection direction, CharacterAnimation characterAnimation, List<AnimationStep> steps, Runnable onComplete) {
        SequentialTransition sequence = new SequentialTransition();

        for (AnimationStep step : steps) {
            switch (step.type) {
                case MOVE:
                case MOVE_WITH_FLIP:
                    // Définir l'orientation avant le mouvement si nécessaire
                    PauseTransition setOrientation = new PauseTransition(Duration.ZERO);
                    setOrientation.setOnFinished(event -> {
                        if (step.shouldFlip) {
                            if (direction == AnimationDirection.LEFT) {
                                characterAnimation.getSpriteView().setScaleX(step.targetX > 0 ? -2 : 2);
                            } else {
                                characterAnimation.getSpriteView().setScaleX(step.targetX > 0 ? 2 : -2);
                            }



                        }
                        characterAnimation.setState(CharacterAnimation.CharacterState.MOVE);
                    });
                    sequence.getChildren().add(setOrientation);

                    // Animation de mouvement
                    TranslateTransition moveTransition = new TranslateTransition(step.duration, characterAnimation.getSpriteView());
                    moveTransition.setToX(step.targetX);
                    moveTransition.setOnFinished(event -> {
                        characterAnimation.setState(CharacterAnimation.CharacterState.IDLE);

                    });
                    sequence.getChildren().add(moveTransition);
                    break;

                case ATTACK:
                    PauseTransition setAttackState = new PauseTransition(Duration.ZERO);
                    setAttackState.setOnFinished(event -> {
                        characterAnimation.setState(CharacterAnimation.CharacterState.ATTACK);
                        int attackFrames = characterAnimation.getFrameCount(CharacterAnimation.CharacterState.ATTACK);
                        PauseTransition attackDuration = new PauseTransition(Duration.millis(attackFrames * 100));
                        attackDuration.setOnFinished(e -> characterAnimation.setState(CharacterAnimation.CharacterState.IDLE));
                        attackDuration.play();
                    });
                    sequence.getChildren().add(setAttackState);
                    sequence.getChildren().add(new PauseTransition(step.duration));
                    break;

                case HIT:
                    PauseTransition setHitState = new PauseTransition(Duration.ZERO);
                    setHitState.setOnFinished(event -> {
                        characterAnimation.setState(CharacterAnimation.CharacterState.HIT);
                        int hitFrames = characterAnimation.getFrameCount(CharacterAnimation.CharacterState.HIT);
                        PauseTransition hitDuration = new PauseTransition(Duration.millis(hitFrames * 100));
                        hitDuration.setOnFinished(e ->
                                characterAnimation.setState(CharacterAnimation.CharacterState.IDLE)
                        );
                        hitDuration.play();
                    });
                    sequence.getChildren().add(setHitState);
                    sequence.getChildren().add(new PauseTransition(step.duration));
                    break;
            }

            sequence.getChildren().add(new PauseTransition(Duration.millis(100)));
        }

        sequence.setOnFinished(event -> {
            // Obtenir le dernier élément de la liste
            AnimationStep lastStep = steps.get(steps.size() - 1);
            if (direction== AnimationDirection.RIGHT) {

                characterAnimation.getSpriteView().setScaleX(lastStep.targetX > 0 ? -2 : 2);

            } else {
                characterAnimation.getSpriteView().setScaleX(lastStep.targetX > 0 ? 2 : -2);
            }


            if (onComplete != null) {
                onComplete.run();
            }

        });

        sequence.play();
    }

    /**
     * Crée une séquence d'animation pour une attaque complète :
     * 1. Avancer vers la cible
     * 2. Effectuer l'attaque
     * 3. Retourner à la position initiale
     *
     * @param targetX Position à atteindre pour l'attaque
     * @param initialPosition Position de départ/retour du personnage
     * @param moveDuration Durée du mouvement (aller ou retour)
     * @param attackDuration Durée de l'animation d'attaque
     * @return Liste des étapes d'animation
     */
    public List<AnimationStep> createAttackSequence(double targetX,
                                                    double initialPosition,
                                                    Duration moveDuration,
                                                    Duration attackDuration) {
        List<AnimationStep> sequence = new ArrayList<>();

        // Mouvement vers l'avant
        sequence.add(new AnimationStep(
                AnimationType.MOVE,
                targetX,
                moveDuration
        ));

        // Animation d'attaque
        sequence.add(new AnimationStep(
                AnimationType.ATTACK,
                attackDuration
        ));

        // Retour à la position initiale avec flip
        sequence.add(new AnimationStep(
                AnimationType.MOVE_WITH_FLIP,
                initialPosition,
                moveDuration,
                true
        ));

        return sequence;
    }






}