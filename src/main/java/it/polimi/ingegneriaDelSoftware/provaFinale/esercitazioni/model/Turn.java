package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observable;

public class Turn extends Observable<Turn.Event> {

    public enum Event {
        PLAYER_CHOICE, CPU_CHOICE, OUTCOME
    }
    private Choice playerChoice;
    public Choice getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice(Choice playerChoice) {
        this.playerChoice = playerChoice;
        setChangedAndNotifyObservers(Event.PLAYER_CHOICE);
    }

    private Choice cpuChoice;
    public Choice getCpuChoice() {
        return cpuChoice;
    }

    public void setCpuChoice(Choice cpuChoice) {
        this.cpuChoice = cpuChoice;
        setChangedAndNotifyObservers(Event.CPU_CHOICE);
    }

    private Outcome outcome;
    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
        setChangedAndNotifyObservers(Event.OUTCOME);
    }

    public void clear() {
        outcome = null;
        cpuChoice = null;
        playerChoice = null;
    }

    private void setChangedAndNotifyObservers(Event arg) {
        setChanged();
        notifyObservers(arg);
    }
}