package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observable;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observer;

public class TurnView extends Observable<Turn.Event> implements Observer<Turn, Turn.Event> {
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
    public void update(Turn o, Turn.Event arg) {
        setChanged();
        notifyObservers(arg);
    }
}
