package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.local;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.controller.Game;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.Client;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.Server;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Choice;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Turn;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.TurnView;

public class ServerImpl implements Server {

    private Turn model;
    private Game controller;

    @Override
    public void register(Client client) {
        this.model = new Turn();
        this.model.addObserver((o, arg) -> client.update(new TurnView(model), arg));

        this.controller = new Game(model, client);
    }

    @Override
    public void update(Client client, Choice arg) {
        this.controller.update(client, arg);
    }
}
