package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model;

import java.util.Observable;
import java.util.Observer;

public class TurnView extends Observable implements Observer {
    private final Turn model;

    public TurnView(Turn model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        this.model = model;
        model.addObserver(this);
    }

    public Choice getPlayerChoice() {
        return model.getPlayerChoice();
    }
    public Choice getCpuChoice() {
        return model.getCpuChoice();
    }
    public Outcome getOutcome() {
        return model.getOutcome();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}
