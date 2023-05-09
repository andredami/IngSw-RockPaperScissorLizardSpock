package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Turn;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.TurnView;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view.UI;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client, Runnable {

    private UI view;

    public ClientImpl(Server server, UI ui) throws RemoteException {
        super();
        initialize(server, ui);
    }

    public ClientImpl(Server server, int port, UI ui) throws RemoteException {
        super(port);
        initialize(server, ui);
    }

    public ClientImpl(Server server, int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf, UI ui) throws RemoteException {
        super(port, csf, ssf);
        initialize(server, ui);
    }

    private void initialize(Server server, UI ui) throws RemoteException {
        this.view = ui;
        server.register(this);
        view.addObserver((o, arg) -> {
            try {
                server.update(this, arg);
            } catch (RemoteException e) {
                System.err.println("Unable to update the server: " + e.getMessage() + ". Skipping the update...");
            }
        });
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
