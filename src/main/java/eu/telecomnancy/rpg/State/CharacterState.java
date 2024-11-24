package eu.telecomnancy.rpg.State;

public interface CharacterState {
    void onEnterState(Character character);
    void onExitState(Character character);
    void onUpdate(Character character);
    void onAttack(Character character, int damage);
    void onReceive(Character character, int damage);
    void inHealthChanged(Character character, int newHealth);
}
