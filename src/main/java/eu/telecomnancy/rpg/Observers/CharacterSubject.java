package eu.telecomnancy.rpg.Observers;




public interface CharacterSubject {
    void addObserver(CharacterObserver observer);
    void removeObserver(CharacterObserver observer);
    void notifyObservers(CharacterEvent event);
}
