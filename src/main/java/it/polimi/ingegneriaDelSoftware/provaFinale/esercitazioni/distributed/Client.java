package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Turn;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.TurnView;

public interface Client {
    /**
     * Notify the client of a model change
     * @param o     The resulting model view
     * @param arg   The causing event
     */
    void update(TurnView o, Turn.Event arg);
}
