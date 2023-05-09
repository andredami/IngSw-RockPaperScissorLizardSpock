package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Choice;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Turn;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.TurnView;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observable;

public abstract class UI extends Observable<Choice> implements Runnable {
    public abstract void update(TurnView model, Turn.Event arg);
}
