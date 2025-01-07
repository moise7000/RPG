package gameInterface;

import eu.telecomnancy.rpg.*;
import eu.telecomnancy.rpg.Strategy.AggressiveStrategy;
import eu.telecomnancy.rpg.Strategy.CombatStrategy;
import eu.telecomnancy.rpg.Strategy.DefensiveStrategy;
import eu.telecomnancy.rpg.Strategy.NeutralStrategy;
import gameInterface.character.CharacterAnimation;
import gameInterface.character.CharacterType;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterfaceConfiguration {

    private static InterfaceConfiguration shared;

    private final double ZOOM = 2.3;





    private static final class Window {

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
        static final String GAME_OVER = "Game Over";
        static final String SELECT = "Select";




    }

    public double getZoom() {return ZOOM;}

    public String getSelectLabel() {return UserInterfaceConfiguration.SELECT;}
    public String getGameOverLabel() {return UserInterfaceConfiguration.GAME_OVER;}
    public String getGameTitle() {return UserInterfaceConfiguration.GAME_TITLE;}
    public String getWindowTitle() {return UserInterfaceConfiguration.GAME_TITLE ;}
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
        static final int FONT_CAPTION_SIZE = 20;
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

        static final String CHARACTER_INFO_STYLE =
                "-fx-font-family:" + FONT_NAME + ";" +
                "-fx-font-size: "+ FONT_CAPTION_SIZE + "px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FFFFFF;";

        static final String GAME_OVER_STYLE =
                "-fx-font-family:" + FONT_NAME + ";" +
                        "-fx-font-size: 160px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: rgba(0, 0, 0, 0.0);" +
                        "-fx-text-fill: #850606;";

        static final String EXIT_BUTTON_STYLE =
                "-fx-font-family:" + FONT_NAME + ";" +
                        "-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: rgba(0, 0, 0, 0.0);" +
                        "-fx-text-fill: #FFFFFF;";

        static final String EXIT_BUTTON_HOVER_STYLE =
                "-fx-background-color: rgba(0, 0, 0, 0.3);" +
                " -fx-text-fill: #850606;";

        static final String LEVEL_STYLE =
                "-fx-font-family:" + FONT_NAME + ";" +
                        "-fx-font-size:  40px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #FFFFFF;";

        static final String VISITOR_DESCRIPTION_STYLE =
                "-fx-font-family:" + FONT_NAME + ";" +
                "-fx-font-size:  14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-wrap-text: true;";

        static final String VISITOR_NAME_STYLE =
                "-fx-font-family:" + FONT_NAME + ";" +
                        "-fx-font-size:  30px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-wrap-text: true;";

    }

    public String getVisitorNameStyle() {return StylesConfiguration.VISITOR_NAME_STYLE;}
    public String getVisitorDescriptionStyle() {return StylesConfiguration.VISITOR_DESCRIPTION_STYLE;}
    public String getLevelStyle() {return StylesConfiguration.LEVEL_STYLE;}
    public String getGameOverStyle() {return StylesConfiguration.GAME_OVER_STYLE;}
    public String getGameTitleStyle() {return StylesConfiguration.GAME_TITLE_STYLE;}
    public String getButtonStyle() {return StylesConfiguration.BUTTON_STYLE;}
    public String getExitButtonStyle() {return StylesConfiguration.EXIT_BUTTON_STYLE;}
    public String getFontPath() {return StylesConfiguration.FONT_PATH;}
    public String getFontName() {return StylesConfiguration.FONT_NAME;}
    public int getFontSize() {return StylesConfiguration.FONT_SIZE;}
    public String getButtonHoverStyle() {return StylesConfiguration.BUTTON_STYLE + StylesConfiguration.BUTTON_HOVER_STYLE;}
    public String getExitButtonHoverStyle() {return  StylesConfiguration.BUTTON_STYLE + StylesConfiguration.EXIT_BUTTON_HOVER_STYLE;}
    public String getFontAuthor() {return StylesConfiguration.FONT_AUTHOR;}
    public int getFontCaptionSize() {return StylesConfiguration.FONT_CAPTION_SIZE;}
    public String getCharacterInfoStyle() {return StylesConfiguration.CHARACTER_INFO_STYLE;}


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
        static final String NECROMANCER_PATH = "/assets/characters/Necromancer";
        static final String NIGHT_BORNE_PATH = "/assets/characters/NightBorne";

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

            static final String NAME = "Wizard";
            static final int HEALTH = 300;
            static CombatStrategy STRATEGY = new DefensiveStrategy();
            static final CharacterType TYPE = CharacterType.WIZARD;
            static final String INFO =
                    "Name: " + NAME + "\n"
                    + "Health: " + HEALTH + "\n"
                    + "Type: " + TYPE + "\n";



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

            static final String NAME = "EvilWizard";
            static final int HEALTH = 100;
            static CombatStrategy STRATEGY = new AggressiveStrategy();
            static final CharacterType TYPE = CharacterType.WIZARD;
            static final String INFO =
                    "Name: " + NAME + "\n"
                    + "Health: " + HEALTH + "\n"
                    + "Type: " + TYPE + "\n";

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

            static final String NAME = "HeroKnight";
            static final int HEALTH = 200;
            static CombatStrategy STRATEGY = new NeutralStrategy();
            static final CharacterType TYPE = CharacterType.WARRIOR;
            static final String INFO = "Name: " + NAME + "\n"
                    + "Health: " + HEALTH + "\n"
                    + "Type: " + TYPE + "\n";

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

            static final String NAME = "MartialHero";
            static final int HEALTH = 250;
            static CombatStrategy STRATEGY = new AggressiveStrategy();
            static final CharacterType TYPE = CharacterType.WARRIOR;
            static final String INFO =
                    "Name: " + NAME + "\n"
                    + "Health: " + HEALTH + "\n"
                    + "Type: " + TYPE + "\n";

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
                static final String SPRITE_PATH = MARTIAL_HERO_PATH + "/Hit.png";
                static final int SPRITE_WIDTH = 800;
                static final int SPRITE_HEIGHT = 200;
                static final int FRAME_COUNT = 4;
            }
        }

        private static final class NightBorne {
            static final int FRAME_WIDTH = 80;
            static final int FRAME_HEIGHT = 80;

            static final String NAME = "NightBorne";
            static final int HEALTH = 400;
            static CombatStrategy STRATEGY = new DefensiveStrategy();
            static final CharacterType TYPE = CharacterType.WARRIOR;
            static final String INFO =
                            "Name: " + NAME + "\n"
                            + "Health: " + HEALTH + "\n"
                            + "Type: " + TYPE + "\n";

            private static final class IDLE {
                static final String SPRITE_PATH = NIGHT_BORNE_PATH + "/Idle.png";
                static final int SPRITE_WIDTH = 720;
                static final int FRAME_COUNT = 9;
            }

            private static final class MOVE {
                static final String SPRITE_PATH = NIGHT_BORNE_PATH + "/Move.png";
                static final int SPRITE_WIDTH = 480;
                static final int FRAME_COUNT = 6;
            }

            private static final class ATTACK {
                static final String SPRITE_PATH = NIGHT_BORNE_PATH + "/Attack.png";
                static final int SPRITE_WIDTH = 960;
                static final int FRAME_COUNT = 12;
            }

            private static final class DEATH {
                static final String SPRITE_PATH = NIGHT_BORNE_PATH + "/Death.png";
                static final int SPRITE_WIDTH = 1840;
                static final int FRAME_COUNT = 23;
            }

            private static final class HIT {
                static final String SPRITE_PATH = NIGHT_BORNE_PATH + "/Hit.png";
                static final int SPRITE_WIDTH = 1840;
                static final int FRAME_COUNT = 5;
            }
        }

        private static final class Necromancer {
            static final int FRAME_WIDTH = 160;
            static final int FRAME_HEIGHT = 128;

            static final String NAME = "Necromancer";
            static final int HEALTH = 150;
            static CombatStrategy STRATEGY = new NeutralStrategy();
            static final CharacterType TYPE = CharacterType.WIZARD;
            static final String INFO =
                    "Name: " + NAME + "\n"
                            + "Health: " + HEALTH + "\n"
                            + "Type: " + TYPE + "\n";

            private static final class IDLE {
                static final String SPRITE_PATH = NECROMANCER_PATH + "/Idle.png";
                static final int SPRITE_WIDTH = 2720;
                static final int FRAME_COUNT = 8;
            }

            private static final class MOVE {
                static final String SPRITE_PATH = NECROMANCER_PATH + "/Move.png";
                static final int SPRITE_WIDTH = 2720;
                static final int FRAME_COUNT = 8;
            }

            private static final class ATTACK {
                static final String SPRITE_PATH = NECROMANCER_PATH + "/Attack2.png";
                static final int SPRITE_WIDTH = 2720;
                static final int FRAME_COUNT = 13;
            }

            private static final class DEATH {
                static final String SPRITE_PATH = NECROMANCER_PATH + "/Death.png";
                static final int SPRITE_WIDTH = 2720;
                static final int FRAME_COUNT = 10;
            }

            private static final class HIT {
                static final String SPRITE_PATH = NECROMANCER_PATH + "/Hit.png";
                static final int SPRITE_WIDTH = 2720;
                static final int FRAME_COUNT = 5;
            }
        }

    }

    public List<CharacterAnimation.CharacterState> getCharacterStates() {return CharacterConfiguration.STATES;}




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

    public List<String> getNightBorneSpritesPaths() {
        List<String> spritesPaths = new ArrayList<>();
        spritesPaths.add(getNightBorneIdleSpritePath());
        spritesPaths.add(getNightBorneMoveSpritePath());
        spritesPaths.add(getNightBorneAttackSpritePath());
        spritesPaths.add(getNightBorneDeathSpritePath());
        spritesPaths.add(getNightBorneHitSpritePath());


        return spritesPaths;
    }
    public List<Integer> getNightBorneFrameCounts() {
        List<Integer> frameCounts = new ArrayList<>();
        frameCounts.add(getNightBorneIdleSpriteFrameCount());
        frameCounts.add(getNightBorneMoveSpriteFrameCount());
        frameCounts.add(getNightBorneAttackSpriteFrameCount());
        frameCounts.add(getNightBorneDeathSpriteFrameCount());
        frameCounts.add(getNightBorneHitSpriteFrameCount());
        return frameCounts;
    }

    public List<String> getNecromancerSpritesPaths() {
        List<String> spritesPaths = new ArrayList<>();
        spritesPaths.add(getNecromancerIdleSpritePath());
        spritesPaths.add(getNecromancerMoveSpritePath());
        spritesPaths.add(getNecromancerAttackSpritePath());
        spritesPaths.add(getNecromancerDeathSpritePath());
        spritesPaths.add(getNecromancerHitSpritePath());


        return spritesPaths;
    }
    public List<Integer> getNecromancerFrameCounts() {
        List<Integer> frameCounts = new ArrayList<>();
        frameCounts.add(getNecromancerIdleSpriteFrameCount());
        frameCounts.add(getNecromancerMoveSpriteFrameCount());
        frameCounts.add(getNecromancerAttackSpriteFrameCount());
        frameCounts.add(getNecromancerDeathSpriteFrameCount());
        frameCounts.add(getNecromancerHitSpriteFrameCount());
        return frameCounts;
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

    public CharacterAnimation getNightBorneAnimations() {
        CharacterAnimation character = new CharacterAnimation();
        character.addAnimations(
                getCharacterStates(),
                getNightBorneSpritesPaths(),
                getNightBorneFrameCounts(),
                getNightBorneFrameWidth(),
                getNightBorneFrameHeight(),
                Duration.millis(100)
        );
        character.setState(CharacterAnimation.CharacterState.IDLE);

        return character;
    }

    public CharacterAnimation getNecromancerAnimations() {
        CharacterAnimation character = new CharacterAnimation();
        character.addAnimations(
                getCharacterStates(),
                getNecromancerSpritesPaths(),
                getNecromancerFrameCounts(),
                getNecromancerFrameWidth(),
                getNecromancerFrameHeight(),
                Duration.millis(100)
        );
        character.setState(CharacterAnimation.CharacterState.IDLE);

        return character;
    }

    //----------------------------------------WIZARD------------------------------------------------------//


    public String getWizardName() {return CharacterConfiguration.Wizard.NAME;}
    public int getWizardHealth() {return CharacterConfiguration.Wizard.HEALTH;}
    public CombatStrategy getWizardCombatStrategy() {return CharacterConfiguration.Wizard.STRATEGY;}
    public CharacterType getWizardType() {return CharacterConfiguration.Wizard.TYPE;}
    public String getWizardInfo() {return CharacterConfiguration.Wizard.INFO;}

    public GameCharacter createWizardGameCharacter() {
        CharacterCreator wizardCreator = new WizardCreator();
        return (Wizard) wizardCreator.create(
                getWizardName(),
                getWizardHealth(),
                getWizardCombatStrategy(),
                getWizardAnimations()
        );
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






    //----------------------------------------EVIL WIZARD------------------------------------------------------//

    public String getEvilWizardName() {return CharacterConfiguration.EvilWizard.NAME;}
    public int getEvilWizardHealth() {return CharacterConfiguration.EvilWizard.HEALTH;}
    public CombatStrategy getEvilWizardCombatStrategy() {return CharacterConfiguration.EvilWizard.STRATEGY;}
    public CharacterType getEvilWizardType() {return CharacterConfiguration.EvilWizard.TYPE;}
    public String getEvilWizardInfo() {return CharacterConfiguration.EvilWizard.INFO;}

    public GameCharacter createEvilWizardGameCharacter() {
        CharacterCreator wizardCreator = new WizardCreator();
        return (Wizard) wizardCreator.create(
                getEvilWizardName(),
                getEvilWizardHealth(),
                getEvilWizardCombatStrategy(),
                getEvilWizardAnimations()
        );
    }


    public int getEvilWizardFrameWidth() {return CharacterConfiguration.EvilWizard.FRAME_WIDTH;}
    public int getEvilWizardFrameHeight() {return CharacterConfiguration.EvilWizard.FRAME_HEIGHT;}

    //IDLE
    public String getEvilWizardIdleSpritePath() {return CharacterConfiguration.EvilWizard.IDLE.SPRITE_PATH;}
    public int getEvilWizardIdleSpriteWidth() {return CharacterConfiguration.EvilWizard.IDLE.SPRITE_WIDTH;}
    public int getEvilWizardIdleSpriteFrameCount() {return CharacterConfiguration.EvilWizard.IDLE.FRAME_COUNT;}

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

    public String getHeroKnightName() {return CharacterConfiguration.HeroKnight.NAME;}
    public int getHeroKnightHealth() {return CharacterConfiguration.HeroKnight.HEALTH;}
    public CombatStrategy getHeroKnightCombatStrategy() {return CharacterConfiguration.HeroKnight.STRATEGY;}
    public CharacterType getHeroKnightType() {return CharacterConfiguration.HeroKnight.TYPE;}
    public String getHeroKnightInfo() {return CharacterConfiguration.HeroKnight.INFO;}

    public GameCharacter createHeroKnightGameCharacter() {
        CharacterCreator warriorCreator = new WarriorCreator();
        return (Warrior) warriorCreator.create(
                getHeroKnightName(),
                getHeroKnightHealth(),
                getHeroKnightCombatStrategy(),
                getHeroKnightAnimations()
        );
    }

    public int getHeroKnightFrameWidth() {return CharacterConfiguration.HeroKnight.FRAME_WIDTH;}
    public int getHeroKnightFrameHeight() {return CharacterConfiguration.HeroKnight.FRAME_HEIGHT;}

    //IDLE
    public String getHeroKnightIdleSpritePath() {return CharacterConfiguration.HeroKnight.IDLE.SPRITE_PATH;}
    public int getHeroKnightIdleSpriteWidth() {return CharacterConfiguration.HeroKnight.IDLE.SPRITE_WIDTH;}
    public int getHeroKnightIdleSpriteFrameCount() {return CharacterConfiguration.HeroKnight.IDLE.FRAME_COUNT;}



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

    public String getMartialHeroName() {return CharacterConfiguration.MartialHero.NAME;}
    public int getMartialHeroHealth() {return CharacterConfiguration.MartialHero.HEALTH;}
    public CombatStrategy getMartialHeroCombatStrategy() {return CharacterConfiguration.MartialHero.STRATEGY;}
    public CharacterType getMartialHeroType() {return CharacterConfiguration.MartialHero.TYPE;}
    public String getMartialHeroInfo() {return CharacterConfiguration.MartialHero.INFO;}

    public GameCharacter createMartialHeroGaleCharacter() {
        CharacterCreator warriorCreator = new WarriorCreator();
        return (Warrior) warriorCreator.create(
                getMartialHeroName(),
                getMartialHeroHealth(),
                getMartialHeroCombatStrategy(),
                getMartialHeroAnimations()
        );
    }

    public int getMartialHeroFrameWidth() {return CharacterConfiguration.MartialHero.FRAME_WIDTH;}
    public int getMartialHeroFrameHeight() {return CharacterConfiguration.MartialHero.FRAME_HEIGHT;}

    //IDLE
    public String getMartialHeroIdleSpritePath() {return CharacterConfiguration.MartialHero.IDLE.SPRITE_PATH;}
    public int getMartialHeroIdleSpriteWidth() {return CharacterConfiguration.MartialHero.IDLE.SPRITE_WIDTH;}
    public int getMartialHeroIdleSpriteFrameCount() {return CharacterConfiguration.MartialHero.IDLE.FRAME_COUNT;}

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


    //----------------------------------------NIGHT BORNE------------------------------------------------------//

    public String getNightBorneName() {return CharacterConfiguration.NightBorne.NAME;}
    public int getNightBorneHealth() {return CharacterConfiguration.NightBorne.HEALTH;}
    public CombatStrategy getNightBorneCombatStrategy() {return CharacterConfiguration.NightBorne.STRATEGY;}
    public CharacterType getNightBorneType() {return CharacterConfiguration.NightBorne.TYPE;}
    public String getNightBorneInfo() {return CharacterConfiguration.NightBorne.INFO;}

    public GameCharacter createNightBorneGameCharacter() {
        CharacterCreator warriorCreator = new WarriorCreator();
        return (Warrior) warriorCreator.create(
                getNightBorneName(),
                getNightBorneHealth(),
                getNightBorneCombatStrategy(),
                getNightBorneAnimations()
        );
    }

    public int getNightBorneFrameWidth() {return CharacterConfiguration.NightBorne.FRAME_WIDTH;}
    public int getNightBorneFrameHeight() {return CharacterConfiguration.NightBorne.FRAME_HEIGHT;}

    //IDLE
    public String getNightBorneIdleSpritePath() {return CharacterConfiguration.NightBorne.IDLE.SPRITE_PATH;}
    public int getNightBorneIdleSpriteFrameCount() {return CharacterConfiguration.NightBorne.IDLE.FRAME_COUNT;}

    //MOVE
    public String getNightBorneMoveSpritePath() {return CharacterConfiguration.NightBorne.MOVE.SPRITE_PATH;}
    public int getNightBorneMoveSpriteFrameCount() {return CharacterConfiguration.NightBorne.MOVE.FRAME_COUNT;}

    //ATTACK
    public String getNightBorneAttackSpritePath() {return CharacterConfiguration.NightBorne.ATTACK.SPRITE_PATH;}
    public int getNightBorneAttackSpriteFrameCount() {return CharacterConfiguration.NightBorne.ATTACK.FRAME_COUNT;}

    //DEATH
    public String getNightBorneDeathSpritePath() {return CharacterConfiguration.NightBorne.DEATH.SPRITE_PATH;}
    public int getNightBorneDeathSpriteFrameCount() {return CharacterConfiguration.NightBorne.DEATH.FRAME_COUNT;}

    //HIT
    public String getNightBorneHitSpritePath() {return CharacterConfiguration.NightBorne.HIT.SPRITE_PATH;}
    public int getNightBorneHitSpriteFrameCount() {return CharacterConfiguration.NightBorne.HIT.FRAME_COUNT;}


    //----------------------------------------NECROMANCER------------------------------------------------------//

    public String getNecromancerName() {return CharacterConfiguration.Necromancer.NAME;}
    public int getNecromancerHealth() {return CharacterConfiguration.Necromancer.HEALTH;}
    public CombatStrategy getNecromancerCombatStrategy() {return CharacterConfiguration.Necromancer.STRATEGY;}
    public CharacterType getNecromancerType() {return CharacterConfiguration.Necromancer.TYPE;}
    public String getNecromancerInfo() {return CharacterConfiguration.Necromancer.INFO;}

    public GameCharacter createNecromancerGameCharacter() {
        CharacterCreator warriorCreator = new WarriorCreator();
        return (Warrior) warriorCreator.create(
                getNecromancerName(),
                getNecromancerHealth(),
                getNecromancerCombatStrategy(),
                getNecromancerAnimations()
        );
    }

    public int getNecromancerFrameWidth() {return CharacterConfiguration.Necromancer.FRAME_WIDTH;}
    public int getNecromancerFrameHeight() {return CharacterConfiguration.Necromancer.FRAME_HEIGHT;}

    //IDLE
    public String getNecromancerIdleSpritePath() {return CharacterConfiguration.Necromancer.IDLE.SPRITE_PATH;}
    public int getNecromancerIdleSpriteWidth() {return CharacterConfiguration.Necromancer.IDLE.SPRITE_WIDTH;}
    public int getNecromancerIdleSpriteFrameCount() {return CharacterConfiguration.Necromancer.IDLE.FRAME_COUNT;}

    //MOVE
    public String getNecromancerMoveSpritePath() {return CharacterConfiguration.Necromancer.MOVE.SPRITE_PATH;}
    public int getNecromancerMoveSpriteWidth() {return CharacterConfiguration.Necromancer.MOVE.SPRITE_WIDTH;}
    public int getNecromancerMoveSpriteFrameCount() {return CharacterConfiguration.Necromancer.MOVE.FRAME_COUNT;}

    //ATTACK
    public String getNecromancerAttackSpritePath() {return CharacterConfiguration.Necromancer.ATTACK.SPRITE_PATH;}
    public int getNecromancerAttackSpriteFrameCount() {return CharacterConfiguration.Necromancer.ATTACK.FRAME_COUNT;}

    //DEATH
    public String getNecromancerDeathSpritePath() {return CharacterConfiguration.Necromancer.DEATH.SPRITE_PATH;}
    public int getNecromancerDeathSpriteFrameCount() {return CharacterConfiguration.Necromancer.DEATH.FRAME_COUNT;}

    //HIT
    public String getNecromancerHitSpritePath() {return CharacterConfiguration.Necromancer.HIT.SPRITE_PATH;}
    public int getNecromancerHitSpriteWidth() {return CharacterConfiguration.Necromancer.HIT.SPRITE_WIDTH;}
    public int getNecromancerHitSpriteFrameCount() {return CharacterConfiguration.Necromancer.HIT.FRAME_COUNT;}



    private static final class GamePlayConfiguration {
        static final String ATTACK = "Attack";
        static final String RECRUIT = "Recruit";
        static final String ADD_VISITORS = "Add bonus";
    }

    public String getAttackButtonLabel() {return GamePlayConfiguration.ATTACK;}
    public String getRecruitButtonLabel() {return GamePlayConfiguration.RECRUIT;}
    public String getAddVisitorButtonLabel() {return GamePlayConfiguration.ADD_VISITORS;}


    public String getStringFromType(CharacterType type) {
        if (type == CharacterType.WARRIOR) {
            return "Warrior";
        }
        if (type == CharacterType.WIZARD) {
            return "Wizard";
        }
        return "Unknown";
    }



    private static final class VisitorsConfiguration {
        static final String BUFF_PATH = "/assets/visitors/Buff.png";
        static final String HEAL_PATH = "/assets/visitors/Heal.png";

        private static final class Buff {
            static final int FRAME_COUNT = 13;
            static final int FRAME_WIDTH = 18;
            static final int FRAME_HEIGHT = 34;
            static final String NAME = "Buff bonus";
            static final String DESCRIPTION =
                    "Improves warriors' strength \n" +
                    "Improves mages' intelligence \n" +
                    "Increases health \n" +
                    "Increases level \n";

        }

        private static final class Heal {
            static final int FRAME_COUNT = 8;
            static final int FRAME_WIDTH = 22;
            static final int FRAME_HEIGHT = 37;
            static final String NAME = "Heal bonus";
            static final String DESCRIPTION = "Increases health";
        }

    }

    public String getBuffPath() {return VisitorsConfiguration.BUFF_PATH;}
    public int getBuffFrameWidth() {return VisitorsConfiguration.Buff.FRAME_WIDTH;}
    public int getBuffFrameHeight() {return VisitorsConfiguration.Buff.FRAME_HEIGHT;}
    public int getBuffFrameCount() {return VisitorsConfiguration.Buff.FRAME_COUNT;}

    public String getHealPath() {return VisitorsConfiguration.HEAL_PATH;}
    public int getHealFrameWidth() {return VisitorsConfiguration.Heal.FRAME_WIDTH;}
    public int getHealFrameHeight() {return VisitorsConfiguration.Heal.FRAME_HEIGHT;}
    public int getHealFrameCount() {return VisitorsConfiguration.Heal.FRAME_COUNT;}

    public CharacterAnimation getBuffAnimation() {
        CharacterAnimation character = new CharacterAnimation();
        character.addAnimation(
                CharacterAnimation.CharacterState.IDLE,
                getBuffPath(),
                getBuffFrameCount(),
                getBuffFrameWidth(),
                getBuffFrameHeight(),
                Duration.millis(100)
        );
        character.setState(CharacterAnimation.CharacterState.IDLE);
        return character;
    }

    public CharacterAnimation getHealAnimation() {
        CharacterAnimation character = new CharacterAnimation();
        character.addAnimation(
                CharacterAnimation.CharacterState.IDLE,
                getHealPath(),
                getHealFrameCount(),
                getHealFrameWidth(),
                getHealFrameHeight(),
                Duration.millis(100)
        );
        character.setState(CharacterAnimation.CharacterState.IDLE);
        return character;
    }

    public String getBuffDescription() {return VisitorsConfiguration.Buff.DESCRIPTION;}
    public String getBuffName() {return VisitorsConfiguration.Buff.NAME;}
    public String getHealDescription() {return VisitorsConfiguration.Heal.DESCRIPTION;}
    public String getHealName() {return VisitorsConfiguration.Heal.NAME;}





}
