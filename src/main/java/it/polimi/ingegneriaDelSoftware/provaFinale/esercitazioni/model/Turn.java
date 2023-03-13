package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observable;

public class Turn extends Observable<String> {
    private Choice playerChoice;
    public static final String FIELD_PLAYER_CHOICE = "playerChoice";
    public Choice getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice(Choice playerChoice) {
        this.playerChoice = playerChoice;
        setChangedAndNotifyObservers(FIELD_PLAYER_CHOICE);
    }

    private Choice cpuChoice;
    public static final String FIELD_CPU_CHOICE = "cpuChoice";
    public Choice getCpuChoice() {
        return cpuChoice;
    }

    public void setCpuChoice(Choice cpuChoice) {
        this.cpuChoice = cpuChoice;
        setChangedAndNotifyObservers(FIELD_CPU_CHOICE);
    }

    private Outcome outcome;
    public static final String FIELD_OUTCOME = "outcome";
    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
        setChangedAndNotifyObservers(FIELD_OUTCOME);
    }

    public void clear() {
        outcome = null;
        cpuChoice = null;
        playerChoice = null;
    }

    private void setChangedAndNotifyObservers(String arg) {
        setChanged();
        notifyObservers(arg);
    }
}