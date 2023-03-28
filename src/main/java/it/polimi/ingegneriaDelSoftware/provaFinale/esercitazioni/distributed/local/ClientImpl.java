package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.local;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.Client;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.Server;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Turn;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.TurnView;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view.TextualUI;

public class ClientImpl implements Client, Runnable {

    TextualUI view = new TextualUI();

    public ClientImpl(Server server) {
        server.register(this);
        view.addObserver((o, arg) -> server.update(this, arg));
    }

    @Override
    public void update(TurnView o, Turn.Event arg) {
        view.update(o, arg);
    }

    @Override
    public void run() {
        view.run();
    }
}
