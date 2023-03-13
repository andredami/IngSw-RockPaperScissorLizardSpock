package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observable;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observer;

public class TurnView extends Observable<String> implements Observer<Turn, String> {
    private final Turn model;

    public TurnView(Turn model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        this.model = model;
        model.addObserver(this);
    }
    public static final String FIELD_PLAYER_CHOICE = Turn.FIELD_PLAYER_CHOICE;
    public Choice getPlayerChoice() {
        return model.getPlayerChoice();
    }

    public static final String FIELD_CPU_CHOICE = Turn.FIELD_CPU_CHOICE;
    public Choice getCpuChoice() {
        return model.getCpuChoice();
    }

    public static final String FIELD_OUTCOME = Turn.FIELD_OUTCOME;
    public Outcome getOutcome() {
        return model.getOutcome();
    }

    @Override
    public void update(Turn o, String arg) {
        setChanged();
        notifyObservers(arg);
    }
}
