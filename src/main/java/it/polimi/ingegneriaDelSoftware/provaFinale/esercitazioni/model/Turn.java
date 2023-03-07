package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model;

import java.util.Observable;

public class Turn extends Observable {
    private Choice playerChoice;

    public Choice getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice(Choice playerChoice) {
        this.playerChoice = playerChoice;
        setChangedAndNotifyObservers(playerChoice);
    }

    private Choice cpuChoice;

    public Choice getCpuChoice() {
        return cpuChoice;
    }

    public void setCpuChoice(Choice cpuChoice) {
        this.cpuChoice = cpuChoice;
        setChangedAndNotifyObservers(cpuChoice);
    }

    private Outcome outcome;

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
        setChangedAndNotifyObservers(outcome);
    }

    public void clear() {
        outcome = null;
        cpuChoice = null;
        playerChoice = null;
    }

    private void setChangedAndNotifyObservers(Object o) {
        setChanged();
        notifyObservers(o);
    }
}