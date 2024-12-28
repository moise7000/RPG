package gameInterface;

import gameInterface.character.CharacterAnimation;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterfaceConfiguration {

    private static InterfaceConfiguration shared;

    private final double ZOOM = 2.1;





    private static final class Window {
        static final String WINDOW_TITLE = "RPG";
        static final int WINDOW_WIDTH = 620;
        static final int WINDOW_HEIGHT = 360;
        static final int WINDOW_X = 0;
        static final int WINDOW_Y = 0;
        static final int WINDOW_BORDER_WIDTH = 10;
        static final int WINDOW_BORDER_HEIGHT = 10;
        static final int WINDOW_BORDER_COLOR = 0x000000;
        static final int WINDOW_BACKGROUND_COLOR = 0xFFFFFF;
        static final int WINDOW_FONT_SIZE = 30;
    }

    private static final class UserInterfaceConfiguration {

        static final String PLAY = "Play";
        static final String CREDITS = "Credits";
        static final String QUIT = "Quit";
        static final String SAVE = "Save";
        static final String LOAD = "Load";
        static final String SETTINGS = "Settings";
        static final String EXIT = "Exit";
        static final String GAME_TITLE = "The Kingdom of the Lost Oyster";
        static final String SELECT_CHARACTER = "Select your character";



    }

    public double getZoom() {return ZOOM;}

    public String getGameTitle() {return UserInterfaceConfiguration.GAME_TITLE;}
    public String getWindowTitle() {return Window.WINDOW_TITLE;}
    public String getPlayButtonLabel() {return UserInterfaceConfiguration.PLAY;}
    public String getCreditsButtonLabel() {return UserInterfaceConfiguration.CREDITS;}
    public String getQuitButtonLabel() {return UserInterfaceConfiguration.QUIT;}
    public String getSelectCharacterButtonLabel() {return UserInterfaceConfiguration.SELECT_CHARACTER;}
    public String getSaveButtonLabel() {return UserInterfaceConfiguration.SAVE;}
    public String getLoadButtonLabel() {return UserInterfaceConfiguration.LOAD;}
    public String getSettingsButtonLabel() {return UserInterfaceConfiguration.SETTINGS;}
    public String getExitButtonLabel() {return UserInterfaceConfiguration.EXIT;}








    public double getWindowWidth() {return Window.WINDOW_WIDTH * ZOOM;}
    public double getWindowHeight() {return Window.WINDOW_HEIGHT * ZOOM;}
    public int getWindowX() {return Window.WINDOW_X;}
    public int getWindowY() {return Window.WINDOW_Y;}
    public int getWindowBorderWidth() {return Window.WINDOW_BORDER_WIDTH;}
    public int getWindowBorderHeight() {return Window.WINDOW_BORDER_HEIGHT;}
    public int getWindowBorderColor() {return Window.WINDOW_BORDER_COLOR;}
    public int getWindowBackgroundColor() {return Window.WINDOW_BACKGROUND_COLOR;}
    public int getWindowFontSize() {return Window.WINDOW_FONT_SIZE;}


    public static InterfaceConfiguration getShared() {
        if (shared == null) {
            shared = new InterfaceConfiguration();
        }
        return shared;
    }


    private static final class ParallaxConfiguration {
        static final List<String> PARALLAX_LAYERS = Arrays.asList(
                "/assets/background/NightForest/Layers/1.png",
                "/assets/background/NightForest/Layers/2.png",
                "/assets/background/NightForest/Layers/3.png",
                "/assets/background/NightForest/Layers/4.png",
                "/assets/background/NightForest/Layers/5.png",
                "/assets/background/NightForest/Layers/6.png"
        );;

        static final List<Double> PARALLAX_SPEEDS = Arrays.asList(0.1, 0.2, 0.3, 0.4, 0.5, 0.6);;

    }

    public List<String> getParallaxLayers() {return ParallaxConfiguration.PARALLAX_LAYERS;}
    public List<Double> getParallaxSpeeds() {return ParallaxConfiguration.PARALLAX_SPEEDS;}







    private static final class SettingsConfiguration {}





    private static final class StylesConfiguration {
        static final String FONT_PATH = "/assets/fonts/VeniceClassic.ttf";
        static final String FONT_NAME = "'Venice Classic'";
        static final int FONT_SIZE = 50;
        static final String FONT_AUTHOR = "Venice Classic à by soixantedeux.";
        static final String BUTTON_STYLE =
                "-fx-font-family:" + FONT_NAME + ";" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: rgba(0, 0, 0, 0.0);" +
                "-fx-text-fill: #FFFFFF;";

        static final String BUTTON_HOVER_STYLE = "-fx-background-color: rgba(0, 0, 0, 0.3);";
        static final String GAME_TITLE_STYLE =
                "-fx-font-family:" + FONT_NAME + ";" +
                "-fx-font-size: 100px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: rgba(0, 0, 0, 0.0);" +
                "-fx-text-fill: #FFFFFF;";


    }

    public String getGameTitleStyle() {return StylesConfiguration.GAME_TITLE_STYLE;}
    public String getButtonStyle() {return StylesConfiguration.BUTTON_STYLE;}
    public String getFontPath() {return StylesConfiguration.FONT_PATH;}
    public String getFontName() {return StylesConfiguration.FONT_NAME;}
    public int getFontSize() {return StylesConfiguration.FONT_SIZE;}
    public String getButtonHoverStyle() {return StylesConfiguration.BUTTON_STYLE + StylesConfiguration.BUTTON_HOVER_STYLE;}
    public String getFontAuthor() {return StylesConfiguration.FONT_AUTHOR;}


    private static final class CreditsConfiguration {
        static final String GAME_AUTHOR = "RPG by Ewan Decima";
        static final String MUSIC_NAME = "Challenge Accepted";
        static final String MUSIC_PATH = "/assets/music/ChallengeAccepted.mp3";
        static final String MUSIC_AUTHOR = "Darren Curtis";
        static final String FONT = "Font : " + StylesConfiguration.FONT_AUTHOR;
        static final String MUSIC = "Music : " + MUSIC_NAME + " by "  + MUSIC_AUTHOR;


    }

    public String getMusicPath() {return CreditsConfiguration.MUSIC_PATH;}


    public String getCreditsText() {
        return CreditsConfiguration.GAME_AUTHOR +
                "\n\n" +
                CreditsConfiguration.FONT +
                "\n\n" +
                CreditsConfiguration.MUSIC
                ;}



    private static final class CharacterConfiguration {
        static final String WIZARD_PATH = "/assets/characters/Wizard";
        static final String EVIL_WIZARD_PATH = "/assets/characters/EvilWizard";
        static final String HERO_KNIGHT_PATH = "/assets/characters/HeroKnight";
        static final String MARTIAL_HERO_PATH = "/assets/characters/MartialHero";

        static final List<CharacterAnimation.CharacterState> STATES = Arrays.asList(
                CharacterAnimation.CharacterState.IDLE,
                CharacterAnimation.CharacterState.MOVE,
                CharacterAnimation.CharacterState.ATTACK,
                CharacterAnimation.CharacterState.DEATH,
                CharacterAnimation.CharacterState.HIT
        );


        private static final class Wizard {

            static final int FRAME_WIDTH = 231;
            static final int FRAME_HEIGHT = 190;

            private static final class IDLE {
                static final String SPRITE_PATH = WIZARD_PATH + "/Idle.png";
                static final int SPRITE_WIDTH = 1386;
                static final int FRAME_COUNT = 6;

            }

            private static final class MOVE {
                static final String SPRITE_PATH = WIZARD_PATH + "/Move.png";
                static final int SPRITE_WIDTH = 1848;
                static final int FRAME_COUNT = 8;
            }

            private static final class ATTACK {
                static final String SPRITE_PATH = WIZARD_PATH + "/Attack.png";
                static final int SPRITE_WIDTH = 1848;
                static final int FRAME_COUNT = 8;

            }

            private static final class DEATH {
                static final String SPRITE_PATH = WIZARD_PATH + "/Death.png";
                static final int SPRITE_WIDTH = 1617;
                static final int FRAME_COUNT = 7;
            }

            private static final class HIT {
                static final String SPRITE_PATH = WIZARD_PATH + "/Hit.png";
                static final int SPRITE_WIDTH = 924;
                static final int FRAME_COUNT = 4;
            }
        }

        private static final class EvilWizard {

            static final int FRAME_WIDTH = 150;
            static final int FRAME_HEIGHT = 150;

            private static final class IDLE {
                static final String SPRITE_PATH = EVIL_WIZARD_PATH + "/Idle.png";
                static final int SPRITE_WIDTH = 1200;
                static final int SPRITE_HEIGHT = 150;
                static final int FRAME_COUNT = 8;
            }

            private static final class MOVE {
                static final String SPRITE_PATH = EVIL_WIZARD_PATH + "/Move.png";
                static final int SPRITE_WIDTH = 1200;
                static final int SPRITE_HEIGHT = 150;
                static final int FRAME_COUNT = 8;
            }

            private static final class ATTACK {
                static final String SPRITE_PATH = EVIL_WIZARD_PATH + "/Attack.png";
                static final int SPRITE_WIDTH = 1200;
                static final int SPRITE_HEIGHT = 150;
                static final int FRAME_COUNT = 8;

            }

            private static final class DEATH {
                static final String SPRITE_PATH = EVIL_WIZARD_PATH + "/Death.png";
                static final int SPRITE_WIDTH = 750;
                static final int SPRITE_HEIGHT = 150;
                static final int FRAME_COUNT = 5;
            }

            private static final class HIT {
                static final String SPRITE_PATH = EVIL_WIZARD_PATH + "/Hit.png";
                static final int SPRITE_WIDTH = 600;
                static final int SPRITE_HEIGHT = 150;
                static final int FRAME_COUNT = 4;
            }
        }


        private static final class HeroKnight {

            static final int FRAME_WIDTH = 180;
            static final int FRAME_HEIGHT = 180;

            private static final class IDLE {
                static final String SPRITE_PATH = HERO_KNIGHT_PATH + "/Idle.png";
                static final int SPRITE_WIDTH = 1980;
                static final int SPRITE_HEIGHT = 180;
                static final int FRAME_COUNT = 11;
            }

            private static final class MOVE {
                static final String SPRITE_PATH = HERO_KNIGHT_PATH + "/Move.png";
                static final int SPRITE_WIDTH = 1440;
                static final int SPRITE_HEIGHT = 180;
                static final int FRAME_COUNT = 8;
            }

            private static final class ATTACK {
                static final String SPRITE_PATH = HERO_KNIGHT_PATH + "/Attack.png";
                static final int SPRITE_WIDTH = 1260;
                static final int SPRITE_HEIGHT = 180;
                static final int FRAME_COUNT = 7;
            }

            private static final class DEATH {
                static final String SPRITE_PATH = HERO_KNIGHT_PATH + "/Death.png";
                static final int SPRITE_WIDTH = 1980;
                static final int SPRITE_HEIGHT = 180;
                static final int FRAME_COUNT = 11;
            }

            private static final class HIT {
                static final String SPRITE_PATH = HERO_KNIGHT_PATH + "/Hit.png";
                static final int SPRITE_WIDTH = 720;
                static final int SPRITE_HEIGHT = 180;
                static final int FRAME_COUNT = 4;
            }
        }

        private static final class MartialHero {

            static final int FRAME_WIDTH = 200;
            static final int FRAME_HEIGHT = 190;

            private static final class IDLE {
                static final String SPRITE_PATH = MARTIAL_HERO_PATH + "/Idle.png";
                static final int SPRITE_WIDTH = 1600;
                static final int SPRITE_HEIGHT = 200;
                static final int FRAME_COUNT = 8;
            }

            private static final class MOVE {
                static final String SPRITE_PATH = MARTIAL_HERO_PATH + "/Move.png";
                static final int SPRITE_WIDTH = 1600;
                static final int SPRITE_HEIGHT = 200;
                static final int FRAME_COUNT = 8;
            }

            private static final class ATTACK {
                static final String SPRITE_PATH = MARTIAL_HERO_PATH + "/Attack.png";
                static final int SPRITE_WIDTH = 1200;
                static final int SPRITE_HEIGHT = 200;
                static final int FRAME_COUNT = 6;
            }

            private static final class DEATH {
                static final String SPRITE_PATH = MARTIAL_HERO_PATH + "/Death.png";
                static final int SPRITE_WIDTH = 1200;
                static final int SPRITE_HEIGHT = 300;
                static final int FRAME_COUNT = 6;
            }

            private static final class HIT {
                static final String SPRITE_PATH = WIZARD_PATH + "/Hit.png";
                static final int SPRITE_WIDTH = 800;
                static final int SPRITE_HEIGHT = 200;
                static final int FRAME_COUNT = 4;
            }
        }

    }

    public List<CharacterAnimation.CharacterState> getCharacterStates() {return CharacterConfiguration.STATES;}


    //----------------------------------------WIZARD------------------------------------------------------//

    public List<String> getWizardSpritesPaths() {
        List<String> spritesPaths = new ArrayList<>();
        spritesPaths.add(getWizardIdleSpritePath());
        spritesPaths.add(getWizardMoveSpritePath());
        spritesPaths.add(getWizardAttackSpritePath());
        spritesPaths.add(getWizardDeathSpritePath());
        spritesPaths.add(getWizardHitSpritePath());


        return spritesPaths;
    }

    public List<Integer> getWizardFrameCounts() {
        List<Integer> frameCounts = new ArrayList<>();
        frameCounts.add(getWizardIdleSpriteFrameCount());
        frameCounts.add(getWizardMoveSpriteFrameCount());
        frameCounts.add(getWizardAttackSpriteFrameCount());
        frameCounts.add(getWizardDeathSpriteFrameCount());
        frameCounts.add(getWizardHitSpriteFrameCount());
        return frameCounts;
    }

    public List<String> getEvilWizardSpritesPaths() {
        List<String> spritesPaths = new ArrayList<>();
        spritesPaths.add(getEvilWizardIdleSpritePath());
        spritesPaths.add(getEvilWizardMoveSpritePath());
        spritesPaths.add(getEvilWizardAttackSpritePath());
        spritesPaths.add(getEvilWizardDeathSpritePath());
        spritesPaths.add(getEvilWizardHitSpritePath());


        return spritesPaths;
    }

    public List<Integer> getEvilWizardFrameCounts() {
        List<Integer> frameCounts = new ArrayList<>();
        frameCounts.add(getEvilWizardIdleSpriteFrameCount());
        frameCounts.add(getEvilWizardMoveSpriteFrameCount());
        frameCounts.add(getEvilWizardAttackSpriteFrameCount());
        frameCounts.add(getEvilWizardDeathSpriteFrameCount());
        frameCounts.add(getEvilWizardHitSpriteFrameCount());
        return frameCounts;
    }


    public List<String> getHeroKnightSpritesPaths() {
        List<String> spritesPaths = new ArrayList<>();
        spritesPaths.add(getHeroKnightIdleSpritePath());
        spritesPaths.add(getHeroKnightMoveSpritePath());
        spritesPaths.add(getHeroKnightAttackSpritePath());
        spritesPaths.add(getHeroKnightDeathSpritePath());
        spritesPaths.add(getHeroKnightHitSpritePath());


        return spritesPaths;
    }

    public List<Integer> getHeroKnightFrameCounts() {
        List<Integer> frameCounts = new ArrayList<>();
        frameCounts.add(getHeroKnightIdleSpriteFrameCount());
        frameCounts.add(getHeroKnightMoveSpriteFrameCount());
        frameCounts.add(getHeroKnightAttackSpriteFrameCount());
        frameCounts.add(getHeroKnightDeathSpriteFrameCount());
        frameCounts.add(getHeroKnightHitSpriteFrameCount());
        return frameCounts;
    }

    public List<String> getMartialHeroSpritesPaths() {
        List<String> spritesPaths = new ArrayList<>();
        spritesPaths.add(getMartialHeroIdleSpritePath());
        spritesPaths.add(getMartialHeroMoveSpritePath());
        spritesPaths.add(getMartialHeroAttackSpritePath());
        spritesPaths.add(getMartialHeroDeathSpritePath());
        spritesPaths.add(getMartialHeroHitSpritePath());


        return spritesPaths;
    }

    public List<Integer> getMartialHeroFrameCounts() {
        List<Integer> frameCounts = new ArrayList<>();
        frameCounts.add(getMartialHeroIdleSpriteFrameCount());
        frameCounts.add(getMartialHeroMoveSpriteFrameCount());
        frameCounts.add(getMartialHeroAttackSpriteFrameCount());
        frameCounts.add(getMartialHeroDeathSpriteFrameCount());
        frameCounts.add(getMartialHeroHitSpriteFrameCount());
        return frameCounts;
    }


    public int getWizardFrameWidth() {return CharacterConfiguration.Wizard.FRAME_WIDTH;}
    public int getWizardFrameHeight() {return CharacterConfiguration.Wizard.FRAME_HEIGHT;}

    //IDLE
    public String getWizardIdleSpritePath() {return CharacterConfiguration.Wizard.IDLE.SPRITE_PATH;}
    public int getWizardIdleSpriteWidth() {return CharacterConfiguration.Wizard.IDLE.SPRITE_WIDTH;}
    public int getWizardIdleSpriteFrameCount() {return CharacterConfiguration.Wizard.IDLE.FRAME_COUNT;}



    //MOVE
    public String getWizardMoveSpritePath() {return CharacterConfiguration.Wizard.MOVE.SPRITE_PATH;}
    public int getWizardMoveSpriteWidth() {return CharacterConfiguration.Wizard.MOVE.SPRITE_WIDTH;}
    public int getWizardMoveSpriteFrameCount() {return CharacterConfiguration.Wizard.MOVE.FRAME_COUNT;}




    //ATTACK
    public String getWizardAttackSpritePath() {return CharacterConfiguration.Wizard.ATTACK.SPRITE_PATH;}
    public int getWizardAttackSpriteWidth() {return CharacterConfiguration.Wizard.ATTACK.SPRITE_WIDTH;}
    public int getWizardAttackSpriteFrameCount() {return CharacterConfiguration.Wizard.ATTACK.FRAME_COUNT;}




    //DEATH
    public String getWizardDeathSpritePath() {return CharacterConfiguration.Wizard.DEATH.SPRITE_PATH;}
    public int getWizardDeathSpriteWidth() {return CharacterConfiguration.Wizard.DEATH.SPRITE_WIDTH;}
    public int getWizardDeathSpriteFrameCount() {return CharacterConfiguration.Wizard.DEATH.FRAME_COUNT;}





    //HIT
    public String getWizardHitSpritePath() {return CharacterConfiguration.Wizard.HIT.SPRITE_PATH;}
    public int getWizardHitSpriteWidth() {return CharacterConfiguration.Wizard.HIT.SPRITE_WIDTH;}
    public int getWizardHitSpriteFrameCount() {return CharacterConfiguration.Wizard.HIT.FRAME_COUNT;}

    public CharacterAnimation getWizardIdleAnimation() {
        CharacterAnimation character = new CharacterAnimation();


        character.addAnimation(
                CharacterAnimation.CharacterState.IDLE,
                getWizardIdleSpritePath(),
                getWizardIdleSpriteFrameCount(),  // frameCount
                getWizardFrameWidth(), // frameWidth
                getWizardFrameHeight(), // frameHeight
                Duration.millis(100)
        );

        // Set initial state
        character.setState(CharacterAnimation.CharacterState.IDLE);
        return character;
    }
    public CharacterAnimation getWizardMoveAnimation() {
        CharacterAnimation character = new CharacterAnimation();


        character.addAnimation(
                CharacterAnimation.CharacterState.MOVE,
                getWizardMoveSpritePath(),
                getWizardMoveSpriteFrameCount(),  // frameCount
                getWizardFrameWidth(), // frameWidth
                getWizardFrameHeight(), // frameHeight
                Duration.millis(100)
        );

        // Set initial state
        character.setState(CharacterAnimation.CharacterState.MOVE);
        return character;
    }
    public CharacterAnimation getWizardAttackAnimation() {
        CharacterAnimation character = new CharacterAnimation();


        character.addAnimation(
                CharacterAnimation.CharacterState.ATTACK,
                getWizardAttackSpritePath(),
                getWizardAttackSpriteFrameCount(),  // frameCount
                getWizardFrameWidth(), // frameWidth
                getWizardFrameHeight(), // frameHeight
                Duration.millis(100)
        );

        // Set initial state
        character.setState(CharacterAnimation.CharacterState.ATTACK);
        return character;
    }
    public CharacterAnimation getWizardDeathAnimation() {
        CharacterAnimation character = new CharacterAnimation();


        character.addAnimation(
                CharacterAnimation.CharacterState.DEATH,
                getWizardDeathSpritePath(),
                getWizardDeathSpriteFrameCount(),  // frameCount
                getWizardFrameWidth(), // frameWidth
                getWizardFrameHeight(), // frameHeight
                Duration.millis(100)
        );

        // Set initial state
        character.setState(CharacterAnimation.CharacterState.DEATH);
        return character;
    }
    public CharacterAnimation getWizardHitAnimation() {
        CharacterAnimation character = new CharacterAnimation();


        character.addAnimation(
                CharacterAnimation.CharacterState.HIT,
                getWizardHitSpritePath(),
                getWizardHitSpriteFrameCount(),  // frameCount
                getWizardFrameWidth(), // frameWidth
                getWizardFrameHeight(), // frameHeight
                Duration.millis(100)
        );

        // Set initial state
        character.setState(CharacterAnimation.CharacterState.HIT);
        return character;
    }

    public CharacterAnimation getWizardAnimations() {
        CharacterAnimation character = new CharacterAnimation();
        character.addAnimations(
                getCharacterStates(),
                getWizardSpritesPaths(),
                getWizardFrameCounts(),
                getWizardFrameWidth(),
                getWizardFrameHeight(),
                Duration.millis(100)
        );
        character.setState(CharacterAnimation.CharacterState.IDLE);

        return character;
    }

    public CharacterAnimation getEvilWizardAnimations() {
        CharacterAnimation character = new CharacterAnimation();
        character.addAnimations(
                getCharacterStates(),
                getEvilWizardSpritesPaths(),
                getEvilWizardFrameCounts(),
                getEvilWizardFrameWidth(),
                getEvilWizardFrameHeight(),
                Duration.millis(100)
        );
        character.setState(CharacterAnimation.CharacterState.IDLE);

        return character;
    }

    public CharacterAnimation getHeroKnightAnimations() {
        CharacterAnimation character = new CharacterAnimation();
        character.addAnimations(
                getCharacterStates(),
                getHeroKnightSpritesPaths(),
                getHeroKnightFrameCounts(),
                getHeroKnightFrameWidth(),
                getHeroKnightFrameHeight(),
                Duration.millis(100)
        );
        character.setState(CharacterAnimation.CharacterState.IDLE);

        return character;
    }

    public CharacterAnimation getMartialHeroAnimations() {
        CharacterAnimation character = new CharacterAnimation();
        character.addAnimations(
                getCharacterStates(),
                getMartialHeroSpritesPaths(),
                getMartialHeroFrameCounts(),
                getMartialHeroFrameWidth(),
                getMartialHeroFrameHeight(),
                Duration.millis(100)
        );
        character.setState(CharacterAnimation.CharacterState.IDLE);

        return character;
    }



    //----------------------------------------EVIL WIZARD------------------------------------------------------//

    public int getEvilWizardFrameWidth() {return CharacterConfiguration.EvilWizard.FRAME_WIDTH;}
    public int getEvilWizardFrameHeight() {return CharacterConfiguration.EvilWizard.FRAME_HEIGHT;}

    //IDLE
    public String getEvilWizardIdleSpritePath() {return CharacterConfiguration.EvilWizard.IDLE.SPRITE_PATH;}
    public int getEvilWizardIdleSpriteWidth() {return CharacterConfiguration.EvilWizard.IDLE.SPRITE_WIDTH;}
    public int getEvilWizardIdleSpriteFrameCount() {return CharacterConfiguration.EvilWizard.IDLE.FRAME_COUNT;}

    public CharacterAnimation getEvilWizardIdleAnimation() {
        CharacterAnimation character = new CharacterAnimation();


        character.addAnimation(
                CharacterAnimation.CharacterState.IDLE,
                getEvilWizardIdleSpritePath(),
                getEvilWizardIdleSpriteFrameCount(),  // frameCount
                getEvilWizardFrameWidth(), // frameWidth
                getEvilWizardFrameHeight(), // frameHeight
                Duration.millis(100)
        );

        // Set initial state
        character.setState(CharacterAnimation.CharacterState.IDLE);
        return character;
    }


    //MOVE
    public String getEvilWizardMoveSpritePath() {return CharacterConfiguration.EvilWizard.MOVE.SPRITE_PATH;}
    public int getEvilWizardMoveSpriteWidth() {return CharacterConfiguration.EvilWizard.MOVE.SPRITE_WIDTH;}
    public int getEvilWizardMoveSpriteFrameCount() {return CharacterConfiguration.EvilWizard.MOVE.FRAME_COUNT;}

    //ATTACK
    public String getEvilWizardAttackSpritePath() {return CharacterConfiguration.EvilWizard.ATTACK.SPRITE_PATH;}
    public int getEvilWizardAttackSpriteWidth() {return CharacterConfiguration.EvilWizard.ATTACK.SPRITE_WIDTH;}
    public int getEvilWizardAttackSpriteFrameCount() {return CharacterConfiguration.EvilWizard.ATTACK.FRAME_COUNT;}


    //DEATH
    public String getEvilWizardDeathSpritePath() {return CharacterConfiguration.EvilWizard.DEATH.SPRITE_PATH;}
    public int getEvilWizardDeathSpriteWidth() {return CharacterConfiguration.EvilWizard.DEATH.SPRITE_WIDTH;}
    public int getEvilWizardDeathSpriteFrameCount() {return CharacterConfiguration.EvilWizard.DEATH.FRAME_COUNT;}

    //HIT
    public String getEvilWizardHitSpritePath() {return CharacterConfiguration.EvilWizard.HIT.SPRITE_PATH;}
    public int getEvilWizardHitSpriteWidth() {return CharacterConfiguration.EvilWizard.HIT.SPRITE_WIDTH;}
    public int getEvilWizardHitSpriteFrameCount() {return CharacterConfiguration.EvilWizard.HIT.FRAME_COUNT;}


    //----------------------------------------HERO KNIGHT------------------------------------------------------//

    public int getHeroKnightFrameWidth() {return CharacterConfiguration.HeroKnight.FRAME_WIDTH;}
    public int getHeroKnightFrameHeight() {return CharacterConfiguration.HeroKnight.FRAME_HEIGHT;}

    //IDLE
    public String getHeroKnightIdleSpritePath() {return CharacterConfiguration.HeroKnight.IDLE.SPRITE_PATH;}
    public int getHeroKnightIdleSpriteWidth() {return CharacterConfiguration.HeroKnight.IDLE.SPRITE_WIDTH;}
    public int getHeroKnightIdleSpriteFrameCount() {return CharacterConfiguration.HeroKnight.IDLE.FRAME_COUNT;}

    public CharacterAnimation getHeroKnightIdleAnimation() {
        CharacterAnimation character = new CharacterAnimation();


        character.addAnimation(
                CharacterAnimation.CharacterState.IDLE,
                getHeroKnightIdleSpritePath(),
                getHeroKnightIdleSpriteFrameCount(),  // frameCount
                getHeroKnightFrameWidth(), // frameWidth
                getHeroKnightFrameHeight(), // frameHeight
                Duration.millis(100)
        );

        // Set initial state
        character.setState(CharacterAnimation.CharacterState.IDLE);
        return character;
    }

    //MOVE
    public String getHeroKnightMoveSpritePath() {return CharacterConfiguration.HeroKnight.MOVE.SPRITE_PATH;}
    public int getHeroKnightMoveSpriteWidth() {return CharacterConfiguration.HeroKnight.MOVE.SPRITE_WIDTH;}
    public int getHeroKnightMoveSpriteFrameCount() {return CharacterConfiguration.HeroKnight.MOVE.FRAME_COUNT;}

    //ATTACK
    public String getHeroKnightAttackSpritePath() {return CharacterConfiguration.HeroKnight.ATTACK.SPRITE_PATH;}
    public int getHeroKnightAttackSpriteWidth() {return CharacterConfiguration.HeroKnight.ATTACK.SPRITE_WIDTH;}
    public int getHeroKnightAttackSpriteFrameCount() {return CharacterConfiguration.HeroKnight.ATTACK.FRAME_COUNT;}

    //DEATH
    public String getHeroKnightDeathSpritePath() {return CharacterConfiguration.HeroKnight.DEATH.SPRITE_PATH;}
    public int getHeroKnightDeathSpriteWidth() {return CharacterConfiguration.HeroKnight.DEATH.SPRITE_WIDTH;}
    public int getHeroKnightDeathSpriteFrameCount() {return CharacterConfiguration.HeroKnight.DEATH.FRAME_COUNT;}

    //HIT
    public String getHeroKnightHitSpritePath() {return CharacterConfiguration.HeroKnight.HIT.SPRITE_PATH;}
    public int getHeroKnightHitSpriteWidth() {return CharacterConfiguration.HeroKnight.HIT.SPRITE_WIDTH;}
    public int getHeroKnightHitSpriteFrameCount() {return CharacterConfiguration.HeroKnight.HIT.FRAME_COUNT;}

    //----------------------------------------MARTIAL HERO------------------------------------------------------//

    public int getMartialHeroFrameWidth() {return CharacterConfiguration.MartialHero.FRAME_WIDTH;}
    public int getMartialHeroFrameHeight() {return CharacterConfiguration.MartialHero.FRAME_HEIGHT;}

    //IDLE
    public String getMartialHeroIdleSpritePath() {return CharacterConfiguration.MartialHero.IDLE.SPRITE_PATH;}
    public int getMartialHeroIdleSpriteWidth() {return CharacterConfiguration.MartialHero.IDLE.SPRITE_WIDTH;}
    public int getMartialHeroIdleSpriteFrameCount() {return CharacterConfiguration.MartialHero.IDLE.FRAME_COUNT;}

    public CharacterAnimation getMartialHeroIdleAnimation() {
        CharacterAnimation character = new CharacterAnimation();


        character.addAnimation(
                CharacterAnimation.CharacterState.IDLE,
                getMartialHeroIdleSpritePath(),
                getMartialHeroIdleSpriteFrameCount(),  // frameCount
                getMartialHeroFrameWidth(), // frameWidth
                getMartialHeroFrameHeight(), // frameHeight
                Duration.millis(100)
        );

        // Set initial state
        character.setState(CharacterAnimation.CharacterState.IDLE);
        return character;
    }

    //MOVE
    public String getMartialHeroMoveSpritePath() {return CharacterConfiguration.MartialHero.MOVE.SPRITE_PATH;}
    public int getMartialHeroMoveSpriteWidth() {return CharacterConfiguration.MartialHero.MOVE.SPRITE_WIDTH;}
    public int getMartialHeroMoveSpriteFrameCount() {return CharacterConfiguration.MartialHero.MOVE.FRAME_COUNT;}

    //ATTACK
    public String getMartialHeroAttackSpritePath() {return CharacterConfiguration.MartialHero.ATTACK.SPRITE_PATH;}
    public int getMartialHeroAttackSpriteWidth() {return CharacterConfiguration.MartialHero.ATTACK.SPRITE_WIDTH;}
    public int getMartialHeroAttackSpriteFrameCount() {return CharacterConfiguration.MartialHero.ATTACK.FRAME_COUNT;}

    //DEATH
    public String getMartialHeroDeathSpritePath() {return CharacterConfiguration.MartialHero.DEATH.SPRITE_PATH;}
    public int getMartialHeroDeathSpriteWidth() {return CharacterConfiguration.MartialHero.DEATH.SPRITE_WIDTH;}
    public int getMartialHeroDeathSpriteFrameCount() {return CharacterConfiguration.MartialHero.DEATH.FRAME_COUNT;}

    //Hit
    public String getMartialHeroHitSpritePath() {return CharacterConfiguration.MartialHero.HIT.SPRITE_PATH;}
    public int getMartialHeroHitSpriteWidth() {return CharacterConfiguration.MartialHero.HIT.SPRITE_WIDTH;}
    public int getMartialHeroHitSpriteFrameCount() {return CharacterConfiguration.MartialHero.HIT.FRAME_COUNT;}




    private static final class GamePlayConfiguration {
        static final String ATTACK = "Attack";
        static final String RECRUIT = "Recruit";
    }

    public String getAttackButtonLabel() {return GamePlayConfiguration.ATTACK;}
    public String getRecruitButtonLabel() {return GamePlayConfiguration.RECRUIT;}







}
