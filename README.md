# RPG Project

## Overview
This project implements a game system featuring different character types (Warrior, Wizard) with various combat mechanics and animations. The architecture follows several design patterns including Strategy, Observer, Decorator, and Visitor patterns.

## Interface Preview

![video](assets/rpg_video_presentation.mp4)


## Structure

### Core Components
- **Characters**: Base character classes with specialized types (Warrior, Wizard)
- **Combat System**: Implements different combat strategies (Aggressive, Defensive, Neutral)
- **Animation System**: Handles character animations with state management
- **Team Management**: Allows creation and management of character teams
- **User Interface**: Configurable UI with scene management

### Design Patterns Used
- **Strategy Pattern**: Combat behavior implementation
- **Observer Pattern**: Character state monitoring
- **Decorator Pattern**: Character enhancement system
- **Visitor Pattern**: Character interactions
- **Facade Pattern**: Game system management
- **Command Pattern**: Action execution and management

### User Interface Features
- Character selection screen with animated previews
- Real-time combat animations
- Dynamic health and status indicators (to be implemented)
- Parallax background effects
- Responsive design for different screen sizes (to be implemented)


## Technical Documentation

### Character System
- Base character class with extensible properties
- Specialized character types (Warrior, Wizard)
- Character state management (Idle, Attack, Move, Death, Hit)


### Combat System
- Multiple combat strategies (to be implemented)
- Damage calculation system (to be implemented)
- Team-based combat (to be implemented)
- Buff and heal mechanics (to be implemented)

### Animation System
- State-based animations
- Sprite management
- Parallax background support
- Animation configuration system

### Interface System
- Configurable UI elements
- Scene management
- Settings management
- Multiple character selection system

## Getting Started

### Prerequisites
- Java Development Kit (JDK)
- JavaFX runtime
- Graphviz (for diagram generation)

### Installation
1. Clone the repository
2. Install required dependencies
3. Build the project
4. Run the main application


